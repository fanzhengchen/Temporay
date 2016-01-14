package com.hcb.jingle.bean;

import com.hcb.jingle.model.base.InBody;

public class UserBasicVO extends InBody {

    private String phone;       //	电话号码	没有设置时
    private String avatar;      //	头像	没有设置时
    private String nickname;    //	昵称	没有设置时
    private String gender;      //	性别	没有设置时

    public boolean isMale() {
        return "男".equals(gender);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
