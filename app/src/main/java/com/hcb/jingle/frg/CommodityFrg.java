package com.hcb.jingle.frg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.dialog.AddressSelectDlg;
import com.hcb.jingle.dialog.GroupBuyTypeDlg;
import com.hcb.jingle.dialog.PayTypeSelectDlg;
import com.hcb.jingle.dialog.SpecificationSelectDlg;
import com.hcb.jingle.util.ToastUtil;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class CommodityFrg extends TitleFragment {
    private String id;
    private Boolean hasSpecification = false;
    @Bind(R.id.image_back)ImageView image_back;
    @Bind(R.id.image_share)ImageView image_share;
    @Bind(R.id.image_title)ImageView image_title;
    @Bind(R.id.text_delegation)TextView text_delegation;
    @Bind(R.id.text_group_progress)TextView text_group_progress;
    @Bind(R.id.text_commodity_name)TextView text_commodity_name;
    @Bind(R.id.text_description)TextView text_description;
    @Bind(R.id.image_progress)ImageView image_progress;
    @Bind(R.id.text_fare)TextView text_fare;
    @Bind(R.id.text_sales)TextView text_sales;
    @Bind(R.id.text_time)TextView text_time;
    @Bind(R.id.relative_specification)RelativeLayout relative_specification;
    @Bind(R.id.text_specification)TextView text_specification;
    @Bind(R.id.image_detail)ImageView image_detail;
    @Bind(R.id.relative_phone_call)RelativeLayout relative_phone_call;
    @Bind(R.id.text_buy)TextView text_buy;
    @Bind(R.id.text_group_join)TextView text_group_join;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getString(GlobalConsts.EX_CMTY_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_commodity_detail,container,false);
        ButterKnife.bind(this, rootView);

        init();

        return rootView;
    }

    private void init() {
        act.hideNavi();
    }


    @OnClick({R.id.text_buy,R.id.text_group_join})
    void fillInformation(View v){
        switch (v.getId()) {
            case R.id.text_buy:
                if(hasSpecification){
                    showAddressSelect();
                }else{
                    showSpecification(false);
                }

                break;
            case R.id.text_group_join:
                showType();
                break;
        }
    }

    private void showType() {
        new GroupBuyTypeDlg().setListener(new GroupBuyTypeDlg.GroupBuyTypeListener() {
            @Override
            public void selectType(String type) {
                if(hasSpecification){
                    showAddressSelect();
                }else{
                    showSpecification(false);
                }
            }
        }).show(getFragmentManager(),"type");
    }

    private void showAddressSelect() {
        new AddressSelectDlg().setListener(new AddressSelectDlg.AddressSelectListener() {
            @Override
            public void selectAddress(String name, String phone, String address) {
                showPayType();
            }
        }).show(getFragmentManager(),"address");

    }

    private void showPayType() {
        new PayTypeSelectDlg().setListener(new PayTypeSelectDlg.PayTypeListener() {
            @Override
            public void selectPayType(String type) {
                ActivitySwitcher.startFragment(act,PaySuccessFrg.class);
            }
        }).show(getFragmentManager(),"pay");
    }

    private void showSpecification(final Boolean back) {
        new SpecificationSelectDlg().setListener(new SpecificationSelectDlg.SpecificationListener() {
            @Override
            public void getSpecification(String specification, String number) {
                if(!back){
                    showAddressSelect();
                }
            }
        }).show(getFragmentManager(), "specification");
    }
}
