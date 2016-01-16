package com.hcb.jingle.biz;

import android.app.Activity;
import android.text.TextUtils;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.util.ToastUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

/**
 * <b>友盟分享集中管理</b>
 * <li>yang.zhao</li>
 * <li>2016-01-16</li>
 * 各平台支持的类型:
 * <center><table>
 * <tr><th>平台</th><th>title</th><th>Text</th><th>TargetUrl</th><th>图片</th><th>音乐</th><th>视频</th></tr>
 * <tr><td>新浪微博<td>不支持<td>最多140个字符<td>TatgetUrl只能加在文字中间或后面，当同时传递视频/音频时无效<td>支持JPG\PNG\GIF格式的本地及URL图片，GIF图片无动态效果<td>支持URL音乐,显示音乐播放缩略图需要linkcard权限<td>支持URL视频，显示视频播放缩略图需要linkcard权限</tr>
 * <tr><td>QQ好友<td>最多20个字符<td>最多30个字符<td>http链接分享后无法打开targetURL，https链接可以打开，纯图片分享时点击无效<td>支持JPG\PNG\GIF格式的本地及URL图片，URL形式及resource内的GIF图片有动态效果，SD卡中的GIF图片无动态效果<td>支持URL音乐,音乐author无法显示，可以点击音乐播放按钮直接播放<td>支持URL视频，视频会转化为音乐样式</tr>
 * <tr><td>QQ空间<td>最多20个字符<td>最多30个字符<td>不支持www开头的URL链接<td>不支持纯图片分享，支持JPG\PNG\GIF格式的本地及URL图片，URL形式及resource内的GIF图片有动态效果，SD卡中的GIF图片无动态效果<td>支持URL音乐,音乐author无法显示，可以点击音乐播放按钮直接播放<td>支持URL视频，视频会转化为音乐样式</tr>
 * <tr><td>微信好友<td>支持<td>支持超长字符和特殊字符<td>纯图片分享时targetURL无效<td>支持JPG\PNG\GIF格式的本地及URL图片，图文分享类型时GIF图片无动态效果<td>支持URL音乐,音乐author无法显示，可以点击音乐播放按钮直接播放	<td>支持URL视频，视频会转化为音乐样式</tr>
 * </table> </center>
 */
public class ShareCenter {

    public void share(final Activity act, final ShareBean bean) {
        if (null == act || null == bean) {
            return;
        }
        new ShareAction(act).setDisplayList(MEDIA_LIST)
                .withText(bean.content)
                .withTitle(bean.title)
                .withTargetUrl(bean.targetUrl)
                .withMedia(genUmImg(act, bean))
                .setCallback(umShareListener)
                .open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtil.show(platform + " 分享成功！");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtil.show(platform + " 分享失败！");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtil.show(platform + " 分享取消了");
        }
    };

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

    private final static SHARE_MEDIA[] MEDIA_LIST = {
            SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA,
            SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
    };

    private static UMImage genUmImg(Activity act, ShareBean bean) {
        UMImage umImg = null;
        if (!TextUtils.isEmpty(bean.imgUrl)) {
            umImg = new UMImage(act, bean.imgUrl);
        } else if (!TextUtils.isEmpty(bean.imgPath)) {
            umImg = new UMImage(act, bean.imgPath);
        } else if (bean.imgResId > 0) {
            umImg = new UMImage(act, bean.imgResId);
        }
        return umImg;
    }

}
