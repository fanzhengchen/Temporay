package com.hcb.jingle.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.Consignee;
import com.hcb.jingle.util.FormatUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/1/20.
 */
public class EditAddressAdapter extends AbsFitAdapter {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private int cellHeight = FormatUtil.pixOfDip(80);
    private ArrayList<Consignee> data;
    private Activity activity;
    private LayoutInflater inflater;
    private int defaultNumber;
    private RadioButton defaultButton;

    public EditAddressAdapter(Activity activity, ArrayList<Consignee> data) {
        this.data = data;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (data == null ? 0 : data.size());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(parent.getContext(), R.layout.cell_edit_address, null);
            view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, cellHeight));
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.setPosition(position);
        holder.fillData(data.get(position));
        return view;
    }


    class ViewHolder extends com.hcb.jingle.biz.ViewHolder<Consignee> {
        @Bind(R.id.cell_edit_address_consignee)
        TextView name;
        @Bind(R.id.cell_edit_address_address)
        TextView address;
        @Bind(R.id.cell_edit_address_phone_number)
        TextView phone;
//        @Bind(R.id.cell_edit_address_radio_button)
//        RadioButton button;
        private int position;

        public ViewHolder(View view) {
            super(view);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public void fillData(Consignee consignee) {
            super.fillData(consignee);
            name.setText(consignee.getName());
            address.setText(consignee.getAddress());
            phone.setText(consignee.getPhone());
//            if (consignee.isDefault()) {
//                button.setChecked(true);
//                defaultButton = button;
//                defaultNumber = position;
//            } else {
//                button.setChecked(false);
//            }
        }

//        @OnClick(R.id.cell_edit_address_radio_button)
//        public void select(RadioButton radioButton) {
//            button.setChecked(!object.isDefault());
//            object.setIsDefault(button.isChecked());
//
//            if (defaultNumber >= 0) {
//                defaultButton.setChecked(false);
//            }
//
//            if (object.isDefault()) {
//                defaultNumber = this.position;
//                defaultButton = radioButton;
//            } else {
//                defaultNumber = -1;
//            }
//            log.debug("FUCK {}", button.isChecked());
////            defaultNumber = this.position;
////            notifyDataSetChanged();
//        }
    }
}
