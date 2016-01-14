package com.hcb.jingle.util;

import android.text.TextUtils;

public class ImageUrlWraper {

    private final static String PIC_L = "_size_3x";
    private final static String PIC_M = "_size_2x";
    private final static String PIC_S = "_size_1x";

    public static String makeUrlL(final String url) {
        return insert(url, PIC_L);
    }

    public static String makeUrlM(final String url) {
        return insert(url, PIC_M);
    }

    public static String makeUrlS(final String url) {
        return insert(url, PIC_S);
    }

    private static String insert(final String url, final String tag) {
        if (TextUtils.isEmpty(url)) {
            return null;
        }
        if (url.contains(tag)) {
            return url;
        }
        final int wrap = url.lastIndexOf('.');
        if (wrap > 0 && wrap < url.length()) {
            return url.substring(0, wrap) + tag + url.substring(wrap);
        }
        return null;
    }

}
