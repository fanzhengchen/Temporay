package com.hcb.jingle.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class BannerVO {

    private String imgUrl;//    img_url		图片URL
    private String id;//    id		商品id

    @JSONField(name = "img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    @JSONField(name = "img_url")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
