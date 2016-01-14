package com.hcb.jingle.frg.main;

import android.os.Bundle;

import com.hcb.jingle.R;

public class PersonalFrg extends CachableFrg {

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

}
