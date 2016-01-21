package com.hcb.jingle.util;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class StringUtil {

    public static boolean isEmpty(final String s) {
        return null == s || s.trim().length() == 0;
    }

    public static boolean isEqual(final String s1, final String s2) {
        return (s1 == null && s2 == null) || (null != s1 && s1.equals(s2));
    }

    public static String getFileNameFromUrl(final String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }
        return url.replaceAll("[.:/,%?&= ]", "+").replaceAll("[+]+", "+");
    }

    public static String cutLength(final String s, final int len) {
        return (s != null && s.length() >= len) ? s.substring(0, 9) : s;
    }

    public static String replaceBrace(final String s) {
        return (null == s) ? null : s.replace("{", "").replace("}", "");
    }

    public static byte[] getBytes(final String src) {
        if (null == src || src.length() == 0) {
            return null;
        }
        try {
            return src.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    // 将输入流转换成字符串
    public static String parseInStream(final InputStream is) throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray());
    }

}
