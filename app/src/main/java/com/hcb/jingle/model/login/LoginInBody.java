package com.hcb.jingle.model.login;

import com.alibaba.fastjson.annotation.JSONField;
import com.hcb.jingle.model.base.InBody;

public class LoginInBody extends InBody {

    private String uuid;
    private int isRegist;

    @JSONField(name = "user_uuid")
    public String getUuid() {
        return uuid;
    }

    @JSONField(name = "user_uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isRegist() {
        return 1 == isRegist;
    }

    @JSONField(name = "is_register")
    public void setIsRegist(int isRegist) {
        this.isRegist = isRegist;
    }

}
