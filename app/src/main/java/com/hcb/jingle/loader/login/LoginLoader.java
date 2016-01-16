package com.hcb.jingle.loader.login;

import com.hcb.jingle.loader.base.BasePostLoader;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.model.login.LoginOutBody;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class LoginLoader extends BasePostLoader<LoginOutBody, LoginInBody> {

    private final static String PATH = "user_login/login/%s";

    public void login(String phone, LoginOutBody body, RespReactor<LoginInBody> respReactor) {
        load(genUrl(PATH, phone), body, respReactor);
    }
}
