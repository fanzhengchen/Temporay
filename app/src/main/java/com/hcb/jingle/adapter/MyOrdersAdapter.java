package com.hcb.jingle.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.MyOrder;
import com.hcb.jingle.bean.MyOrderViewHolder;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.biz.OrderManager;
import com.hcb.jingle.frg.order.OrderDetailFrg;
import com.hcb.jingle.widget.RCTextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/19.
 */
public class MyOrdersAdapter extends AbsFitAdapter {
    private ArrayList<MyOrder> data;
    private Activity activity;
    private LayoutInflater inflater;
    private Resources resources;

    public MyOrdersAdapter(Activity activity) {
        this.activity = activity;
        this.inflater = (LayoutInflater.from(activity));
        this.data = new ArrayList<>();
    }

    public void appendData(ArrayList<MyOrder> orders) {
        this.data.addAll(orders);
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MyOrderViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.cell_my_order, null);
            holder = new MyOrderViewHolder();
            ButterKnife.bind(holder, view);
            view.setTag(holder);
        } else {
            holder = (MyOrderViewHolder) view.getTag();
        }

        OrderManager.fillData(data.get(position), holder, position);
        holder.getLookUp().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.startFragment(activity, OrderDetailFrg.class);
            }
        });
        return view;
    }

}
