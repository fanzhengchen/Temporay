package com.hcb.jingle.frg.main;

import android.os.Bundle;
import android.view.View;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.order.AddressManageFrg;
import com.hcb.jingle.frg.order.MyOrdersFrg;
import com.hcb.jingle.frg.personal.GroupInfoFrg;
import com.hcb.jingle.frg.personal.MyBalanceFrg;
import com.hcb.jingle.frg.personal.MyInfoFrg;
import com.hcb.jingle.frg.personal.SettingFrg;

import butterknife.Bind;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class PersonalFrg extends CachableFrg {
    @Bind({
            R.id.main_personal_info,
            R.id.main_personal_balance,
            R.id.address_manage,
            R.id.main_personal_order,
            R.id.main_personal_group_info,
            R.id.main_personal_setting})
    View[] views;

    Class[] classes = {
            MyInfoFrg.class,
            MyBalanceFrg.class,
            AddressManageFrg.class,
            MyOrdersFrg.class,
            GroupInfoFrg.class,
            SettingFrg.class
    };

    @Override
    protected int rootLayout() {
        return R.layout.main_personal;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        handleSecondaryFrg();
    }

    private void handleSecondaryFrg() {
        for (int i = 0; i < views.length; ++i) {
            final int j = i;
            views[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivitySwitcher.startFragment(getActivity(), classes[j]);
                }
            });
        }
    }


}
