package com.hcb.jingle.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.actlink.NaviRightDecorator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dyx on 2016/1/21.
 */
public class PaySuccessFrg extends TitleFragment {
    @Override
    public int getTitleId() {
        return R.string.pay_success;
    }

    @Bind(R.id.text_receiver)TextView text_receiver;
    @Bind(R.id.text_phone)TextView text_phone;
    @Bind(R.id.text_address)TextView text_address;
    @Bind(R.id.text_money)TextView text_money;
    @Bind(R.id.image_progress)TextView image_progress;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_pay_success,container,false);
        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init() {

    }

}
