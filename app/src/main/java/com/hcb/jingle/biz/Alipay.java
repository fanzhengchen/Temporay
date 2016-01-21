package com.hcb.jingle.biz;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;
import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.loader.user.PayOrderFetcher;
import com.hcb.jingle.model.pay.PayOrderInBody;
import com.hcb.jingle.util.SignUtil;
import com.hcb.jingle.util.ToastUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

public class Alipay {

    private final ExecutorService executor;
    private final Activity act;
    private String orderNo;

    public Alipay(Activity act) {
        executor = GlobalBeans.getSelf().getExecutorService();
        this.act = act;
    }

    public void payFor(final String subject, final String desc, final int price) {
        new PayOrderFetcher().fetchAli(price, new AbsLoader.RespReactor<PayOrderInBody>() {
            @Override
            public void succeed(PayOrderInBody body) {
                orderNo = body.getMyOrderNo();
                callpay(getOrderInfo(subject, desc, price, orderNo));
            }

            @Override
            public void failed(String code, String reason) {
                ToastUtil.show(reason);
            }
        });
    }

    private void callpay(final String payInfo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String result = new PayTask(act).pay(payInfo, true);
                evtPaySucceed();
            }
        });
    }

    private void evtPaySucceed() {
        final GlobalBeans beans = GlobalBeans.getSelf();
        if (null != beans) {
            beans.getEventCenter().sendEvtWithKey(
                    EventCenter.EventType.EVT_PAY_SUCCEED,
                    GlobalConsts.EX_ORDER_NO, orderNo);
        }
    }

    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, int price, String orderNo) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + GlobalConsts.ALI_PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + GlobalConsts.ALI_SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        String fee = String.format(Locale.getDefault(), "%.2f", price / 100.0f);
        // 商品金额
        orderInfo += "&total_fee=" + "\"" + fee + "\"";

        // 服务器异步通知页面路径
//        orderInfo += "&notify_url=" + "\"" + NOTIFY_URL + "\"";

        // 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";
        orderInfo += "&payment_type=\"1\"";
        orderInfo += "&_input_charset=\"utf-8\"";
        orderInfo += "&it_b_pay=\"30m\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtil.sign(content, GlobalConsts.ALI_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
