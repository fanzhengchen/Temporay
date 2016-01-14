package com.hcb.jingle.model.login;

import com.hcb.jingle.model.base.InBody;

/**
 * Created by Administrator on 2015/12/24.
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
