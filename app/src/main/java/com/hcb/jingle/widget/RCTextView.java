package com.hcb.jingle.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.Toast;

import com.hcb.jingle.R;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/1/19.
 */
public class RCTextView extends TextView {

    private int strokeColor;
    private int strokeWidth;
    private int backgroundColor;
    private int cornerWidth;
    private int width, height;
    private float textSize;
    private int textColor;
    private String text;
    private Logger log = LoggerFactory.getLogger(RCTextView.class);
    private Canvas canvas;

    public RCTextView(Context context) {
        super(context);
    }

    public RCTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
    }

    public RCTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
    }

    public RCTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        final Resources.Theme theme = context.getTheme();
        TypedArray array = theme.obtainStyledAttributes(attrs, R.styleable.RCTextView, 0, 0);
        try {
            strokeWidth = array.getDimensionPixelSize(R.styleable.RCTextView_strokeWidth, 2);
            strokeColor = array.getColor(R.styleable.RCTextView_strokeColor, 0);
            backgroundColor = array.getColor(R.styleable.RCTextView_backgroundColor, Color.WHITE);
            cornerWidth = array.getDimensionPixelSize(R.styleable.RCTextView_cornerWidth, 0);
            text = array.getString(R.styleable.RCTextView_centerText);
            textColor = array.getColor(R.styleable.RCTextView_textColor, 0);
            textSize = array.getDimensionPixelSize(R.styleable.RCTextView_textSize, 14);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawSolid(canvas, backgroundColor);
        drawStroke(canvas, strokeColor);
        if (text == null) {
            super.onDraw(canvas);
        } else {
            drawCenterText(canvas, text);
        }
        this.canvas = canvas;
    }

    private RectF getRectF() {
        int pad = strokeWidth >> 1;
        int remain = 0;
        return new RectF(pad, pad, width - pad - remain, height - pad - remain);
    }

    private void drawSolid(Canvas canvas, int backgroundColor) {
        Paint solidPaint = new Paint();
        solidPaint.setAntiAlias(true);
        solidPaint.setColor(backgroundColor);
        canvas.drawRoundRect(getRectF(), cornerWidth, cornerWidth, solidPaint);
    }

    private void drawStroke(Canvas canvas, int strokeColor) {
        Paint strokePaint = new Paint();
        strokePaint.setAntiAlias(true);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setStyle(Paint.Style.STROKE);
        log.debug("RCTEXTVIEW {}", canvas);
        canvas.drawRoundRect(getRectF(), cornerWidth, cornerWidth, strokePaint);
    }

    private void drawCenterText(Canvas canvas, String text) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        float textWidth = paint.measureText(text);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseY = (height >> 1) + (fontMetrics.descent - fontMetrics.ascent) * 0.5f - fontMetrics.bottom;
        float startX = (width >> 1) - (textWidth * 0.5f);
        canvas.drawText(text, startX, baseY, paint);
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
    }
}
