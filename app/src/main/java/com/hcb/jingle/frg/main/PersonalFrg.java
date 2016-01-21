package com.hcb.jingle.frg.main;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.AddressManageFrg;
import com.hcb.jingle.frg.order.MyOrdersFrg;
import com.hcb.jingle.frg.personal.BalanceFrg;
import com.hcb.jingle.frg.personal.MyInfoFrg;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class PersonalFrg extends CachableFrg {

    @Bind(R.id.main_personal_order)
    LinearLayout myOrders;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int rootLayout() {
        return R.layout.main_personal;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @OnClick(R.id.main_personal_info)
    public void goToMyInfo() {
        ActivitySwitcher.startFragment(getActivity(), MyInfoFrg.class);
    }

    @OnClick(R.id.main_personal_balance)
    public void goToMyBalance() {
        ActivitySwitcher.startFragment(getActivity(), BalanceFrg.class);
    }

    @OnClick(R.id.main_personal_order)
    public void goToMyOrders() {
        ActivitySwitcher.startFragment(getActivity(), MyOrdersFrg.class);
    }

    @OnClick(R.id.address_manage)
    public void manageAddress() {
        ActivitySwitcher.startFragment(getActivity(), AddressManageFrg.class);
    }
}
