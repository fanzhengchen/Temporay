package com.hcb.jingle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.bean.GroupInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/20.
 */
public class GroupInfoAdapter extends AbsFitAdapter {
    private ArrayList<GroupInfo> data;
    private Activity activity;
    private LayoutInflater inflater;

    public GroupInfoAdapter(Activity activity, ArrayList<GroupInfo> data) {
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public int getCount() {
        return (data == null) ? 0 : data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
