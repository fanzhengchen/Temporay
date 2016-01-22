package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.Consignee;
import com.hcb.jingle.dialog.EditAddressDlg;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.util.FormatUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/20.
 */
public class AddressManageFrg extends TitleFragment {

    @Bind(R.id.parent)
    LinearLayout parent;
    @Bind(R.id.frg_address_manage_radio_group)
    RadioGroup radioGroup;
    @Bind(R.id.frg_address_manage_right)
    LinearLayout right;

    private int cellHeight = FormatUtil.pixOfDip(80);
    private int dividerHeight = FormatUtil.pixOfDip(1);
    private View footer;
    private int defaultIndex = 0;
    private ArrayList<View> cells;
    private ArrayList<Consignee> consignees;

    @Override
    public int getTitleId() {
        return R.string.address_manage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_address_manage, container, false);
        ButterKnife.bind(this, rootView);
        footer = View.inflate(getContext(), R.layout.cell_add_new_address, null);
        footer.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        cells = new ArrayList<>();
        consignees = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            View newAddress = createNewAddress();
            addListener(i, newAddress);
            addCell(i, newAddress);
        }
        return rootView;
    }

    private View createNewAddress() {
        View view = createCell(R.layout.cell_edit_address, cellHeight);
        final ViewHolder holder = new ViewHolder();
        ButterKnife.bind(holder, view);
        cells.add(view);
        view.setTag(holder);
        return view;
    }

    private void addCell(final int i, View addressView) {
        radioGroup.addView(createCell(R.layout.cell_radio_button, cellHeight));
        radioGroup.addView(createCell(R.layout.under_line, dividerHeight));
        right.addView(addressView);
        right.addView(createCell(R.layout.under_line, dividerHeight));
    }

    private void addListener(final int i, final View addressView) {
        View edit = (TextView) addressView.findViewById(R.id.edit_address);
        final ViewHolder holder = (ViewHolder) addressView.getTag();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDlg(i, addressView, false);
            }
        });
    }

    private void showDlg(final int i, final View view, final boolean isNew) {
        EditAddressDlg dlg = new EditAddressDlg();
        final ViewHolder holder = (ViewHolder) view.getTag();
        if (!isNew) {
            dlg.setData(
                    holder.name.getText().toString(),
                    holder.phone.getText().toString(),
                    holder.address.getText().toString());
        }
        dlg.setListener(new EditAddressDlg.EditListener() {
            @Override
            public void onConfirm(String name, String phone, String address) {
                Consignee consignee = new Consignee();
                consignee.setPhone(phone);
                consignee.setName(name);
                consignee.setAddress(address);
                consignees.add(consignee);
                holder.name.setText(name);
                holder.address.setText(address);
                holder.phone.setText(phone);
                if (isNew) {
                    addCell(i, view);
                }
            }
        });
        dlg.show(getFragmentManager(), "EditAddress");
    }


    View createCell(int layout, int height) {
        View view = View.inflate(getContext(), layout, null);
        view.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        return view;
    }

    class ViewHolder {
        @Bind(R.id.cell_edit_address_consignee)
        TextView name;
        @Bind(R.id.cell_edit_address_phone_number)
        TextView phone;
        @Bind(R.id.cell_edit_address_address)
        TextView address;
    }

    @OnClick(R.id.add_new_address)
    public void addAddress() {
        final int i = consignees.size();
        View addressView = createNewAddress();
        addListener(i, addressView);
        showDlg(i, addressView, true);

    }

    public Consignee getDefaultConsignee() {
        if (consignees == null || consignees.size() == 0) {
            return null;
        }
        return consignees.get(defaultIndex);
    }
}
