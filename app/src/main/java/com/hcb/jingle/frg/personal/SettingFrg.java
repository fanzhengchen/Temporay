package com.hcb.jingle.frg.personal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.TitleFragment;

import java.sql.Time;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.frg_setting_about)
    public void goToAbout() {
        ActivitySwitcher.startFragment(getActivity(), AboutAppFrg.class);
    }

    @OnClick(R.id.frg_setting_logout)
    public void logout() {

    }


}
