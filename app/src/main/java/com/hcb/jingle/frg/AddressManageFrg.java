package com.hcb.jingle.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hcb.jingle.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/1/20.
 */
public class AddressManageFrg extends TitleFragment {

    @Bind(R.id.list_view)
    ListView listView;
    private View addAddress;
    @Override
    public int getTitleId() {
        return R.string.address_manage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_address_manager, container, false);
        addAddress = inflater.inflate(R.layout.cell_add_new_addr, null);
        return rootView;
    }


}
