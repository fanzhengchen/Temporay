package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.adapter.BalanceAdapter;
import com.hcb.jingle.bean.BalanceVO;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.util.FormatUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/1/20.
 */
public class MyBalanceFrg extends TitleFragment {
    private BalanceAdapter adapter;
    private TextView balance;
    private View header;
    private int headHeight = FormatUtil.pixOfDip(200);
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.ptrFrameLayout)
    PtrFrameLayout ptrFrameLayout;

    @Override
    public int getTitleId() {
        return R.string.my_balance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_list_view, container, false);
        ButterKnife.bind(this, rootView);
        header = View.inflate(getContext(), R.layout.header_balance_list, null);
        header.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, headHeight));

        listView.addHeaderView(header);

        setUpPtr();
        loadData();
        return rootView;
    }

    private void setUpPtr() {
        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameLayout.refreshComplete();
            }
        });
    }

    private void loadData() {
        balance = (TextView) header.findViewById(R.id.frg_balance_number);
        balance.setText(GlobalConsts.RMB + "10000000");
        ArrayList<BalanceVO> data = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            BalanceVO vo = new BalanceVO();
            vo.setName("金三胖");
            vo.setPrice(GlobalConsts.RMB + "4578");
            data.add(vo);
        }
        adapter = new BalanceAdapter(data);
        listView.setAdapter(adapter);
    }
}
