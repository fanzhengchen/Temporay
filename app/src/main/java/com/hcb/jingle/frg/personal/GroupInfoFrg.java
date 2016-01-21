package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hcb.jingle.R;
import com.hcb.jingle.frg.TitleFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/1/20.
 */
public class GroupInfoFrg extends TitleFragment {

    @Bind(R.id.list_view)
    ListView listView;

    @Override
    public int getTitleId() {
        return R.string.group_buying_message;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_list_view, container, false);
        return rootView;
    }
}
