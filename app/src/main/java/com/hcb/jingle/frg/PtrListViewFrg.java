package com.hcb.jingle.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hcb.jingle.R;
import com.hcb.jingle.util.FormatUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/1/21.
 */
public class PtrListViewFrg extends TitleFragment {

    @Bind(R.id.list_view)
    protected ListView listView;
    @Bind(R.id.ptrFrameLayout)
    protected PtrFrameLayout ptrFrameLayout;
    @Bind(R.id.frg_list_view_parent)
    protected LinearLayout parent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_list_view, container, false);
        ButterKnife.bind(this, rootView);
        View view = new View(getContext());
        view.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, topSpaceHeight()
        ));
        listView.addHeaderView(view);
        return rootView;
    }

    protected int topSpaceHeight() {
        return FormatUtil.pixOfDip(10);
    }

    protected void setUpPtr() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.refreshComplete();
            }
        });
    }

}
