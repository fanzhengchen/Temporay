package com.hcb.jingle.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.bean.CommodityVO;

import java.util.List;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class CommodityAdapter extends PagableAdapter {

    private List<CommodityVO> data;

    public CommodityAdapter(Activity act, List<CommodityVO> data) {
        super(act);
        this.data = data;
    }

    @Override
    protected int getRealCount() {
        return null == data ? 0 : data.size();
    }

    class ViewHolder {
    }

    @Override
    protected View makeItemView(int position, ViewGroup parent) {
        return null;
    }

    @Override
    protected void bandData(int position, View convertView) {

    }

}
