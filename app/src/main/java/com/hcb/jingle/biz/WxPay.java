package com.hcb.jingle.biz;

import android.content.Context;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.bean.WxOrderVO;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WxPay {

    public static void callPay(final Context ctx, final WxOrderVO order) {
        final IWXAPI wxApi = WXAPIFactory.createWXAPI(ctx, null);
        wxApi.registerApp(GlobalConsts.WX_ID);
        PayReq req = new PayReq();
        req.appId = order.getAppid();
        req.partnerId = order.getPartnerid();
        req.prepayId = order.getPrepayid();
        req.packageValue = order.getPkg();
        req.nonceStr = order.getNoncestr();
        req.timeStamp = order.getTimestamp();
        req.sign = order.getSign();
        wxApi.sendReq(req);
    }
}
