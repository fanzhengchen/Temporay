package com.hcb.jingle;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.hcb.jingle.bean.AppInfo;
import com.hcb.jingle.bean.DeviceInfo;
import com.hcb.jingle.biz.CurrentUser;
import com.hcb.jingle.biz.EventCenter;
import com.hcb.jingle.cache.CacheCenter;
import com.hcb.jingle.cache.SignalCache;
import com.hcb.jingle.http.HttpProvider;
import com.hcb.jingle.util.DeviceHelper;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.common.SocializeConstants;

import java.io.File;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class GlobalBeans {

    private GlobalBeans(final Context ctx) {
        this.ctx = ctx;
    }

    public static void initForMainUI(final Context ctx) {
        self = new GlobalBeans(ctx);
        self.uiHandler = new Handler(ctx.getMainLooper());

        self.initBizObjects();
        self.initImageLoader();

        self.initUmeng();
//        PushManager.getInstance().initialize(ctx);
    }

    public static GlobalBeans getSelf() {
        return self;
    }

    public void onTerminate() {
        try {
            ImageLoader.getInstance().destroy();
            CacheCenter.clearCameraTempFile();
            exeService.shutdown();
            MobclickAgent.onKillProcess(ctx);
        } catch (Exception e) {
        }
        self = null;
    }

    private void initBizObjects() {
        device = DeviceHelper.readDeviceInfo(ctx);
        appInfo = DeviceHelper.readAppInfo(ctx);
        exeService = new ScheduledThreadPoolExecutor(3);
        cacheCenter = new CacheCenter(ctx);
        httpProvider = new HttpProvider(ctx);
        eventCenter = new EventCenter(uiHandler);
        curUser = new CurrentUser(ctx);
    }

    private void initUmeng() {
        AnalyticsConfig.setAppkey(ctx, GlobalConsts.UMENG_APPKEY);
        MobclickAgent.openActivityDurationTrack(false);
        SocializeConstants.APPKEY = GlobalConsts.UMENG_APPKEY;
        SocializeConstants.DEBUG_MODE = GlobalConsts.DEBUG;
        PlatformConfig.setQQZone(GlobalConsts.QQ_ID, GlobalConsts.QQ_SECRET);
    }

    private void initImageLoader() {

        LruDiskCache imgCache = null;
        try {
            final String imgCacheDir = cacheCenter.imgCacheDir;
            imgCache = new LruDiskCache(new File(imgCacheDir),
                    new Md5FileNameGenerator(), CacheCenter.MAX_DISK_IMAGE);
        } catch (Exception e) {
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.stub_image) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.stub_image)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.stub_image)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                        //.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .build();//构建完成
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(ctx)
                .memoryCache(new UsingFreqLimitedMemoryCache(CacheCenter.MAX_MEM_IMAGE))
                .diskCache(imgCache)
                .defaultDisplayImageOptions(options);
        ImageLoader.getInstance().init(builder.build());
    }

    public final static DisplayImageOptions avatarOpts = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.stub_avatar)
            .showImageForEmptyUri(R.mipmap.stub_avatar)
            .showImageOnFail(R.mipmap.stub_avatar)
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
            .imageScaleType(ImageScaleType.EXACTLY)//设置图片以如何的编码方式显示
            .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型//
            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
            .build();//构建完成

    private static GlobalBeans self;

    private Context ctx;
    private HttpProvider httpProvider;
    private ScheduledExecutorService exeService;

    private Handler uiHandler;
    private EventCenter eventCenter;
    private CacheCenter cacheCenter;
    private DeviceInfo device;// device(phone,pad,or other)'s information
    private AppInfo appInfo;// this beans's information
    private CurrentUser curUser;

    public Context getApp() {
        return ctx;
    }

    public DeviceInfo getDeviceInfo() {
        return device;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public CurrentUser getCurUser() {
        return curUser;
    }

    public HttpProvider getHttpProvider() {
        return httpProvider;
    }

    public Handler getHandler() {
        return uiHandler;
    }

    public EventCenter getEventCenter() {
        return eventCenter;
    }

    public CacheCenter getCacheCenter() {
        return cacheCenter;
    }

    public SignalCache getSignalCache() {
        return cacheCenter.getSignalCache();
    }

    public ScheduledExecutorService getExecutorService() {
        return exeService;
    }

}
