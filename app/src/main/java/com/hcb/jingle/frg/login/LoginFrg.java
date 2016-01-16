package com.hcb.jingle.frg.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.biz.ShareHelper;
import com.hcb.jingle.loader.login.FetchCaptchaLoader;
import com.hcb.jingle.loader.login.LoginLoader;
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.model.login.CaptchaInBody;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.model.login.LoginOutBody;
import com.hcb.jingle.util.FormatUtil;
import com.hcb.jingle.util.LoggerUtil;
import com.hcb.jingle.util.Md5;
import com.hcb.jingle.util.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.handler.UMQQSsoHandler;
import com.umeng.socialize.media.UMImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class LoginFrg extends BaseAuthFrg {

    private final static Logger LOG = LoggerFactory.getLogger(LoginFrg.class);

    private final long COUNT_DOWN_TOTAL = 60000;
    private final long COUNT_DOWN_INTERVAL = 1000;
    private final CountDownTimer countDownTimer = new CountDownTimer(COUNT_DOWN_TOTAL, COUNT_DOWN_INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            fetchCaptcha.setText(String.format("请等待 %d 秒", millisUntilFinished / COUNT_DOWN_INTERVAL));
            fetchCaptcha.setEnabled(false);
        }

        @Override
        public void onFinish() {
            fetchCaptcha.setEnabled(true);
            fetchCaptcha.setText(R.string.send_captcha);
        }
    };

    @Bind(R.id.fetchCaptchaButton) TextView fetchCaptcha;
    @Bind(R.id.captchaNumber) EditText captchaNumber;
    @Bind(R.id.phoneNumber) EditText phoneNumber;

    private UMShareAPI umAPI;

    @Override
    public int getTitleId() {
        return R.string.login;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_login, container, false);
        ButterKnife.bind(this, rootView);
        umAPI = UMShareAPI.get(act);
        return rootView;
    }

    @OnClick(R.id.fetchCaptchaButton)
    public void fetchCaptcha(View view) {
        if (!checkPhone(phoneNumber.getText().toString())) {
            return;
        }
        new FetchCaptchaLoader().sendCaptcha(phone, new AbsLoader.RespReactor<CaptchaInBody>() {
            @Override
            public void succeed(CaptchaInBody body) {
                ToastUtil.show(R.string.captcha_sending);
                countDownTimer.start();
            }

            @Override
            public void failed(String code, String reason) {
                ToastUtil.show(reason);
            }
        });
    }

    @OnClick(R.id.loginButton)
    public void login(View view) {
        if (!checkPhone(phoneNumber.getText().toString())) {
            return;
        }
        if (!checkCaptcha(captchaNumber.getText().toString())) {
            return;
        }
        final String rawString = FormatUtil.getDateString() + phone + GlobalConsts.JINGLE_PWD_KEY;
        final String md5 = Md5.encode(rawString);

        final LoginOutBody outBody = new LoginOutBody();
        outBody.setPassword(md5);
        outBody.setPhone(phone);
        outBody.setCaptcha(captcha);
        new LoginLoader().login(phone, outBody, new AbsLoader.RespReactor<LoginInBody>() {
            @Override
            public void succeed(LoginInBody body) {
                curUser.setPhone(phone);
                curUser.setPassword(md5);
                onLogin(body);
            }

            @Override
            public void failed(String code, String reason) {
                ToastUtil.show(reason);
            }
        });

    }

    @OnClick(R.id.btn_qq_login)
    public void loginQQ(View view) {
        if (umAPI.isInstall(act, SHARE_MEDIA.QQ)) {
            umAPI.doOauthVerify(act, SHARE_MEDIA.QQ, umAuthListener);
        } else {
            ToastUtil.show(getString(R.string.qq_not_installed));
        }
    }

    private final UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (ACTION_AUTHORIZE == action) {
                LoggerUtil.d(LOG, "Authorize succeed");
                //auth_time=,ret=0,sendinstall=,page_type=,appid=,pf=desktop_m_qq-10000144-android-2002-
                // uid=6B0521BC0A466CA026196443964B0599
                // pay_token=8C77C25C621F13C3828820E105B908AE
                // pfkey=fe28a3924cb69f03daf15276d35b6d8f
                // expires_in=7776000
                // openid=6B0521BC0A466CA026196443964B0599
                // access_token=9515F0336708C3D6C6E889A6BAB8DA6C
                umAPI.getPlatformInfo(act, platform, umAuthListener);
            } else if (ACTION_GET_PROFILE == action) {
                ToastUtil.show(getString(R.string.qq_authed_loging));
                LoggerUtil.d(LOG, "UserInfo--{}", data.toString());
                //is_yellow_year_vip=0, vip=0, level=0, yellow_vip_level=0, is_yellow_vip=0, msg=,// province=浙江, city=杭州
                // gender=男,
                // openid=6B0521BC0A466CA026196443964B0599,
                // screen_name=冰山飞羊,
                // profile_image_url=http://q.qlogo.cn/qqapp/1103630364/6B0521BC0A466CA026196443964B0599/100,
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtil.show(getString(R.string.qq_auth_failed));
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        umAPI.onActivityResult(requestCode, resultCode, data);
    }

    /////////////////////////////////////
    /// test share
    @OnClick(R.id.btn_share)
    public void share(View view) {
        ShareHelper.shareApp(act);
    }

}
