package com.hcb.jingle.biz;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/21.
 */
public abstract class ViewHolder<T> {

    protected T object;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);

    }

    public void fillData(T object) {
        this.object = object;
    }
}
