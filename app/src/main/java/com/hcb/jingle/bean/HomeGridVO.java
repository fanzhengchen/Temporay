package com.hcb.jingle.bean;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class HomeGridVO {

    private String imgUrl;//    img_url		图片URL
    private String title;
    private String tag;// 类别标签
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
