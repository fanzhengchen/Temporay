package com.hcb.jingle.frg.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.order.MyOrdersFrg;

import java.util.List;

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


    @OnClick(R.id.main_personal_order)
    public void goToMyOrders() {
        ActivitySwitcher.startFragment(getActivity(), MyOrdersFrg.class);
    }



}
