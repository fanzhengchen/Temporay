package com.hcb.jingle.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.BalanceVO;
import com.hcb.jingle.util.FormatUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class BalanceAdapter extends AbsFitAdapter {

    private ArrayList<BalanceVO> data;
    private int cellHeight = FormatUtil.pixOfDip(50);

    public BalanceAdapter(ArrayList<BalanceVO> data) {
        this.data = data;
    }


    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.cell_balance, null);
            view.setLayoutParams(new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, cellHeight));
            holder = new ViewHolder();
            ButterKnife.bind(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        fillData(holder, data.get(position));
        return view;
    }

    private void fillData(ViewHolder holder, BalanceVO balanceVO) {
        holder.name.setText(balanceVO.getName());
        holder.price.setText(balanceVO.getPrice());
    }

    class ViewHolder {
        @Bind(R.id.cell_balance_name)
        TextView name;
        @Bind(R.id.cell_balance_price)
        TextView price;
    }
}
