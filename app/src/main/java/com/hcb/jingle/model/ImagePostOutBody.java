package com.hcb.jingle.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hcb.jingle.model.base.OutBody;

public class ImagePostOutBody extends OutBody {

    private String fileName;

    @JSONField(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    @JSONField(name = "file_name")
    public ImagePostOutBody setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
