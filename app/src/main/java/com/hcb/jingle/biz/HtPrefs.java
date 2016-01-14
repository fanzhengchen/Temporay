package com.hcb.jingle.biz;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

public class HtPrefs {

//    private static final String KEY_VERCODE = "key_version";
//    private static final String KEY_FIRST_START = "key_first";
//    private static final String KEY_TOKEN = "key_token";
//    private static final String TOKEN_EXPIRE = "token_expire";
    private final static String USER_UUID = "user_id";
    private final static String USER_CID = "user_cid";
    private final static String USER_PWD = "user_pwd";
    private final static String USER_PHONE = "user_phone";
    private final static String USER_UNREAD = "unread_";
    private final static String FIRST_LOGIN = "first_login";

    // default
    private static SharedPreferences getDefault(final Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUid(final Context ctx, final String uid) {
        getDefault(ctx).edit().putString(USER_UUID, uid).commit();
    }

    public static String getUid(final Context ctx) {
        return getDefault(ctx).getString(USER_UUID, null);
    }

    public static void setPassword(final Context ctx, final String password) {
        getDefault(ctx).edit().putString(USER_PWD, password).commit();
    }

    public static String getPassword(final Context ctx) {
        return getDefault(ctx).getString(USER_PWD, null);
    }

    public static void setUnread(final Context ctx, final String uid, final int c) {
        getDefault(ctx).edit().putInt(USER_UNREAD + uid, c).commit();
    }

    public static int getUnread(final Context ctx, final String uid) {
        return getDefault(ctx).getInt(USER_UNREAD + uid, 0);
    }

    public static void setCid(final Context ctx, final String cid) {
        getDefault(ctx).edit().putString(USER_CID, cid).commit();
    }

    public static String getCid(final Context ctx) {
        return getDefault(ctx).getString(USER_CID, null);
    }

    public static String getPhone(Context ctx) {
        return getDefault(ctx).getString(USER_PHONE, null);
    }

    public static void setPhone(Context ctx, String phone) {
        getDefault(ctx).edit().putString(USER_PHONE, phone).commit();
    }

}
