package com.hcb.jingle;

public class GlobalConsts {

    public static final boolean DEBUG = true;

    private static final String HOST_DEBUG = "http://waitToBeAdd/index.php";
    private static final String HOST_RELEASE = "http://waitToBeAdd/index.php";
    public static final String HOST_IMAGE = "http://honey.waibao.help/honey/service_image/index.php";
    public static final String HOST = DEBUG ? HOST_DEBUG : HOST_RELEASE;
    public static final String UPLOAD_IMAGE = "http://121.199.47.167/honey/service_image/index.php";

    //------------------友盟统计分享-------------------
    private static final String UMENG_KEY_DEBUG = "waitToBeAdd";
    private static final String UMENG_KEY_RELEASE = "waitToBeAdd";
    public static final String UMENG_APPKEY = DEBUG ? UMENG_KEY_DEBUG : UMENG_KEY_RELEASE;

    //------------------微信分享-------------------
    private static final String WX_ID_DEBUG = "waitToBeAdd";
    private static final String WX_SECRET_DEBUG = "waitToBeAdd";
    private static final String WX_ID_RELEASE = "waitToBeAdd";
    private static final String WX_SECRET_RELEASE = "waitToBeAdd";
    public static final String WX_ID = DEBUG ? WX_ID_DEBUG : WX_ID_RELEASE;
    public static final String WX_SECRET = DEBUG ? WX_SECRET_DEBUG : WX_SECRET_RELEASE;

    //------------------服务协议--------------------
    public final static String SERVICE_AGREEMENTS_URL = "waitToBeAdd";

    //------------------分享链接--------------------
    public final static String SHARE_URL = "waitToBeAdd";

    public final static String JINGLE_PWD_KEY = "jingle";

    public final static int ACTREQ_PICTURE = 99;
    public final static int ACTREQ_CAMERA = 100;
    public final static int ACTREQ_CLIP_AVATAR = 103;

    public final static String EX_USERID = "user_uuid";
    public final static String EX_USERNICK = "user_nick";

    public final static String EX_CMTY_ID = "commodity";
    public final static String EX_CTGY_TAG = "category";

}
