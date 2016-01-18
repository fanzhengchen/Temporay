package com.hcb.jingle.model;

import com.hcb.jingle.model.base.OutBody;

public class ProfileOutBody extends OutBody {
    private String avatar;
    private String nickname;

    public String getAvatar() {
        return avatar;
    }

    public ProfileOutBody setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public ProfileOutBody setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
