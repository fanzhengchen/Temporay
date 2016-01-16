package com.hcb.jingle;

public class GlobalConsts {

    public static final boolean DEBUG = true;

    private static final String HOST_DEBUG = "http://b2.waibao.help/dingdang/index.php/";
    private static final String HOST_RELEASE = "http://waitToBeAdd/index.php";
    public static final String HOST_IMAGE = "http://honey.waibao.help/honey/service_image/index.php";
    public static final String HOST = DEBUG ? HOST_DEBUG : HOST_RELEASE;
    public static final String UPLOAD_IMAGE = "http://121.199.47.167/honey/service_image/index.php";

    //------------------友盟统计分享-------------------
    //// TODO: 2016/1/15 umeng KEY, waitToBeAdd
    private static final String UMENG_KEY_DEBUG = "5698d68e67e58ed3c400281e";
    private static final String UMENG_KEY_RELEASE = "waitToBeAdd";
    public static final String UMENG_APPKEY = DEBUG ? UMENG_KEY_DEBUG : UMENG_KEY_RELEASE;

    //------------------微信分享-------------------
    private static final String WX_ID_DEBUG = "wxe6c31febbd05d58d";
    private static final String WX_SECRET_DEBUG = "efbbec4b7b7440e339f1192d0733082b";
    private static final String WX_ID_RELEASE = "waitToBeAdd";
    private static final String WX_SECRET_RELEASE = "waitToBeAdd";
    public static final String WX_ID = DEBUG ? WX_ID_DEBUG : WX_ID_RELEASE;
    public static final String WX_SECRET = DEBUG ? WX_SECRET_DEBUG : WX_SECRET_RELEASE;

    //------------------QQ授权-------------------
    public static final String QQ_ID = "1105117308";
    public static final String QQ_SECRET = "oj8BdgVB4jfdVrmg";

    //------------------微博授权-------------------
    public static final String WB_ID = "292075378";
    public static final String WB_SECRET = "9c8081785477032489293fef4d3a4fdf";

    //------------------服务协议--------------------
    public final static String SERVICE_AGREEMENTS_URL = "waitToBeAdd";

    //------------------分享链接--------------------
    public final static String SHARE_URL = "https://www.baidu.com";

    public final static String JINGLE_PWD_KEY = "jingle";

    public final static int ACTREQ_PICTURE = 99;
    public final static int ACTREQ_CAMERA = 100;
    public final static int ACTREQ_CLIP_AVATAR = 103;

    public final static String EX_USERID = "user_uuid";
    public final static String EX_USERNICK = "user_nick";

    public final static String EX_CMTY_ID = "commodity";
    public final static String EX_CTGY_TAG = "category";

}
