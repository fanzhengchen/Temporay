package com.hcb.jingle.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hcb.jingle.R;
import com.hcb.jingle.util.FormatUtil;

public class AutoBanner extends RelativeLayout
        implements LoopViewPager.OnPageChangeListener {

    private boolean isLoop;
    private boolean isAuto;
    private int barHight = FormatUtil.pixOfDip(20);

    private LoopViewPager pager;
    private RadioGroup indicatorBar;
    private int curIdx;

    public AutoBanner(Context context) {
        this(context, null);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        pager = new LoopViewPager(getContext());
        pager.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        pager.setOnPageChangeListener(this);
        addView(pager);

        indicatorBar = new RadioGroup(getContext());
        indicatorBar.setFocusable(false);
        indicatorBar.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams barParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, barHight);
        barParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        barParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        indicatorBar.setLayoutParams(barParams);
        indicatorBar.setGravity(Gravity.CENTER_VERTICAL);
        addView(indicatorBar);
    }

    public void setAdapter(final PagerAdapter adapter) {
        if (null != adapter) {
            adapter.registerDataSetObserver(new DataObserver());
            changeCount(adapter.getCount());
        } else {
            changeCount(0);
        }
        pager.setAdapter(adapter);
    }

    private void changeCount(final int count) {
        int curCount = indicatorBar.getChildCount();
        if (curCount == count) {
            return;
        } else if (curCount < count) {
            for (int i = curCount; i < count; i++) {
                indicatorBar.addView(makePoint());
            }
        } else {
            for (int j = curCount - 1; j >= count; j--) {
                indicatorBar.removeViewAt(j);
            }
        }
        if (curIdx >= count) {
            curIdx = count - 1;
        }
        if (count > 0) {
            setPointSelected(curIdx, true);
        }
    }

    private View makePoint() {
        ImageView btn = new ImageView(getContext());
        btn.setLayoutParams(new LinearLayout.LayoutParams(
                barHight, barHight
        ));
        btn.setPadding(barHight / 4, barHight / 4, barHight / 4, barHight / 4);
        btn.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        btn.setImageResource(R.drawable.indicator_point);
        return btn;
    }

    private void setPointSelected(int i, boolean selected) {
        indicatorBar.getChildAt(i).setSelected(selected);
    }

    private class DataObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            changeCount(pager.getAdapter().getCount());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int size = indicatorBar.getChildCount();
        int relPos = position % size;
        while (relPos < 0) {
            relPos = size + relPos;
        }
        setPointSelected(curIdx, false);
        curIdx = relPos;
        setPointSelected(curIdx, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
