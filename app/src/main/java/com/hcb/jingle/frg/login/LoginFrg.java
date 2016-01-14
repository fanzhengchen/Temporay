package com.hcb.jingle.frg.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.loader.login.FetchCaptchaLoader;
import com.hcb.jingle.loader.login.LoginLoader;
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.model.login.CaptchaInBody;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.model.login.LoginOutBody;
import com.hcb.jingle.util.FormatUtil;
import com.hcb.jingle.util.Md5;
import com.hcb.jingle.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2015/12/24.
 */
public class LoginFrg extends BaseAuthFrg {
    private final Logger LOG = LoggerFactory.getLogger(LoginFrg.class);
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


    @Bind(R.id.fetchCaptchaButton)
    TextView fetchCaptcha;
    @Bind(R.id.captchaNumber)
    EditText captchaNumber;
    @Bind(R.id.phoneNumber)
    EditText phoneNumber;
    @Bind(R.id.loginButton)
    TextView loginButton;
    @Bind(R.id.user_protocol)
    TextView userProtocol;
    @Bind(R.id.checkbox)
    CheckBox checkBox;

    private LoginOutBody outBody = new LoginOutBody();

    @Override
    public int getTitleId() {
        return R.string.login_with_phone_number;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_login, container, false);
        ButterKnife.bind(this, rootView);
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
        if (!checkBox.isChecked()) {
            ToastUtil.show("请同意用户协议");
            return;
        }
        if (checkCaptcha(captchaNumber.getText().toString())) {
            return;
        }
        outBody.setCaptcha(captcha);
        String rawString = FormatUtil.getDateString() + phone + GlobalConsts.JINGLE_PWD_KEY;
        String md5 = Md5.encode(rawString);
        outBody.setPassword(md5);
        outBody.setPhone(phone);
        curUser.setPassword(md5);

        new LoginLoader().login(phone, outBody, new AbsLoader.RespReactor<LoginInBody>() {
            @Override
            public void succeed(LoginInBody body) {
                curUser.setPhone(phone);
                curUser.setUid(body.getUuid());
                curUser.fetchBasicInfo(null);
                act.finishDown();
            }

            @Override
            public void failed(String code, String reason) {
                ToastUtil.show(reason);
            }
        });


    }
}
