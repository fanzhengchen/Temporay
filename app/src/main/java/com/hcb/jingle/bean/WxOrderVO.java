package com.hcb.jingle.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class WxOrderVO {

    private String appid;//	微信分配的公众账号ID
    private String partnerid;//	微信支付分配的商户号
    private String prepayid;//	微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
    private String pkg;//	暂填写固定值Sign=WXPay
    private String noncestr;//	随机字符串
    private String timestamp;//	时间戳
    private String sign;//	签名

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    @JSONField(name = "package")
    public String getPkg() {
        return pkg;
    }

    @JSONField(name = "package")
    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
