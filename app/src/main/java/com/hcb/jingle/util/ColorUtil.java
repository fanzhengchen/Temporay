package com.hcb.jingle.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.hcb.jingle.widget.photoview.Compat;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ColorUtil {

    public static Context context;

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static int getColor(int id) {
        return ContextCompat.getColor(context, id);
    }
}
