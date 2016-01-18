package com.hcb.jingle.model.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.hcb.jingle.bean.WxOrderVO;
import com.hcb.jingle.model.base.InBody;

public class WxOrderInBody extends InBody {
    private WxOrderVO wxOrder;
    private String myOrderNo;

    @JSONField(name = "out_trade_no")
    public String getMyOrderNo() {
        return myOrderNo;
    }

    @JSONField(name = "out_trade_no")
    public void setMyOrderNo(String myOrderNo) {
        this.myOrderNo = myOrderNo;
    }

    @JSONField(name = "payload")
    public WxOrderVO getWxOrder() {
        return wxOrder;
    }

    @JSONField(name = "payload")
    public void setWxOrder(WxOrderVO wxOrder) {
        this.wxOrder = wxOrder;
    }

}
