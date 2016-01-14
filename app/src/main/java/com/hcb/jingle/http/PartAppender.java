package com.hcb.jingle.http;

import com.squareup.okhttp.MultipartBuilder;

public interface PartAppender {

    void addMoreParts(MultipartBuilder builder);
}
