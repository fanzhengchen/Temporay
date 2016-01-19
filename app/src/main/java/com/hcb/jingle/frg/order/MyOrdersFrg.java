package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hcb.jingle.R;
import com.hcb.jingle.adapter.MyOrdersAdapter;
import com.hcb.jingle.bean.MyOrder;
import com.hcb.jingle.frg.TitleFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/1/19.
 */
public class MyOrdersFrg extends TitleFragment {

    private MyOrdersAdapter adapter = null;
    private ArrayList<MyOrder> orders;

    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.frg_my_order_ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    @Override
    public int getTitleId() {
        return R.string.my_orders;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_my_order, container, false);
        ButterKnife.bind(this, rootView);
        setUpPtr();
        loadData();
        return rootView;
    }

    private void setUpPtr() {
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

    private void loadData() {
        adapter = new MyOrdersAdapter(act);
        orders = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            orders.add(new MyOrder());
        }
        adapter.appendData(orders);
        listView.setAdapter(adapter);
    }

}
