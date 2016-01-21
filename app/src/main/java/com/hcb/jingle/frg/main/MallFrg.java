package com.hcb.jingle.frg.main;

import android.os.Bundle;
import android.view.View;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.ShareHelper;
import com.hcb.jingle.biz.WxPay;
import com.hcb.jingle.dialog.PaymentDlg;
import com.hcb.jingle.loader.base.AbsLoader;
import com.hcb.jingle.loader.user.WxOrderFetcher;
import com.hcb.jingle.model.pay.WxOrderInBody;

import butterknife.OnClick;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class MallFrg extends CachableFrg {

    @Override
    protected int rootLayout() {
        return R.layout.main_mall;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    /////////////////////////////////////
    /// test share
    @OnClick(R.id.btn_share)
    public void share(View view) {
        ShareHelper.shareApp(getActivity());
    }

    /////////////////////////////////////
    /// test pay
    @OnClick(R.id.btn_paytest)
    public void testPay(View view) {
        new PaymentDlg().setMoney(100).show(getFragmentManager(), "pay");
    }

}
