package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.R;
import com.hcb.jingle.biz.Alipay;
import com.hcb.jingle.biz.EventCenter;
import com.hcb.jingle.biz.EventCenter.EventListener;
import com.hcb.jingle.biz.EventCenter.EventType;
import com.hcb.jingle.biz.WxPay;
import com.hcb.jingle.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentDlg extends BaseDialog implements EventListener {

    private int money;

    public PaymentDlg() {
        EventCenter eventCenter = GlobalBeans.getSelf().getEventCenter();
        eventCenter.registEvent(this, EventType.EVT_PAY_SUCCEED);
        eventCenter.registEvent(this, EventType.EVT_PAY_FAILED);
    }

    /**
     * @param money 单位：分
     */
    public PaymentDlg setMoney(int money) {
        this.money = money;
        return this;
    }

    @Bind(R.id.pay_group) RadioGroup payGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.dlg_payment, container, false);
        ButterKnife.bind(this, content);

        return content;
    }

    @OnClick(R.id.btn_cancel)
    void cancel() {
        dismiss();
    }

    @OnClick(R.id.btn_confirm)
    public void surePay(View v) {
        switch (payGroup.getCheckedRadioButtonId()) {
            case R.id.pay_balance:
                ToastUtil.show("余额支付");
                break;
            case R.id.pay_wx:
                ToastUtil.show("支付宝支付");
                aliPay();
                break;
            case R.id.pay_ali:
                ToastUtil.show("微信支付");
                wxPay();
                break;
        }
    }

    public void aliPay() {
        new Alipay(act).payFor("《葬花宝典》珍藏版", "全球限量8本，卖完退隐", 1);
    }

    public void wxPay() {
        new WxPay(act).payFor("《葬花宝典》珍藏版", "全球限量8本，卖完退隐", 1);
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        switch (e.type) {
            case EVT_PAY_SUCCEED:
                ToastUtil.show("支付成功");

                break;
            case EVT_PAY_FAILED:
                ToastUtil.show("支付失败");
                break;
        }
    }
}
