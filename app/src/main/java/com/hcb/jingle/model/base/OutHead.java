package com.hcb.jingle.model.base;

import com.alibaba.fastjson.annotation.JSONField;

public class OutHead {

    private String uid;// 	user_uuid	当前登录用户id	字符串	是
    private String cid;// 当前设备的client_id    字符串 是
    private String password;// 密码校验    字符串 是
    private String appVersion;// app_version  字符串 是
    private String appOs = "ANDROID";// app_os 当前所使用的操作系统	字符串	是	IOS或ANDROID
    private String longitude;//当前登录用户的经度    字符串 否
    private String latitude;//当前登录用户的纬度    字符串 否

    public String getPassword() {
        return password;
    }

    public OutHead setPassword(String password) {
        this.password = password;
        return this;
    }

    @JSONField(name = "app_version")
    public String getAppVersion() {
        return appVersion;
    }

    @JSONField(name = "app_version")
    public OutHead setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    @JSONField(name = "app_os")
    public String getAppOs() {
        return appOs;
    }

    @JSONField(name = "app_os")
    public OutHead setAppOs(String appOs) {
        this.appOs = appOs;
        return this;
    }

    public String getCid() {
        return cid;
    }

    public OutHead setCid(String cid) {
        this.cid = cid;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public OutHead setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public OutHead setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    @JSONField(name = "user_uuid")
    public String getUid() {
        return uid;
    }

    @JSONField(name = "user_uuid")
    public OutHead setUid(String uid) {
        this.uid = uid;
        return this;
    }

    @Override
    public String toString() {
        return "OutHead{" +
                "uid='" + uid + '\'' +
                ", cid='" + cid + '\'' +
                ", password='" + password + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appOs='" + appOs + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
