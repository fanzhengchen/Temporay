package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
            radioGroup.addView(createCell(R.layout.cell_radio_button, cellHeight));
            radioGroup.addView(createCell(R.layout.under_line, dividerHeight));
            final View view = createCell(R.layout.cell_edit_address, cellHeight);
            final ViewHolder holder = new ViewHolder();
            ButterKnife.bind(holder, view);
            cells.add(view);
            right.addView(view);
            view.setTag(holder);
            right.addView(createCell(R.layout.under_line, dividerHeight));
            consignees.add(new Consignee());
            final int index = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditAddressDlg dlg = new EditAddressDlg();
                    dlg.setListener(new EditAddressDlg.EditListener() {
                        @Override
                        public void onConfirm(EditText name, EditText phone, EditText address) {
                            Consignee consignee = consignees.get(index);
                            consignee.setPhone(phone.getText().toString());
                            consignee.setName(name.getText().toString());
                            consignee.setAddress(address.getText().toString());
                            holder.name.setText(name.getText().toString());
                            holder.address.setText(address.getText().toString());
                            holder.phone.setText(phone.getText().toString());
                        }
                    });
                    dlg.show(getFragmentManager(), "EditAddress");
                }
            });
        }
        return rootView;
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
}
