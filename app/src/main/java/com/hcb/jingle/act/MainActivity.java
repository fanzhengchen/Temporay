package com.hcb.jingle.act;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.hcb.jingle.GlobalBeans;
import com.hcb.jingle.R;
import com.hcb.jingle.biz.CurrentUser;
import com.hcb.jingle.biz.EventCenter;
import com.hcb.jingle.frg.main.AdminFrg;
import com.hcb.jingle.frg.main.HomePageFrg;
import com.hcb.jingle.frg.main.MallFrg;
import com.hcb.jingle.frg.main.PersonalFrg;
import com.hcb.jingle.frg.main.ZoneFrg;
import com.hcb.jingle.util.ToastUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragAct implements TabHost.OnTabChangeListener
        , EventCenter.EventListener {

    private static final Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    @Bind(R.id.main_title) TextView tvTitle;
    @Bind(android.R.id.tabhost) FragmentTabHost mTabHost;

    private final static int IDX_ME = 4;
    private int curTab = 0;
    private GlobalBeans beans;
    private EventCenter eventCenter;
    private CurrentUser curUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (GlobalBeans.getSelf() == null) {
            GlobalBeans.initForMainUI(getApplication());
        }
        super.onCreate(savedInstanceState);
        beans = GlobalBeans.getSelf();
        curUser = beans.getCurUser();
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        initTabHost();
        setTabSelected(curTab, true);
        beans = GlobalBeans.getSelf();
        eventCenter = beans.getEventCenter();
        eventCenter.registEvent(this, EventCenter.EventType.EVT_MSG_UNREAD);
    }

    @Override
    protected void onDestroy() {
        eventCenter.unregistEvent(this, EventCenter.EventType.EVT_MSG_UNREAD);
        super.onDestroy();
    }

    @Override
    public void onTabChanged(String tabId) {
        final int tabIdx = mTabHost.getCurrentTab();
        if (tabIdx == curTab) {
            return;
        }
        setTabSelected(curTab, false);
        curTab = tabIdx;
        setTabSelected(curTab, true);
    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        for (int i = 0; i < tabs.size(); i++) {
            final TabDesc td = tabs.get(i);
            final View vTab = makeTabView();
            ((TextView) vTab.findViewById(R.id.tab_label)).setText(td.name);
            refreshTab(vTab, td, false);
            if (i == IDX_ME) {
                showUnread(vTab);
            }
            mTabHost.addTab(mTabHost.newTabSpec(td.tag).setIndicator(vTab), td.frgClass, null);
        }
        mTabHost.setBackgroundResource(R.color.light_black);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setOnTabChangedListener(this);
    }

    private View makeTabView() {
        return this.getLayoutInflater().inflate(
                R.layout.cell_maintab, mTabHost.getTabWidget(), false);
    }

    private void setTabSelected(int tabIdx, boolean selected) {
        if (!curUser.isLogin() && selected) {
//            ActivitySwitcher.startFragment(this, LoginFrg.class);
        }
        refreshTab(mTabHost.getTabWidget().getChildAt(tabIdx),
                tabs.get(tabIdx), selected);
        if (selected) {
            tvTitle.setText(tabs.get(curTab).name);
        }
    }

    private void refreshTab(View vTab, TabDesc td, boolean selected) {
        final ImageView iv = (ImageView) vTab.findViewById(R.id.tab_image);
        final TextView tv = (TextView) vTab.findViewById(R.id.tab_label);
        iv.setImageResource(selected ? td.icSelect : td.icNormal);
        tv.setTextColor(getResources().getColor(selected ? R.color.tab_select : R.color.tab_normal));
    }

    private final static List<TabDesc> tabs = new ArrayList<TabDesc>() {
        {
            add(TabDesc.make("homepage", R.string.homepage,
                    R.mipmap.tab_home_n, R.mipmap.tab_home_h, HomePageFrg.class));
            add(TabDesc.make("mall", R.string.mall,
                    R.mipmap.tab_mall_n, R.mipmap.tab_mall_h, MallFrg.class));
            add(TabDesc.make("admin", R.string.admin,
                    R.mipmap.tab_admin_n, R.mipmap.tab_admin_h, AdminFrg.class));
            add(TabDesc.make("zone", R.string.zone,
                    R.mipmap.tab_zone_n, R.mipmap.tab_zone_h, ZoneFrg.class));
            add(TabDesc.make("personal", R.string.personal,
                    R.mipmap.tab_me_n, R.mipmap.tab_me_h, PersonalFrg.class));
        }
    };

    private static class TabDesc {
        String tag;
        int name;
        int icNormal;
        int icSelect;
        Class<? extends Fragment> frgClass;

        static TabDesc make(String tag, int name, int icNormal, int icSelect,
                            Class<? extends Fragment> frgClass) {
            TabDesc td = new TabDesc();
            td.tag = tag;
            td.name = name;
            td.icNormal = icNormal;
            td.icSelect = icSelect;
            td.frgClass = frgClass;
            return td;
        }
    }

    private long backPressTime = 0;
    private static final int SECOND = 1000;

    @Override
    public void onBackPressed() {
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - backPressTime > 2 * SECOND) {
            backPressTime = uptimeMillis;
            ToastUtil.show(getString(R.string.press_again_to_leave));
        } else {
            ToastUtil.cancel();
            onExit();
        }
    }

    private void onExit() {
        finish();
        if (null != beans) {
            beans.onTerminate();
        }
    }

    @Override
    public void onEvent(EventCenter.HcbEvent e) {
        switch (e.type) {
            case EVT_MSG_UNREAD:
                refreshUnread();
                break;
        }
    }

    private void refreshUnread() {
        if (null == mTabHost || null == mTabHost.getTabWidget()) {
            return;
        }
        if (mTabHost.getTabWidget().getChildCount() < tabs.size()) {
            return;
        }
        showUnread(mTabHost.getTabWidget().getChildAt(IDX_ME));
    }

    private void showUnread(final View vTab) {
        final TextView tvUnread = (TextView) vTab.findViewById(R.id.chat_unread);
        final int count = GlobalBeans.getSelf().getCurUser().getTotalUnread();
        if (count > 0) {
            tvUnread.setVisibility(View.VISIBLE);
            tvUnread.setText("" + count);
        } else {
            tvUnread.setVisibility(View.GONE);
        }
    }

}
