package com.hcb.jingle.act;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcb.jingle.R;
import com.hcb.jingle.actlink.ActDecorator;
import com.hcb.jingle.actlink.NaviLeftListener;
import com.hcb.jingle.actlink.NaviLeftText;
import com.hcb.jingle.actlink.NaviRightDecorator;
import com.hcb.jingle.actlink.NaviRightListener;
import com.hcb.jingle.actlink.NaviTitleDecorator;
import com.hcb.jingle.biz.ActivitySwitcher;
import com.hcb.jingle.frg.TitleFragment;
import com.hcb.jingle.util.ReflectUtil;
import com.hcb.jingle.util.StringUtil;
import com.hcb.jingle.util.TypeSafer;
import com.umeng.analytics.MobclickAgent;

public class NaviActivity extends BaseFragAct {

    public static final String EXT_FRAGMENT = "fragment_name";

    protected TitleFragment fragment;

    protected boolean isDestroyed;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        fragment = createInstance(getFragmentName(getIntent()));
        if (null == fragment) {
            finish();
            return;
        }
        fragment.setArguments(getIntent().getExtras());
        fragment.setActivity(this);
        if (fragment instanceof ActDecorator) {
            ((ActDecorator) fragment).beforeContentView();
        }
        setContentView(R.layout.act_common);
        initNaviBar();
        if (fragment instanceof ActDecorator) {
            ((ActDecorator) fragment).afterContentView();
        }
        addFragment();
    }

    @Deprecated
    public void changeAllBg(final int color) {
        findViewById(R.id.act_whole).setBackgroundColor(
                getResources().getColor(color));
        findViewById(R.id.act_content).setBackgroundColor(
                getResources().getColor(color));
    }

    public void setNaviTitle(final String title) {
        TypeSafer.text((TextView) findViewById(
                R.id.navi_title), title);
    }

    public void hideNavi() {
        findViewById(R.id.act_navi).setVisibility(View.GONE);
    }

    public boolean isDisable() {
        return isDestroyed || isFinishing();
    }

    public String getFragmentName(final Intent intent) {
        return (null != intent) ? intent.getStringExtra(EXT_FRAGMENT) : null;
    }

    public TitleFragment createInstance(final String fragName) {
        final Object obj = ReflectUtil.createInstance(fragName);
        if (obj instanceof TitleFragment) {
            return (TitleFragment) obj;
        }
        return null;
    }

    public void initNaviBar() {
        initTitle();
        initLeft();
        initRight();
        initTitleBg();
    }



    private View arrowLeft;

    public void initLeft() {
        final OnClickListener cl = new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (fragment instanceof NaviLeftListener) {
                    ((NaviLeftListener) fragment).onLeftClicked();
                } else {
                    onBackPressed();
                }
            }
        };

        arrowLeft = findViewById(R.id.navi_left);
        if (!fragment.hideLeftArrow()) {
            arrowLeft.setOnClickListener(cl);
        }
        arrowLeft.setVisibility(View.GONE);

        final TextView tvLeft = (TextView) findViewById(R.id.navi_left_txt);
        if (fragment instanceof NaviLeftText) {
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText(((NaviLeftText) fragment).leftText());
            tvLeft.setOnClickListener(cl);
            arrowLeft.setPadding(
                    arrowLeft.getPaddingLeft(), arrowLeft.getPaddingTop(),
                    0, arrowLeft.getPaddingBottom());
        }
    }

    public void initTitle() {
        final TextView tv = (TextView) findViewById(R.id.navi_title);
        final int title = fragment.getTitleId();
        final String titleName = fragment.getTitleName();
        if (title > 0) {
            tv.setText(title);
        } else if (!TextUtils.isEmpty(titleName)) {
            tv.setText(titleName);
        }
        if (fragment instanceof NaviTitleDecorator) {
            ((NaviTitleDecorator) fragment).decorTitle(tv);
        }
    }

    public void initRight() {
        final TextView btn = (TextView) findViewById(R.id.navi_right_txt);
        if (fragment instanceof NaviRightListener) {
            final NaviRightListener l = (NaviRightListener) fragment;
            btn.setText(l.rightText());
            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (null != l) {
                        l.onRightClicked(v);
                    }
                }
            });
        } else if (fragment instanceof NaviRightDecorator) {
            ((NaviRightDecorator) fragment).decorRightBtn(btn);
        } else {
            btn.setVisibility(View.INVISIBLE);
        }
    }

    private void initTitleBg() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.act_navi);
        relativeLayout.setBackgroundResource(fragment.getTitleBackGround());
    }

    public void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.act_content, fragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        fragment.onActivityResult(arg0, arg1, arg2);
    }

    public void finishSelf() {
        arrowLeft.setVisibility(View.GONE);
        ActivitySwitcher.finish(this);
    }

    public void finishDown() {
        ActivitySwitcher.finishDown(this);
    }

    @Override
    public void onBackPressed() {
        if (fragment instanceof NaviLeftListener
                && ((NaviLeftListener) fragment).onLeftClicked()) {
            return;
        }
        finishSelf();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (null != fragment) {
            MobclickAgent.onPageStart(simpleName());
        }
        if (!fragment.hideLeftArrow()) {
            arrowLeft.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != fragment) {
            MobclickAgent.onPageEnd(simpleName());
        }
        MobclickAgent.onPause(this);
    }

    public String simpleName() {
        return fragment.getClass().getSimpleName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    public void setNaviColor(int color) {
        View navi = findViewById(R.id.act_navi);
        navi.setBackgroundColor(color);
    }
}
