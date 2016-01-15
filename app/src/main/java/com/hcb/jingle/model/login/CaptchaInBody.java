package com.hcb.jingle.model.login;

import com.hcb.jingle.model.base.InBody;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class CaptchaInBody extends InBody {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
