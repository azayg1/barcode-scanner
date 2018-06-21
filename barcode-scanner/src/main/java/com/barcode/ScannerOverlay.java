package com.barcode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.util.Random;

import info.androidhive.barcode.R;


public class ScannerOverlay extends ViewGroup {
    private  int frameColor;
    private int lineWidth;
    private int lineColor;
    private Paint line;
    private float left, top, endY;
    private int rectWidth, rectHeight;
    private int frames;
    private boolean revAnimation;
    Paint eraser = new Paint();
    Paint point = new Paint();
    RectF rect = new RectF();
    private Random random;


    public ScannerOverlay(Context context) {
        super(context);
    }

    public ScannerOverlay(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScannerOverlay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ScannerOverlay,
                0, 0);
        rectWidth = a.getInteger(R.styleable.ScannerOverlay_square_width, getResources().getInteger(R.integer.scanner_rect_width));
        rectHeight = a.getInteger(R.styleable.ScannerOverlay_square_height, getResources().getInteger(R.integer.scanner_rect_height));
        lineColor = a.getColor(R.styleable.ScannerOverlay_line_color, ContextCompat.getColor(context, R.color.scanner_line));
        frameColor = a.getColor(R.styleable.ScannerOverlay_frame_color, ContextCompat.getColor(context, R.color.scanner_line));
        int pointColor = a.getColor(R.styleable.ScannerOverlay_point_color, ContextCompat.getColor(context, R.color.scanner_line));
        lineWidth = a.getInteger(R.styleable.ScannerOverlay_line_width, getResources().getInteger(R.integer.line_width));
        frames = a.getInteger(R.styleable.ScannerOverlay_line_speed, getResources().getInteger(R.integer.line_width));

        line = new Paint();
        line.setColor(lineColor);
        line.setAntiAlias(true);
        line.setStyle(Paint.Style.STROKE);
        line.setStrokeWidth((float) lineWidth);

        point = new Paint();
        point.setColor(pointColor);
        point.setAntiAlias(true);
        point.setStrokeWidth((float) lineWidth);

        eraser.setAntiAlias(true);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        random = new Random();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        left = (w - dpToPx(rectWidth)) / 2;
        top = (h - dpToPx(rectHeight)) / 2;
        endY = top;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    Path path = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int cornerRadius = 0;
        rect.left = left;
        rect.top = top;
        line.setColor(lineColor);
        line.setStrokeWidth(lineWidth);
        rect.right = dpToPx(rectWidth) + left;
        rect.bottom = dpToPx(rectHeight) + top;
        canvas.drawRoundRect(rect, (float) cornerRadius, (float) cornerRadius, eraser);
        if (endY >= top + dpToPx(rectHeight) + frames) {
            revAnimation = true;
        } else if (endY == top + frames) {
            revAnimation = false;
        }

        if (revAnimation) {
            endY -= frames;
        } else {
            endY += frames;
        }
        canvas.drawLine(left, endY, left + dpToPx(rectWidth), endY, line);
        for (int i = 0; i < 10; i++) {
            canvas.drawPoint(left + random.nextInt(dpToPx(rectWidth)), top + random.nextInt(dpToPx(rectHeight)), point);
        }


        path.reset();
        path.moveTo(rect.left + 100, rect.top);
        path.lineTo(rect.left, rect.top);
        path.lineTo(rect.left, rect.top + 100);

        path.moveTo(rect.left, rect.bottom - 100);
        path.lineTo(rect.left, rect.bottom);
        path.lineTo(rect.left + 100, rect.bottom);

        path.moveTo(rect.right - 100, rect.bottom);
        path.lineTo(rect.right, rect.bottom);
        path.lineTo(rect.right, rect.bottom - 100);

        path.moveTo(rect.right, rect.top + 100);
        path.lineTo(rect.right, rect.top);
        path.lineTo(rect.right - 100, rect.top);
        line.setStrokeWidth(2 * lineWidth);
        line.setColor(frameColor);
        canvas.drawPath(path, line);


        invalidate();
    }
}