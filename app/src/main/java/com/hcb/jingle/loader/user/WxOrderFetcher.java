package com.hcb.jingle.loader.user;

import com.hcb.jingle.loader.base.BasePostLoader;
import com.hcb.jingle.model.pay.WxOrderInBody;
import com.hcb.jingle.model.pay.WxOrderOutBody;

public class WxOrderFetcher extends BasePostLoader<WxOrderOutBody, WxOrderInBody> {

    private final static String PATH = "pay/WeiXinUnifiedOrder";

    /**
     * @param money 单位分
     */
    public void fetch(final int money, final RespReactor reactor) {
        super.load(genUrl(PATH), new WxOrderOutBody().setMoney(money), reactor);
    }

}
