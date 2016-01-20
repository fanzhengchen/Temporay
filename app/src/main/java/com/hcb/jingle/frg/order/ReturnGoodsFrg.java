package com.hcb.jingle.frg.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.actlink.NaviRightDecorator;
import com.hcb.jingle.adapter.RecyclerViewAdapter;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.util.ColorUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/20.
 */
public class ReturnGoodsFrg extends TitleFragment implements NaviRightDecorator {

    @Bind(R.id.frg_return_goods_img_list)
    RecyclerView recyclerView;

    private RecyclerViewAdapter adapter = null;

    @Override
    public int getTitleId() {
        return R.string.return_goods_illustration;
    }

    @Override
    public void decorRightBtn(TextView textView) {
        textView.setTextColor(ColorUtil.getColor(R.color.main_pink));
        textView.setText(R.string.submit);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_return_goods, container, false);
        ButterKnife.bind(this, rootView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(act);
        recyclerView.setAdapter(adapter);
        return rootView;
    }


}
