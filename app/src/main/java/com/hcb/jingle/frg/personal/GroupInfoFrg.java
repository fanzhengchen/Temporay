package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hcb.jingle.R;
import com.hcb.jingle.adapter.GroupInfoAdapter;
import com.hcb.jingle.bean.GroupInfo;
import com.hcb.jingle.frg.PtrListViewFrg;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.util.TimeUtil;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/1/20.
 */
public class GroupInfoFrg extends PtrListViewFrg {

    private GroupInfoAdapter adapter = null;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    @Override
    public int getTitleId() {
        return R.string.group_buying_message;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        super.setUpPtr();
        listView.setDividerHeight(0);
        loadData();
        return rootView;
    }

    private void loadData() {
        ArrayList<GroupInfo> data = new ArrayList<>();
        for (int i = 0; i < 15; ++i) {
            GroupInfo info = new GroupInfo();
            info.setTime(TimeUtil.getCurrentTime());
            info.setGoodsAmount("444");
            info.setGoodsSize("11");
            info.setGoodsName("advert");
            info.setGroupStatus("success");
            data.add(info);
        }
        adapter = new GroupInfoAdapter(getActivity(), data);
        listView.setAdapter(adapter);
    }


}
