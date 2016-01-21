package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.jingle.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dyx on 2016/1/20.
 */
public class GroupBuyTypeDlg extends BaseDialog{
    public interface GroupBuyTypeListener{
        void selectType(String type);
    }

    GroupBuyTypeListener listener ;

    public GroupBuyTypeListener getListener() {
        return listener;
    }

    public GroupBuyTypeDlg setListener(GroupBuyTypeListener listener) {
        this.listener = listener;
        return this;
    }

    @Bind(R.id.relative_open)RelativeLayout relative_open;
    @Bind(R.id.relative_join)RelativeLayout relative_join;
    @Bind(R.id.radio_open)RadioButton radio_open;
    @Bind(R.id.radio_join)RadioButton radio_join;
    @Bind(R.id.text_confirm)TextView text_confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlg_groupbuy_type,container,false);
        ButterKnife.bind(this,view);

        init();

        return view;
    }

    private void init() {
        radio_open.setChecked(true);
    }

    @OnClick({R.id.relative_open,R.id.relative_join})
    void selectType(View v){
        switch (v.getId()){
            case R.id.relative_open:openSelect();break;
            case R.id.relative_join:joinSelect();break;
        }
    }

    private void joinSelect() {
        radio_open.setChecked(false);
        radio_join.setChecked(true);
    }

    private void openSelect() {
        radio_open.setChecked(true);
        radio_join.setChecked(false);
    }

    @OnClick({R.id.relative_dlg,R.id.image_close,R.id.relative_content})
    void close(View v){
        switch (v.getId()){
            case R.id.relative_dlg:
            case R.id.image_close:dismiss();break;
            default:break;
        }
    }

    @OnClick(R.id.text_confirm)
    void confirmType(){
        if(radio_join.isChecked()) {
            listener.selectType("open");
        }else{
            listener.selectType("join");
        }
        dismiss();
    }
}
