package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.R;
import com.hcb.jingle.frg.TitleFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class AboutAppFrg extends TitleFragment {
    @Bind(R.id.version_number)
    TextView version;

    @Override
    public int getTitleId() {
        return R.string.about_app;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_about_app, container, false);
        ButterKnife.bind(this, rootView);
        loadData();
        return rootView;
    }

    private void loadData() {
        version.setText(GlobalBeans.getSelf().getAppInfo().getVersionName());
    }


}
