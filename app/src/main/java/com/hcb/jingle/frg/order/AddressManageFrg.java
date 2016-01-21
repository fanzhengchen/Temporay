package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hcb.jingle.R;
import com.hcb.jingle.adapter.EditAddressAdapter;
import com.hcb.jingle.bean.Consignee;
import com.hcb.jingle.frg.PtrListViewFrg;
import com.hcb.jingle.frg.TitleFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class AddressManageFrg extends PtrListViewFrg {

    private EditAddressAdapter adapter;
    private View footer;

    @Override
    public int getTitleId() {
        return R.string.address_manage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        footer = View.inflate(getContext(), R.layout.cell_add_new_address, null);
        ptrFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
//        loadData();
        return rootView;
    }

    private void loadData() {
        ArrayList<Consignee> consignees = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            Consignee consignee = new Consignee();
            consignee.setAddress("宇宙真理");
            consignee.setName("金三胖");
            consignee.setPhone("110");
            consignees.add(consignee);
        }
        adapter = new EditAddressAdapter(act, consignees);
        listView.setAdapter(adapter);

    }


}
