package com.hcb.jingle.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcb.jingle.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<String> data;
    private Activity activity;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Activity activity) {
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.return_goods_single_img, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder h = (ViewHolder) holder;
        if (position + 1 < getItemCount()) {
            h.hiddenText();
        }
    }

    @Override
    public int getItemCount() {
        return (null == data ? 0 : data.size()) + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cell_return_goods_img)
        ImageView imageView;
        @Bind(R.id.cell_return_goods_text)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void hiddenText() {
            if (textView != null) {
                textView.setVisibility(View.GONE);
            }
        }
    }
}
