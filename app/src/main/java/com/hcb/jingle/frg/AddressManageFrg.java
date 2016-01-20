package com.hcb.jingle.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hcb.jingle.R;
import com.hcb.jingle.adapter.EditAddressAdapter;
import com.hcb.jingle.bean.Consignee;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class AddressManageFrg extends TitleFragment {

    @Bind(R.id.list_view)
    ListView listView;

    private EditAddressAdapter adapter;

    @Override
    public int getTitleId() {
        return R.string.address_manage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_address_manager, container, false);
        ButterKnife.bind(this, rootView);
        loadData();
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
