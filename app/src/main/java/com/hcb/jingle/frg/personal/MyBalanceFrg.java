package com.hcb.jingle.frg.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.adapter.BalanceAdapter;
import com.hcb.jingle.bean.BalanceVO;
import com.hcb.jingle.frg.PtrListViewFrg;
import com.hcb.jingle.util.ColorUtil;
import com.hcb.jingle.util.FormatUtil;

import java.util.ArrayList;

public class MyBalanceFrg extends PtrListViewFrg {
    private BalanceAdapter adapter;
    private TextView balance;
    private View header;
    private int headHeight = FormatUtil.pixOfDip(200);

    @Override
    public int getTitleId() {
        return R.string.my_balance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        super.setUpPtr();
        header = View.inflate(getContext(), R.layout.header_balance_list, null);
        header.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, headHeight));

        TextView textView = new TextView(getContext());
        textView.setText("返利明细");
        textView.setTextColor(ColorUtil.getColor(R.color.txt_lvl_3));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.txt_size_3));
        textView.setGravity(Gravity.CENTER_VERTICAL);
        int padding = FormatUtil.pixOfDip(5);
        textView.setPadding(padding, 0, 0, 0);
        textView.setLayoutParams(new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, FormatUtil.pixOfDip(40)));


        listView.addHeaderView(header);
        listView.addHeaderView(textView);
        loadData();
        return rootView;
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
