package com.hcb.jingle.biz;

import android.app.Activity;
import android.text.TextUtils;

import com.hcb.jingle.GlobalConsts;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.umeng.socialize.media.SinaShareContent;
//import com.umeng.socialize.media.SmsShareContent;
//import com.umeng.socialize.media.UMImage;

public class ShareCenter {

    public static class ShareBean {
        String title;
        String content;
        String targetUrl;
        String imgUrl;
        String imgPath;
        int imgResId;

        public ShareBean setTitle(final String title) {
            this.title = title;
            return this;
        }

        public ShareBean setContent(final String content) {
            this.content = content;
            return this;
        }

        public ShareBean setTargetUrl(final String targetUrl) {
            this.targetUrl = targetUrl;
            return this;
        }

        public ShareBean setImgUrl(final String imgUrl) {
            this.imgUrl = imgUrl;
            return this;
        }

        public ShareBean setImgPath(final String imgPath) {
            this.imgPath = imgPath;
            return this;
        }

        public ShareBean setImgResId(final int imgResId) {
            this.imgResId = imgResId;
            return this;
        }
    }

//    private static UMSocialService mController = null;

    private static void init(final Activity act) {
//        mController = UMServiceFactory.getUMSocialService(act.getPackageName());
//        // 添加短信
//        new SmsHandler().addToSocialSDK();
//        // 添加微信平台
//        new UMWXHandler(act, GlobalConsts.WX_ID, GlobalConsts.WX_SECRET)
//                .addToSocialSDK();
//        // 支持微信朋友圈
//        new UMWXHandler(act, GlobalConsts.WX_ID, GlobalConsts.WX_SECRET)
//                .setToCircle(true)
//                .addToSocialSDK();
//
//        mController.getConfig().removePlatform(SHARE_MEDIA.TENCENT);// 腾讯微博
//        mController.getConfig().removePlatform(SHARE_MEDIA.QZONE);// QQ空间
//        mController.getConfig().setPlatformOrder(
//                SHARE_MEDIA.SMS,
//                SHARE_MEDIA.SINA,
//                SHARE_MEDIA.WEIXIN,
//                SHARE_MEDIA.WEIXIN_CIRCLE);
    }

    static void share(final Activity act, final ShareBean bean) {
        if (null == act || null == bean) {
            return;
        }
//        if (null == mController) {
//            init(act);
//        }
//        addSmsConfig(bean);
//        addSinaConfig(act, bean);
//        configWX(act, bean, new WeiXinShareContent());
//        configWX(act, bean, new CircleShareContent());
//
//        mController.openShare(act, false);
    }

    private static void addSmsConfig(ShareBean bean) {
//        SmsShareContent sms = new SmsShareContent();
//        sms.setShareContent(bean.content + " " + bean.targetUrl);
//        mController.setShareMedia(sms);
    }

    private static void addSinaConfig(final Activity act, final ShareBean bean) {
//        SinaShareContent sinaContent = new SinaShareContent();
//        sinaContent.setShareContent(bean.content + " " + bean.targetUrl);
//        final UMImage umImg = genUmImg(act, bean);
//        if (null != umImg) {
//            sinaContent.setShareImage(umImg);
//        }
//        mController.setShareMedia(sinaContent);
    }

//    private static void configWX(Activity act, ShareBean bean,
//                                 BaseShareContent shareContent) {
//        shareContent.setTitle(bean.title);
//        shareContent.setShareContent(bean.content);
//        shareContent.setTargetUrl(bean.targetUrl);
//
//        final UMImage umImg = genUmImg(act, bean);
//        if (null != umImg) {
//            shareContent.setShareImage(umImg);
//        }
//        mController.setShareMedia(shareContent);
//    }

//    private static UMImage genUmImg(Activity act, ShareBean bean) {
//        UMImage umImg = null;
//        if (!TextUtils.isEmpty(bean.imgUrl)) {
//            umImg = new UMImage(act, bean.imgUrl);
//        } else if (!TextUtils.isEmpty(bean.imgPath)) {
//            umImg = new UMImage(act, bean.imgPath);
//        } else if (bean.imgResId > 0) {
//            umImg = new UMImage(act, bean.imgResId);
//        }
//        return umImg;
//    }

}
