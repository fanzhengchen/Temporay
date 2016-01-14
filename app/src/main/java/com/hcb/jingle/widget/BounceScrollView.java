package com.hcb.jingle.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class BounceScrollView extends ScrollView {

    // 拖动的距离 size 的意思 只允许拖动屏幕的1/size
    private static final int size = 3;
    private View inner;
    private float downY;
    private Rect normal = new Rect();

    public BounceScrollView(Context context) {
        super(context);
    }

    public BounceScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        if (inner == null) {
            return super.onTouchEvent(ev);
        } else {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
                    animation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaY = (int) ((downY - ev.getY()) / size);
                if (isNeedMove(deltaY)) {
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                        return;
                    }
                    // 移动布局
                    inner.layout(inner.getLeft(), normal.top - deltaY,
                            inner.getRight(), normal.bottom - deltaY);
                }
                break;
            default:
                break;
        }
    }

    // 开启动画移动

    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(), normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    // 是否需要移动布局
    public boolean isNeedMove(int direct) {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        return (scrollY == 0 && direct < 0) || (scrollY == offset && direct > 0);
    }

}

