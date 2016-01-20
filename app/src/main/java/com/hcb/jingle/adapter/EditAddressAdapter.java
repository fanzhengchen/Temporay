package com.hcb.jingle.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.Consignee;
import com.hcb.jingle.biz.ActivitySwitcher;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class EditAddressAdapter extends AbsFitAdapter {

    private ArrayList<Consignee> data;
    private Activity activity;
    private LayoutInflater inflater;

    public EditAddressAdapter(Activity activity, ArrayList<Consignee> data) {
        this.data = data;
        this.activity = activity;
        this.inflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return (data == null ? 0 : data.size());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.cell_edit_address, null);
            holder = new ViewHolder();
            ButterKnife.bind(holder, view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.fillData(data.get(position));
        return view;
    }


    class ViewHolder {
        @Bind(R.id.cell_edit_address_consignee)
        TextView name;
        @Bind(R.id.cell_edit_address_address)
        TextView address;
        @Bind(R.id.cell_edit_address_phone_number)
        TextView phone;

        public void fillData(Consignee consignee) {
            name.setText(consignee.getName());
            address.setText(consignee.getAddress());
            phone.setText(consignee.getPhone());
        }
    }
}
