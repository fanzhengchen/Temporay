package com.hcb.jingle.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hcb.jingle.R;

/**
 * Created by dyx on 2016/1/20.
 */
public class GroupBuyTypeDlg extends BaseDialog{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dlg_groupbuy_type,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
