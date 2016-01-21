package com.hcb.jingle.frg.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hcb.jingle.GlobalConsts;
import com.hcb.jingle.R;
import com.hcb.jingle.adapter.BannerAdapter;
import com.hcb.jingle.adapter.BannerAdapter.BannerListener;
import com.hcb.jingle.adapter.CommodityAdapter;
import com.hcb.jingle.adapter.HomeGridAdapter;
import com.hcb.jingle.adapter.PagableAdapter.MoreLoader;
import com.hcb.jingle.bean.BannerVO;
import com.hcb.jingle.bean.CommodityVO;
import com.hcb.jingle.bean.HomeGridVO;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.CategoryFrg;
import com.hcb.jingle.frg.CommodityFrg;
import com.hcb.jingle.frg.login.LoginFrg;
import com.hcb.jingle.loader.HomeListLoader;
import com.hcb.jingle.loader.base.AbsLoader.RespReactor;
import com.hcb.jingle.model.HomeListInBody;
import com.hcb.jingle.util.ListUtil;
import com.hcb.jingle.util.ScreenUtil;
import com.hcb.jingle.util.ToastUtil;
import com.hcb.jingle.widget.AutoBanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by yang.zhao on 2016/01/15.
 */
public class HomePageFrg extends CachableFrg implements MoreLoader {

    private final static Logger LOG = LoggerFactory.getLogger(HomePageFrg.class);

    @Bind(R.id.home_ptr_frame) PtrFrameLayout ptrFrame;
    @Bind(R.id.home_list) ListView listView;

    private List<BannerVO> bannerData;
    private List<String> bannerUrls;
    private List<HomeGridVO> gridData;
    private List<CommodityVO> listData;
    private BannerAdapter bannerAdapter;
    private HomeGridAdapter gridAdapter;
    private CommodityAdapter cmtyAdapter;
    private boolean isScrollIdle = true;
    private int page = 0;

    @Override
    protected int rootLayout() {
        return R.layout.main_homepage;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.home_header, null);
        listView.addHeaderView(v);

        initBanner((AutoBanner) v.findViewById(R.id.home_banner));
        initGrid((GridView) v.findViewById(R.id.home_grid));
        initList();
        initPtr();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null == bannerData) {
            loadBanner();
        }
        if (null == gridData) {
            loadGrid();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!curUser.isLogin()) {
            ActivitySwitcher.startFragment(getActivity(), LoginFrg.class);
        }
    }

    private void initBanner(AutoBanner banner) {
        banner.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ScreenUtil.getScreenWidth(getActivity()) / 2
        ));
        bannerData = new ArrayList<>();
        bannerUrls = new ArrayList<>();
        bannerAdapter = new BannerAdapter(bannerUrls)
                .setListener(new BannerListener() {
                    @Override
                    public void OnItemClick(int pos) {
                        jumpDetail(bannerData.get(pos).getId());
                    }
                });
        banner.setAdapter(bannerAdapter);
    }

    private void initGrid(GridView gridView) {
        gridData = monkey();
        gridAdapter = new HomeGridAdapter(gridData);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                jumpCategory(gridData.get(position).getTag());
            }
        });
    }

    private List<HomeGridVO> monkey() {
        int count = 10;
        List<HomeGridVO> data = new ArrayList<>(count);
        String titles = "祝新年快乐恭喜发大财";
        for (int i = 0; i < count; i++) {
            HomeGridVO vo = new HomeGridVO();
            vo.setTitle(titles.substring(i, i + 1));
            data.add(vo);
        }
        return data;
    }

    private void initList() {
        listData = new ArrayList<>();
        listData = getData();
        cmtyAdapter = new CommodityAdapter(getActivity(), listData);
        cmtyAdapter.setMoreLoader(this);
        listView.setAdapter(cmtyAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position -= listView.getHeaderViewsCount();
                jumpDetail(listData.get(position).getId());
            }
        });
        listView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                isScrollIdle = scrollState == SCROLL_STATE_IDLE;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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

    private void initPtr() {
        ptrFrame.disableWhenHorizontalMove(true);
        ptrFrame.setEnabledNextPtrAtOnce(false);
        MaterialHeader ptrHead = new MaterialHeader(getActivity());
        ptrHead.setPadding(0, 24, 0, 24);
        ptrFrame.setHeaderView(ptrHead);
        ptrFrame.addPtrUIHandler(ptrHead);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view1) {
                return isScrollIdle &&
                        listView.getFirstVisiblePosition() == 0 &&
                        listView.getChildAt(0).getTop() >= listView.getPaddingTop();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                reloadData();
            }
        });
    }

    private void loadBanner() {

    }

    private void loadGrid() {

    }

    private void genBannerUrls() {
        for (BannerVO bvo : bannerData) {
            bannerUrls.add(bvo.getImgUrl());
        }
    }

    private void jumpCategory(final String tag) {
        final Bundle b = new Bundle();
        b.putString(GlobalConsts.EX_CTGY_TAG, tag);
        ActivitySwitcher.startFragment(getActivity(), CategoryFrg.class, b);
    }

    private void jumpDetail(final String id) {
        final Bundle b = new Bundle();
        b.putString(GlobalConsts.EX_CMTY_ID, id);
        ActivitySwitcher.startFragment(getActivity(), CommodityFrg.class, b);
    }

    @Override
    public int pageSize() {
        return HomeListLoader.PAGE_SIZE;
    }

    @Override
    public void nextPage() {
        loadData();
    }

    private void reloadData() {
        loadData();
    }

    private void loadData() {
        new HomeListLoader().load(page, new RespReactor<HomeListInBody>() {
            @Override
            public void succeed(HomeListInBody body) {
                List<CommodityVO> list = body.getData();
                if (ListUtil.isEmpty(list)) {
                    cmtyAdapter.setNoMore();
                } else {
                    page++;
                    listData.addAll(list);
                    cmtyAdapter.onDataChange();
                }
                cmtyAdapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String code, String reason) {
                ToastUtil.show(reason);
                cmtyAdapter.setLoadError();
                cmtyAdapter.notifyDataSetChanged();
            }
        });
    }

}
