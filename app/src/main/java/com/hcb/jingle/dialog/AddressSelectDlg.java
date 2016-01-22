package com.hcb.jingle.dialog;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hcb.jingle.R;
import com.hcb.jingle.bean.Consignee;
import com.hcb.jingle.frg.order.AddressManageFrg;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dyx on 2016/1/21.
 */
public class AddressSelectDlg extends BaseDialog {
    private int curId;
    private AddressManageFrg addressManageFrg;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    public interface AddressSelectListener {
        void selectAddress(String name, String phone, String address);
    }

    private AddressSelectListener listener;

    public AddressSelectListener getListener() {
        return listener;
    }

    public AddressSelectDlg setListener(AddressSelectListener listener) {
        this.listener = listener;
        return this;
    }


    @Bind(R.id.linear_address)
    LinearLayout linear_addresse;

//    @Bind(R.id.relative_add)RelativeLayout relative_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlg_address_select, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = getChildFragmentManager();
        addressManageFrg = new AddressManageFrg();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.addressFrgContainer, addressManageFrg);
        transaction.addToBackStack(null);
        transaction.commit();

        return view;
    }

//    private void init() {
//        initLinearAddress();
//    }
//
//    private void initLinearAddress() {
//        for (int i = 0;i<3;i++){
//            View view = View.inflate(getActivity(),R.layout.cell_address,null);
//            view.setId(i);
//            if(i==0){
//                ((RadioButton)view.findViewById(R.id.radio)).setChecked(true);
//                curId = 1;
//            }
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectRadio(v);
//                }
//            });
//            linear_addresse.addView(view);
//            linear_addresse.addView(getUnderLine());
//        }
//    }

//    private void selectRadio(View v) {
//        for (int i = 1;i<6;i = i+2){
//            if(linear_addresse.getChildAt(i).getId() != v.getId()){
//                ((RadioButton)linear_addresse.getChildAt(i).findViewById(R.id.radio)).setChecked(false);
//            }else{
//                ((RadioButton)linear_addresse.getChildAt(i).findViewById(R.id.radio)).setChecked(true);
//                curId = i;
//            }
//        }
//    }
//
//    private View getUnderLine() {
//        View view  = new View(getActivity());
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.height = 1;
//        view.setLayoutParams(lp);
//        view.setBackgroundResource(R.color.line_color);
//
//        return view;
//    }

    @OnClick({R.id.relative_dlg, R.id.image_close, R.id.relative_content})
    void close(View v) {
        switch (v.getId()) {
            case R.id.relative_dlg:
            case R.id.image_close:
                dismiss();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.text_confirm)
    void confirmAddress() {
//        View v = linear_addresse.getChildAt(curId);
//        TextView name = (TextView) v.findViewById(R.id.text_name);
//        TextView phone = (TextView) v.findViewById(R.id.text_phone);
//        TextView address = (TextView) v.findViewById(R.id.text_address);
//        listener.selectAddress(name.getText().toString(),
//                phone.getText().toString(),
//                address.getText().toString());
        Consignee consignee = addressManageFrg.getDefaultConsignee();
        dismiss();
    }

}
