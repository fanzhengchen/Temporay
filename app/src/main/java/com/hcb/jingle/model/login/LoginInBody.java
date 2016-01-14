package com.hcb.jingle.model.login;

import com.alibaba.fastjson.annotation.JSONField;
import com.hcb.jingle.model.base.InBody;

public class LoginInBody extends InBody {

    private String uuid;
    private int is_register;


    public boolean isRegister() {
        return is_register == 1;
    }

    public int getIs_register() {
        return is_register;
    }

    public void setIs_register(int is_register) {
        this.is_register = is_register;
    }

    @JSONField(name = "user_uuid")
    public String getUuid() {
        return uuid;
    }

    @JSONField(name = "user_uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
