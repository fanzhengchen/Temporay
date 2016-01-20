package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;
import com.hcb.jingle.frg.TitleFragment;

/**
 * Created by Administrator on 2016/1/20.
 */
public class OrderDetailFrg extends TitleFragment {

    @Override
    public int getTitleId() {
        return R.string.order_detail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_order_detail, container, false);
        return rootView;
    }
}
