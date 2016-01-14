package com.hcb.jingle.biz;

import android.app.Activity;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;

public class ShareHelper {

    public static void shareApp(final Activity act) {
        if (null == act) {
            return;
        }
        final ShareCenter.ShareBean shareBean = new ShareCenter.ShareBean()
                .setTitle(act.getString(R.string.share_title))
                .setContent(act.getString(R.string.share_content))
                .setImgResId(R.mipmap.ic_launcher)
                .setTargetUrl(GlobalConsts.SHARE_URL);

        ShareCenter.share(act, shareBean);
    }

}
