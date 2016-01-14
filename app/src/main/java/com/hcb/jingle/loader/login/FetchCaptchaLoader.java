package com.hcb.jingle.loader.login;

import com.hcb.jingle.loader.base.BaseGetLoader;
import com.hcb.jingle.model.login.CaptchaInBody;

/**
 * Created by Administrator on 2015/12/24.
 */
public class FetchCaptchaLoader extends BaseGetLoader<CaptchaInBody> {

    public void sendCaptcha(String phoneNumber, RespReactor<CaptchaInBody> respReactor) {
        String path = "/user_login/sms_captcha/%s";
        String url = genUrl(path, phoneNumber);
        load(url, respReactor);
    }
}
