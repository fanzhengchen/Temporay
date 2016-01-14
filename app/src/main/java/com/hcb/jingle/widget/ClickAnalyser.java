package com.hcb.jingle.widget;

import android.view.MotionEvent;
import android.view.View;

public class ClickAnalyser implements View.OnTouchListener {

    public interface AnalysListener {
        void onClick(View v, int area);
    }

    public ClickAnalyser setView(View v, AnalysListener l) {
        this.v = v;
        this.listener = l;
        v.setOnTouchListener(this);
        return this;
    }

    private View v;
    private AnalysListener listener;

    private void analyPosition() {
        int area = 0;
        if (downX + downY > v.getWidth() / 2 + v.getHeight() / 2) {
            area = 1;
        }
        if (null != listener) {
            listener.onClick(v, area);
        }
    }

    float downX;
    float downY;
    boolean moved;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                moved = false;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (Math.hypot(downX - event.getX(), downY - event.getY()) > 5) {
                    moved = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!moved) {
                    analyPosition();
                }
                break;
        }
        return false;
    }
}
