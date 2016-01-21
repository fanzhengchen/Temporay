package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;
import com.hcb.jingle.frg.TitleFragment;

import java.sql.Time;

/**
 * Created by Administrator on 2016/1/20.
 */
public class SettingFrg extends TitleFragment {

    @Override
    public int getTitleId() {
        return R.string.setting;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_setting, container, false);
        return rootView;
    }

}
