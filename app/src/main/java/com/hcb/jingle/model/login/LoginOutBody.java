package com.hcb.jingle.model.login;

import com.hcb.jingle.model.base.OutBody;

public class LoginOutBody extends OutBody {
    // 密码	是	字符串	密码由前端生成。32位md5码。
    // 生成规则：md5(当前时间+用户手机+密钥)。
    // 密钥为不变的6位字符串（vcar11） 示例：md5(2015-07-13 15:44:0013705185091vcar11)
    private String password;

    private String captcha;//	验证码	是	字符串	6位纯数字

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Deprecated
    private String phone;

    @Deprecated
    public String getPhone() {
        return phone;
    }

    @Deprecated
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
