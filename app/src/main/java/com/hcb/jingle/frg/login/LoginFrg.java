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
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.loader.login.FetchCaptchaLoader;
import com.hcb.jingle.loader.login.LoginLoader;
import com.hcb.jingle.loader.user.ProfileUploader;
import com.hcb.jingle.model.ProfileOutBody;
import com.hcb.jingle.model.base.InBody;
import com.hcb.jingle.model.login.CaptchaInBody;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.model.login.LoginOutBody;
import com.hcb.jingle.util.FormatUtil;
import com.hcb.jingle.util.Md5;
import com.hcb.jingle.util.ToastUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

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
        outBody.setCaptcha(captcha);
        showProgressDialog(R.string.login, R.string.login_ing);
        new LoginLoader().login(phone, outBody, new AbsLoader.RespReactor<LoginInBody>() {
            @Override
            public void succeed(LoginInBody body) {
                curUser.setPhone(phone);
                curUser.setPassword(md5);
                onLogin(body);
            }

            @Override
            public void failed(String code, String reason) {
                dismissDialog();
                ToastUtil.show(reason);
            }
        });

    }

    @OnClick(R.id.btn_qq_login)
    public void qqClicked(View view) {
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
                loginQQ(data.get("openid"));
            } else if (ACTION_GET_PROFILE == action) {
                registThumbNick(data.get("screen_name"), data.get("profile_image_url"));
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

    private void loginQQ(final String openId) {
        final String rawString = FormatUtil.getDateString() + openId + GlobalConsts.JINGLE_PWD_KEY;
        final String md5 = Md5.encode(rawString);

        final LoginOutBody outBody = new LoginOutBody();
        outBody.setPassword(md5);
        showProgressDialog(R.string.login_with_qq, R.string.qq_authed_loging);
        new LoginLoader().loginQQ(openId, outBody, new AbsLoader.RespReactor<LoginInBody>() {
            @Override
            public void succeed(LoginInBody body) {
                curUser.setPassword(md5);
                if (body.isRegist()) {
                    curUser.setUid(body.getUuid());
                    showProgressDialog(R.string.login_with_qq, R.string.qq_thumb_nick);
                    umAPI.getPlatformInfo(act, SHARE_MEDIA.QQ, umAuthListener);
                } else {
                    onLogin(body);
                }
            }

            @Override
            public void failed(String code, String reason) {
                dismissDialog();
                ToastUtil.show(reason);
            }
        });
    }

    private void registThumbNick(String thumb, String nick) {
        new ProfileUploader().upload(new ProfileOutBody().setAvatar(thumb).setNickname(nick),
                new AbsLoader.RespReactor() {
                    @Override
                    public void succeed(InBody body) {
                        dismissDialog();
                        curUser.fetchBasicInfo(null);
                        eventCenter.evtLogin();
                    }

                    @Override
                    public void failed(String code, String reason) {
                        dismissDialog();
                        curUser.fetchBasicInfo(null);
                        eventCenter.evtLogin();
                    }
                });
    }

}
