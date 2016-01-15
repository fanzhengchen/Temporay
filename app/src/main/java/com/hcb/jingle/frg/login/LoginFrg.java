package com.hcb.jingle.frg.login;

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
import com.hcb.jingle.loader.login.FetchCaptchaLoader;
import com.hcb.jingle.loader.login.LoginLoader;
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.model.login.CaptchaInBody;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.model.login.LoginOutBody;
import com.hcb.jingle.util.FormatUtil;
import com.hcb.jingle.util.Md5;
import com.hcb.jingle.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class LoginFrg extends BaseAuthFrg {

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

    @Override
    public int getTitleId() {
        return R.string.login;
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
}
