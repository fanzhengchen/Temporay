package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.hcb.jingle.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dyx on 2016/1/21.
 */
public class PayTypeSelectDlg extends BaseDialog{
    public interface PayTypeListener{
        void selectPayType(String type);
    }

    private PayTypeListener listener;

    public PayTypeListener getListener() {
        return listener;
    }

    public PayTypeSelectDlg setListener(PayTypeListener listener) {
        this.listener = listener;
        return this;
    }

    private int curId = 1;

    @Bind(R.id.linear)LinearLayout linear;
    @Bind(R.id.relative_dingdang)RelativeLayout relative_dingdang;
    @Bind(R.id.relative_alipay)RelativeLayout relative_alipay;
    @Bind(R.id.relative_wchat)RelativeLayout relative_wchat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlg_pay_type,container,false);
        ButterKnife.bind(this,view);

        init();

        return view ;
    }

    private void init() {

        ((RadioButton)relative_dingdang.getChildAt(0)).setChecked(true);
    }

    @OnClick({R.id.relative_dingdang,R.id.relative_alipay,R.id.relative_wchat})
    void paySelect(View v){
        switch (v.getId()){
            case R.id.relative_dingdang: setSelect(1);break;
            case R.id.relative_alipay:setSelect(3);break;
            case R.id.relative_wchat:setSelect(5);break;
        }
    }

    private void setSelect(int i) {
        ((RadioButton)((RelativeLayout)linear.getChildAt(curId)).getChildAt(0)).setChecked(false);
        ((RadioButton)((RelativeLayout)linear.getChildAt(i)).getChildAt(0)).setChecked(true);
        curId = i;
    }


    @OnClick(R.id.text_confirm)
    void payTypeConfirm(){
        switch (curId){
            case 1:listener.selectPayType("dingdang");
            case 3:listener.selectPayType("支付宝支付");
            case 5:listener.selectPayType("微信支付");
        }
        dismiss();
    }

    @OnClick({R.id.relative_dlg,R.id.image_close,R.id.relative_content})
    void close(View v){
        switch (v.getId()){
            case R.id.relative_dlg:
            case R.id.image_close:dismiss();break;
            default:break;
        }
    }
}
