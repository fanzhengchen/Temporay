
package com.hcb.jingle.biz;

import android.content.Context;

import com.hcb.jingle.bean.UserBasicVO;
import com.hcb.jingle.loader.base.AbsLoader.RespReactor;
import com.hcb.jingle.loader.user.UserBasicLoader;

public class CurrentUser {

    public interface UserInfoListener {
        void succeed();

        void failed(String reason);
    }

    private final Context ctx;
    private String uid;
    private String cid;
    private String password;// 密码校验
    private String phone;
    private int unreadCount;

    private void initUnread() {
        unreadCount = HtPrefs.getUnread(ctx, uid);
    }

    public int getTotalUnread() {
        return unreadCount;
    }

    private void plusUnread(final int c) {
        this.unreadCount += c;
        if (this.unreadCount < 0) {
            this.unreadCount = 0;
        }
        HtPrefs.setUnread(ctx, uid, unreadCount);
    }

    private UserBasicVO userBasic;

    public CurrentUser(final Context ctx) {
        this.ctx = ctx;

        uid = HtPrefs.getUid(ctx);
        password = HtPrefs.getPassword(ctx);
        phone = HtPrefs.getPhone(ctx);
        if (null != uid) {
            initUnread();
        }
    }

    public void logout() {
        setUid(null);
        setPassword(null);
        setPhone(null);
        userBasic = null;
        unreadCount = 0;
    }

    public String getUid() {
        return uid;
    }

    public CurrentUser setUid(final String uid) {
        this.uid = uid;
        HtPrefs.setUid(ctx, uid);
        if (null != uid) {
            initUnread();
        }
        return this;
    }

    public boolean isLogin() {
        return this.uid != null;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        HtPrefs.setPhone(ctx, phone);
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password;
        HtPrefs.setPassword(ctx, password);
        return this;
    }

    public String getCid() {
        return cid;
    }

    public CurrentUser setCid(String cid) {
        this.cid = cid;
        return this;
    }

    public void fetchBasicInfo(final UserInfoListener listener) {
        if (userBasic != null) {
            notifySucceed(listener);
            return;
        }
        reloadBasic(listener);
    }

    public void reloadBasic(final UserInfoListener listener) {
        if (null == uid) {
            return;
        }
        new UserBasicLoader().load(new RespReactor<UserBasicVO>() {
            @Override
            public void succeed(UserBasicVO body) {
                userBasic = body;
                notifySucceed(listener);
            }

            @Override
            public void failed(String code, String reason) {
                notifyFailed(listener, reason);
            }
        });

    }

    private void notifySucceed(final UserInfoListener listener) {
        if (null != listener) {
            listener.succeed();
        }
    }

    private void notifyFailed(final UserInfoListener listener, final String reason) {
        if (null != listener) {
            listener.failed(reason);
        }
    }

}
