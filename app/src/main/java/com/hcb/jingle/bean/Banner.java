package com.hcb.jingle.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by aojiao001 on 2016/1/9.
 */
public class Banner {
    private String imageUrl;
    private String jumpUrl;


    @JSONField(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JSONField(name = "image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JSONField(name = "jump_url")
    public String getJumpUrl() {
        return jumpUrl;
    }

    @JSONField(name = "jump_url")
    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
