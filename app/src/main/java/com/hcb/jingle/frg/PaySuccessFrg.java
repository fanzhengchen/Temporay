package com.hcb.jingle.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;

/**
 * Created by dyx on 2016/1/21.
 */
public class PaySuccessFrg extends TitleFragment {
    @Override
    public int getTitleId() {
        return R.string.pay_success;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_pay_success,container,false);
        return view;
    }
}
