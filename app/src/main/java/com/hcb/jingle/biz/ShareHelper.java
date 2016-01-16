package com.hcb.jingle.biz;

import android.app.Activity;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.bean.CommodityVO;

public class ShareHelper {

    public static void shareGood(final Activity act, final CommodityVO good) {
        if (null == act) {
            return;
        }
        if (null == good) {
            shareApp(act);
            return;
        }
        final ShareCenter.ShareBean shareBean = new ShareCenter.ShareBean()
                .setTitle(good.getTitle())
                .setContent(good.getDesc())
                .setImgResId(R.mipmap.ic_launcher)
                .setTargetUrl(GlobalConsts.SHARE_URL);

        new ShareCenter().share(act, shareBean);
    }

    public static void shareApp(final Activity act) {
        if (null == act) {
            return;
        }
        final ShareCenter.ShareBean shareBean = new ShareCenter.ShareBean()
                .setTitle(act.getString(R.string.share_title))
                .setContent(act.getString(R.string.share_content))
                .setImgResId(R.mipmap.ic_launcher)
                .setTargetUrl(GlobalConsts.SHARE_URL);

        new ShareCenter().share(act, shareBean);
    }

}
