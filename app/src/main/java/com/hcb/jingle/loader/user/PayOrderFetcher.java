package com.hcb.jingle.loader.user;

import com.hcb.jingle.loader.base.BasePostLoader;
import com.hcb.jingle.model.pay.PayOrderInBody;
import com.hcb.jingle.model.pay.PayOrderOutBody;

public class PayOrderFetcher extends BasePostLoader<PayOrderOutBody, PayOrderInBody> {

    private final static String PATH_WX = "pay/WeiXinUnifiedOrder";
    private final static String PATH_ALI = "pay/AliUnifiedOrder";

    /**
     * @param money 单位分
     */
    public void fetchWx(final int money, final RespReactor reactor) {
        fetch(PATH_WX, money, reactor);
    }

    /**
     * @param money 单位分
     */
    public void fetchAli(final int money, final RespReactor reactor) {
        fetch(PATH_ALI, money, reactor);
    }

    private void fetch(final String path, final int money, final RespReactor reactor) {
        super.load(genUrl(path), new PayOrderOutBody().setMoney(money), reactor);
    }
}
