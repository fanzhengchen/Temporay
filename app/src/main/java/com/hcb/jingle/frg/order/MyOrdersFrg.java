package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;
import com.hcb.jingle.adapter.MyOrdersAdapter;
import com.hcb.jingle.bean.MyOrder;
import com.hcb.jingle.frg.PtrListViewFrg;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/19.
 */
public class MyOrdersFrg extends PtrListViewFrg {

    private MyOrdersAdapter adapter = null;
    private ArrayList<MyOrder> orders;

    @Override
    public int getTitleId() {
        return R.string.my_orders;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        super.setUpPtr();
        loadData();
        listView.setDividerHeight(0);
        return rootView;
    }

    @Override
    protected int topSpaceHeight() {
        return 2;
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
