package com.hcb.jingle;

public class GlobalConsts {

    public static final boolean DEBUG = true;

    private static final String HOST_DEBUG = "http://b2.waibao.help/dingdang/index.php/";
    private static final String HOST_RELEASE = "http://waitToBeAdd/index.php";
    public static final String HOST_IMAGE = "http://honey.waibao.help/honey/service_image/index.php";
    public static final String HOST = DEBUG ? HOST_DEBUG : HOST_RELEASE;
    public static final String UPLOAD_IMAGE = "http://121.199.47.167/honey/service_image/index.php";

    //------------------友盟统计分享-------------------
    private static final String UMENG_KEY_DEBUG = "5698d68e67e58ed3c400281e";
    private static final String UMENG_KEY_RELEASE = "56a0d0f3e0f55aa4df001fc9";
    public static final String UMENG_APPKEY = DEBUG ? UMENG_KEY_DEBUG : UMENG_KEY_RELEASE;

    //------------------微信分享 & Pay -------------------
    private static final String WX_ID_DEBUG =
            //"wx027975c909c0604e";//Nixi
            "wxe6c31febbd05d58d";//huancheclub
    private static final String WX_SECRET_DEBUG =
            //"1ae2cd63192bd304e10869eeeed1ce8f";//Nixi
            "1ae2cd63192bd304e10869eeeed1ce8f";//huancheclub
    private static final String WX_ID_RELEASE = "waitToBeAdd";
    private static final String WX_SECRET_RELEASE = "waitToBeAdd";
    public static final String WX_ID = DEBUG ? WX_ID_DEBUG : WX_ID_RELEASE;
    public static final String WX_SECRET = DEBUG ? WX_SECRET_DEBUG : WX_SECRET_RELEASE;
    public static final String WXPAY_KEY =
            "efbbec4b7b7440e339f1192d0733082b";//huancheclub
//            "881310896b75ddf7bd544acaa9bc052e";//Nixi

    //------------------QQ授权-------------------
    public static final String QQ_ID = "1105117308";
    public static final String QQ_SECRET = "oj8BdgVB4jfdVrmg";

    //------------------微博授权-------------------
    public static final String WB_ID = "907338238";
    public static final String WB_SECRET = "7956ca472062f0e78bff23d2e789ea0d";

    //------------------支付宝支付-------------------
    // todo 现在是测试，务必记得改成正式，改后删掉本todo
    public final static String ALI_PARTNER = "2088121142769816";
    public final static String ALI_SELLER = "bomojiaoyu@126.com";
    public final static String ALI_PRIVATE =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAK2CA+mKDylZEwvDPNT7ekc1eY2dbhhfyux/ddrmT8tOhvAvToaw2hsePIzrhMWZqcAEIK2GGewwuNVZLRacLNeRbHplHvhSi7xCKXk/sIAc5UR9upYAiojv060teZ2SQRnBtA1zsCAaG94RQNSiKNI+u6Uyq3NrCUTfxICpZKYVAgMBAAECgYA2fN8Hp0CNpphxmDVxWSwUUrnCfE8HvoV2caysOqmSWi3MHb8WU1GbNIqlYo0GlPQXBfTRKYzYfJB/B4s+T3tRDotHJ3R9Nxz6FMM17Q0oPxJ81tAJp5sYXvxfyvAhLSKUEm+CNs4Vtvdw0rrT4bvi+ylVRtBeGrM6B3u+3G1g/QJBAOBmvKWHDwLlZNmpP2Bb+S/3dJLkpD4NxynfaKfNt7gpMUaO4oPsYBZdwAq/dCqyaGQP3IpLoSu5oFkAm0U2L7MCQQDF8KlT1E4ec9aMBaWgDb9U+0v6DLsYo/0d+Sin2pdV+RhR52B3+gag1BlYqsPbYpotgjIpGzxMZ6cUeB2P6a8XAkA+bHX3+g0z+gpG/mzx6kP0kvrrzgzeckjAHjTn0TNVWKgrf54FouDL+AJvOvke04h0pXDtS8Km/jlb6O5In50/AkBVGKzDRF+9SdGv6vGUiLR8BXlZa8W3xzCXhTmMSDaNmIRzGcTob44P8H9hFjETjSV5cSK9d18J/fnfm/AhobZLAkAMQ5SuwY5blr9PjcIDMsT9zupMpndc9bdQNu02ojT6ZkfziNJpHRVeFvg9bTBA4LKI++8hAqMKYagU+uHG/tes";

    //------------------服务协议--------------------
    public final static String SERVICE_AGREEMENTS_URL = "waitToBeAdd";

    //------------------分享链接--------------------
    public final static String SHARE_URL = "https://www.baidu.com";

    public final static String JINGLE_PWD_KEY = "jingle";
    public final static String RMB = "¥";

    public final static int ACTREQ_PICTURE = 99;
    public final static int ACTREQ_CAMERA = 100;
    public final static int ACTREQ_CLIP_AVATAR = 103;

    public final static String EX_USERID = "user_uuid";
    public final static String EX_USERNICK = "user_nick";

    public final static String EX_CMTY_ID = "commodity";
    public final static String EX_CTGY_TAG = "category";
    public final static String EX_ORDER_NO = "order_no";

}
