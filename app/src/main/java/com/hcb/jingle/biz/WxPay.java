package com.hcb.jingle.biz;

import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.bean.WxOrderVO;
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.loader.user.PayOrderFetcher;
import com.hcb.jingle.model.pay.PayOrderInBody;
import com.hcb.jingle.util.ToastUtil;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WxPay {

    private final Context ctx;
    private String orderNo;

    public WxPay(final Context ctx) {
        this.ctx = ctx;
    }

    /**
     * @param price 单位：分RMB
     */
    public void payFor(final int price) {
        new PayOrderFetcher().fetchWx(price, new AbsLoader.RespReactor<PayOrderInBody>() {
            @Override
            public void succeed(PayOrderInBody body) {
                orderNo = body.getMyOrderNo();
                Log.w("Wxpay", "orderNo:" + JSON.toJSONString(body.getWxOrder()));
                callPay(body.getWxOrder());
            }

            @Override
            public void failed(String code, String reason) {
                GlobalBeans.getSelf().getEventCenter().sendType(
                        EventCenter.EventType.EVT_PAY_FAILED
                );
                ToastUtil.show("生成微信订单失败！");
            }
        });
    }

    private void callPay(final WxOrderVO order) {
        final IWXAPI wxApi = WXAPIFactory.createWXAPI(ctx, GlobalConsts.WX_ID);
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
