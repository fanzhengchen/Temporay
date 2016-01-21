package com.hcb.jingle.model.pay;

import com.alibaba.fastjson.annotation.JSONField;
import com.hcb.jingle.model.base.OutBody;

public class PayOrderOutBody extends OutBody {

    private int money;  // total_fee

    @JSONField(name = "total_fee")
    public int getMoney() {
        return money;
    }

    @JSONField(name = "total_fee")
    public PayOrderOutBody setMoney(int money) {
        this.money = money;
        return this;
    }

}
