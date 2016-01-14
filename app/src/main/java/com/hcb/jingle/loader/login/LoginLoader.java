package com.hcb.jingle.loader.login;

import com.hcb.jingle.loader.base.BasePostLoader;
import com.hcb.jingle.model.login.LoginInBody;
import com.hcb.jingle.model.login.LoginOutBody;

/**
 * Created by Administrator on 2015/12/24.
 */
public class LoginLoader extends BasePostLoader<LoginOutBody, LoginInBody> {

    public void login(String phone, LoginOutBody body, RespReactor<LoginInBody> respReactor) {
        String path = "/user_login/login/%s";
        String url = genUrl(path, phone);
        load(url, body, respReactor);
    }
}
