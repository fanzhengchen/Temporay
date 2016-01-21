package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.util.ToastUtil;
import com.sina.weibo.sdk.api.share.Base;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dyx on 2016/1/20.
 */
public class SpecificationSelectDlg extends BaseDialog{
    private int curId = 0;
    public interface SpecificationListener{
        void getSpecification(String specification,String number);
    }
    private SpecificationListener listener;

    public SpecificationSelectDlg setListener(SpecificationListener listener){
        this.listener = listener;
        return this;
    }

    @Bind(R.id.image_close)ImageView image_close;
    @Bind(R.id.image_commodity)ImageView image_commodity;
    @Bind(R.id.text_commodity_name)TextView text_commodity_name;
    @Bind(R.id.text_price)TextView text_price;
    @Bind(R.id.radio_specification)RadioGroup radio_specification;
    @Bind(R.id.text_xs)TextView text_xs;
    @Bind(R.id.text_s)TextView text_s;
    @Bind(R.id.text_ml)TextView text_ml;
    @Bind(R.id.text_xl)TextView text_xl;
    @Bind(R.id.text_reduce)TextView text_reduce;
    @Bind(R.id.text_add)TextView text_add;
    @Bind(R.id.edit_number)EditText edit_number;
    @Bind(R.id.text_confirm)TextView text_confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlg_select_specification,container,false);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        radio_specification.getChildAt(curId).setSelected(true);
        edit_number.setText("1");
    }

    @OnClick({R.id.text_xs,
              R.id.text_s,
              R.id.text_ml,
              R.id.text_xl,})
    void selectSpecification(View v){
        radio_specification.clearCheck();
        switch (v.getId()){
            case R.id.text_xs:setSelect( 0);break;
            case R.id.text_s:setSelect(1);break;
            case R.id.text_ml:setSelect(2);break;
            case R.id.text_xl:setSelect(3);break;
        }
    }

    private void setSelect(int i) {
        if(curId!=i){
            radio_specification.getChildAt(curId).setSelected(false);
            curId = i;
            radio_specification.getChildAt(curId).setSelected(true);
        }
    }

    @OnClick({R.id.text_reduce,R.id.text_add})
    void setNumber(View v){
        int i = Integer.valueOf(edit_number.getText().toString());
        switch (v.getId()){
            case R.id.text_reduce:
                if(i!=1){
                   i -= 1;
                    edit_number.setText(i+"");
                }break;
            case R.id.text_add:
                i += 1;
                edit_number.setText(i + "");
                break;
        }
    }

    @OnClick(R.id.text_confirm)
    void confirm(){
        String str = null;
        switch (curId){
            case 0:str = "xs";break;
            case 1:str = "s";break;
            case 2:str = "ml";break;
            case 3:str = "xl";break;
        }
        listener.getSpecification(str,edit_number.getText().toString());
        dismiss();
    }

    @OnClick({R.id.relative_dlg,R.id.image_close,R.id.linear_content})
    void close(View v){
        switch (v.getId()){
            case R.id.relative_dlg:
            case R.id.image_close:dismiss();break;
            default:break;
        }
    }


    @Override
    protected void animAppear() {
        animPopup();
    }

    @Override
    protected void animDisAppear() {
        animPopDown();
    }

}
