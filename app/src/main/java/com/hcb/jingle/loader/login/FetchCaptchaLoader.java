package com.hcb.jingle.loader.login;

import com.hcb.jingle.loader.base.BaseGetLoader;
import com.hcb.jingle.model.login.CaptchaInBody;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class FetchCaptchaLoader extends BaseGetLoader<CaptchaInBody> {

    private final static String PATH = "user_login/sms_captcha/%s";

    public void sendCaptcha(String phoneNumber, RespReactor<CaptchaInBody> respReactor) {
        load(genUrl(PATH, phoneNumber), respReactor);
    }
}
