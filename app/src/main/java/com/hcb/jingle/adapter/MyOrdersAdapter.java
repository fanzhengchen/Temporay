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

import com.hcb.jingle.R;
import com.hcb.jingle.bean.MyOrder;

import java.util.ArrayList;

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
    }

    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.cell_my_order, null);
        } else {

        }
        return view;
    }

    private int getColor(int resId) {
        return ContextCompat.getColor(activity, resId);
    }
}
