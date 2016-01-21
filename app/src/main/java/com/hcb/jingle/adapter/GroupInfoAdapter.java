package com.hcb.jingle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.GroupInfo;
import com.hcb.jingle.util.FormatUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class GroupInfoAdapter extends AbsFitAdapter {
    private ArrayList<GroupInfo> data;
    private Activity activity;
    private LayoutInflater inflater;
    private int cellHeight = FormatUtil.pixOfDip(180);

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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.cell_group_info, null);
            view.setLayoutParams(new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, cellHeight));
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.fill(data.get(position));
        return view;
    }

    class ViewHolder {
        @Bind(R.id.cell_group_info_goods_amount)
        TextView amount;
        @Bind(R.id.cell_group_info_goods_name)
        TextView name;
        @Bind(R.id.cell_group_info_size)
        TextView size;
        @Bind(R.id.cell_group_info_status)
        TextView status;
        @Bind(R.id.cell_group_info_time)
        TextView time;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }

        public void fill(GroupInfo info) {
            time.setText(info.getTime());
            name.setText(info.getGoodsName());
            size.setText(info.getGoodsSize());
            status.setText(info.getGoodsSize());
            amount.setText(info.getGoodsAmount());
        }

    }
}
