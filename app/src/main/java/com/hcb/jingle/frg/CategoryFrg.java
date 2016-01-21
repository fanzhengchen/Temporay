package com.hcb.jingle.frg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.adapter.CommodityAdapter;
import com.hcb.jingle.bean.CommodityVO;
import com.hcb.jingle.biz.ActivitySwitcher;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class CategoryFrg extends TitleFragment {
    private String tag ;
    private CommodityAdapter adapter;
    private ArrayList<CommodityVO> listData;

    @Bind(R.id.list_commodity)ListView list_commodity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tag = getArguments().getString(GlobalConsts.EX_CTGY_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.frg_category,container,false);
        ButterKnife.bind(this, rootView);
        init();

        return rootView;
    }

    private void init() {
        initTitle();
        initList();
    }

    private void initList() {
        listData = new ArrayList<CommodityVO>();
        listData = getData();
        adapter = new CommodityAdapter(act,listData);
        adapter.setIsHomeFrg(false);
        list_commodity.setAdapter(adapter);
        list_commodity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpDetail(listData.get(position).getId());
            }
        });
    }

    private ArrayList<CommodityVO> getData(){
        int count = 5;
        ArrayList<CommodityVO> data = new ArrayList<CommodityVO>();
        for(int i = 0;i<count;i++){
            CommodityVO vo = new CommodityVO();
            vo.setId(i+"");
            vo.setDesc("商品"+i);
            vo.setTitle("超级大甩卖"+i);
            data.add(vo);
        }
        return data;
    }

    private void jumpDetail(String id) {
        Bundle b = new Bundle();
        b.putString(GlobalConsts.EX_CMTY_ID,id);
        ActivitySwitcher.startFragment(act,CommodityFrg.class,b);
    }

    private void initTitle() {
        act.setNaviTitle(tag);
    }
}
