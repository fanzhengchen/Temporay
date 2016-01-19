package com.hcb.jingle.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hcb.jingle.R;

/**
 * Created by Administrator on 2016/1/19.
 */
public class RoundedCornerTextView extends TextView {

    private int strokeColor;
    private int strokeWidth;
    private int backgroundColor;
    private int cornerWidth;
    private int width, height;

    public RoundedCornerTextView(Context context) {
        super(context);
    }

    public RoundedCornerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundedCornerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RoundedCornerTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundedCornerTextView, 0, 0);
        final Resources.Theme theme = context.getTheme();
        try {
            strokeWidth = array.getDimensionPixelSize(R.styleable.RoundedCornerTextView_strokeWidth, 2);
            strokeColor = array.getColor(R.styleable.RoundedCornerTextView_strokeColor, Color.BLACK);
            backgroundColor = array.getColor(R.styleable.RoundedCornerTextView_backgroundColor, Color.WHITE);
            cornerWidth = array.getDimensionPixelSize(R.styleable.RoundedCornerTextView_cornerWidth, 0);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);

    }
}
