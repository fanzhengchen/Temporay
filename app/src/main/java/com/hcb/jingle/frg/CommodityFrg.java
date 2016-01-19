package com.hcb.jingle.frg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;

import butterknife.ButterKnife;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class CommodityFrg extends TitleFragment {
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString(GlobalConsts.EX_CMTY_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_commodity_detail,container,false);
        ButterKnife.bind(this, rootView);

        init();

        return rootView;
    }

    private void init() {
        act.hideNavi();
    }
}
