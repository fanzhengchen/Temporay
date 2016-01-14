package com.hcb.jingle.dialog;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.jingle.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagePickerDialog extends BaseDialog {

    public interface HandleImage {
        void onPick();

        void onCamera();
    }

    private Bitmap bitmap;
    private HandleImage handleImage;

    public ImagePickerDialog setHandleImage(HandleImage handleImage) {
        this.handleImage = handleImage;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dlg_image_picker, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.dlg_whole, R.id.dlg_frame, R.id.btn_cancel})
    void cancel(View v) {
        switch (v.getId()) {
            case R.id.dlg_whole:
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
    }

    private int picked = 0;

    @OnClick({R.id.take_photo, R.id.image_gallery})
    void onPick(View v) {
        picked = v.getId();
        dismiss();
    }

    @Override
    protected void willDismiss() {
        switch (picked) {
            case R.id.take_photo:
                handleImage.onCamera();
                break;
            case R.id.image_gallery:
                handleImage.onPick();
                break;
        }
    }
}
