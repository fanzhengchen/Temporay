package com.hcb.jingle.util;

import android.content.Context;
import android.widget.Toast;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.R;

public final class ToastUtil {


    private static Context context;

    private ToastUtil() {

    }

    public static void setContext(Context ctx) {
        context = ctx;
    }

    public static String show(int resId) {
        return context.getResources().getString(resId);
    }

    private static Toast mToast;

    public static void show(final String title) {
        showToast(title, Toast.LENGTH_SHORT);
    }

    public static void show(String title, int gravity) {
        if (mToast == null) {
            mToast = Toast.makeText(GlobalBeans.getSelf().getApp(), title, Toast.LENGTH_LONG);
        } else {
            mToast.setText(title);
        }
        mToast.setGravity(gravity, 0, 0);
        mToast.show();
    }


    public static void show(final String title, final boolean isLong) {
        showToast(title, Toast.LENGTH_LONG);
    }

    private static void showToast(final String title, final int dur) {
        if (mToast == null) {
            mToast = Toast.makeText(GlobalBeans.getSelf().getApp(), title, dur);
        } else {
            mToast.setText(title);
        }
        mToast.show();
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
