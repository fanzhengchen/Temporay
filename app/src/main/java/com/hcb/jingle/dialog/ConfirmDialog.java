package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.util.StringUtil;
import com.hcb.jingle.util.UiTool;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmDialog extends BaseDialog implements OnClickListener {

    public interface SureListener {
        public void onSure();
    }

    private SureListener listener;
    private CharSequence desc;

    public ConfirmDialog setDesc(final String desc) {
        if (!TextUtils.isEmpty(desc)) {
            this.desc = desc;
        }
        return this;
    }

    public ConfirmDialog setDesc(final CharSequence desc) {
        this.desc = desc;
        return this;
    }

    public ConfirmDialog setSureListener(final SureListener sure) {
        listener = sure;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.dlg_confirm, container, false);
        ButterKnife.bind(this, content);
        if (null != desc) {
            ((TextView) content.findViewById(R.id.dlg_desc)).setText(desc);
        }
        return content;
    }

    @OnClick({R.id.dlgbtn_left, R.id.dlgbtn_right})
    public void onClick(View v) {
        if (R.id.dlgbtn_right == v.getId()) {
            if (null != listener) {
                listener.onSure();
            }
        }
        dismiss();
    }
}
