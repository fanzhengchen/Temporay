package com.hcb.jingle.frg.main;

import android.os.Bundle;

import com.hcb.jingle.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.hcb.jingle.loader.user.HomePageLoader;
//import com.hcb.jingle.model.HomePageInBody;

public class HomePageFrg extends CachableFrg {
    private final static Logger LOG = LoggerFactory.getLogger(HomePageFrg.class);

    @Override
    protected int rootLayout() {
        return R.layout.main_homepage;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        loadData();
    }

    private void loadData() {
//
//        new HomePageLoader().load(new AbsLoader.RespReactor<HomePageInBody>() {
//            @Override
//            public void succeed(HomePageInBody body) {
//                ToastUtil.show(R.string.login_success);
//                listList = body.getLiveList();
//                fillLivePortraits(listList);
//                homePageUserInfos = body.getUserList();
//                adapter = new GridViewAdapter(getActivity(), homePageUserInfos);
//                gridView.setAdapter(adapter);
//            }
//
//            @Override
//            public void failed(String code, String reason) {
//                ToastUtil.show(reason);
//            }
//        });
    }

}
