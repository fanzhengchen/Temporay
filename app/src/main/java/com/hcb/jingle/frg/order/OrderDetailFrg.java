package com.hcb.jingle.frg.order;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.widget.RCTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/20.
 */
public class OrderDetailFrg extends TitleFragment {

    @Bind(R.id.frg_order_detail_look_up_order)
    RCTextView lookUpOrderTV;

    @Override
    public int getTitleId() {
        return R.string.order_detail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_order_detail, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.frg_order_detail_look_up_order)
    public void lookUpOrder() {
        ActivitySwitcher.startFragment(getActivity(), ReturnGoodsFrg.class);
    }
}
