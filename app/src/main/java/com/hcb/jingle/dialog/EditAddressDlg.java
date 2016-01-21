package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.util.StringUtil;
import com.hcb.jingle.util.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/1/21.
 */
public class EditAddressDlg extends BaseDialog {

    @Bind(R.id.dlg_edit_address_confirm)
    TextView confirm;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.phoneNumber)
    EditText phone;
    @Bind(R.id.address)
    EditText address;

    public interface EditListener {
        public void onConfirm(EditText name, EditText phone, EditText address);
    }

    private View rootView;
    private EditListener listener;

    public void setListener(EditListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dlg_edit_address, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.dlg_edit_address_confirm)
    public void confirmDismiss() {
        if (listener != null) {

            if (StringUtil.isEmpty(name.getText().toString())) {
                ToastUtil.show("姓名不能为空");
                return;
            }

            if (StringUtil.isEmpty(phone.getText().toString())) {
                ToastUtil.show("姓名不能为空");
                return;
            }

            if (StringUtil.isEmpty(address.getText().toString())) {
                ToastUtil.show("地址不能为空");
                return;
            }

            listener.onConfirm(name, phone, address);
        }
        dismiss();
    }

    @OnClick(R.id.exit)
    public void exit() {
        dismiss();
    }
}
