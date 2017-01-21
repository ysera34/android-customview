package com.example.android.circlehazardview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by yoon on 2017. 1. 21..
 */

public class CircleHazardView extends View {

    private int mCircleColor;
    private String mHazardValueText;

    private Paint mCirclePaint;
    private Paint mHazardValuePaint;

    public CircleHazardView(Context context) {
        super(context);
        init();
    }

    public CircleHazardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleHazardView,
                0,0
        );

        try {
            mCircleColor = a.getColor(R.styleable.CircleHazardView_circleColor, 0);
            mHazardValueText = a.getString(R.styleable.CircleHazardView_hazardValueText);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mHazardValuePaint = new Paint();
        mHazardValuePaint.setColor(getResources().getColor(android.R.color.white));
    }

    public int getCircleColor() {
        return mCircleColor;
    }

    public void setCircleColor(int circleColor) {
        mCircleColor = circleColor;
        invalidate();
    }

    public String getHazardValueText() {
        return mHazardValueText;
    }

    public void setHazardValueText(String hazardValueText) {
        mHazardValueText = hazardValueText;
        invalidate();
    }

    float mUsableWidth;
    float mUsableHeight;

    float mRadius;
    float mCenterX;
    float mCenterY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        float width = getWidth();
//        float height = getHeight();

//        float usableWidth = width - (getPaddingStart() + getPaddingEnd());
//        float usableHeight = height - (getPaddingTop() + getPaddingBottom());

//        float radius = Math.min(width, height) / 2;
//        float centerX = getPaddingStart() + (usableWidth / 2);
//        float centerY = getPaddingTop() + (usableHeight / 2);

        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
        drawHazardValue(canvas);

    }

    private void drawHazardValue(Canvas canvas) {

        if (mHazardValueText.length() == 1) {
            mHazardValuePaint.setTextSize(mRadius * 0.88f);
        } else {
            mHazardValuePaint.setTextSize(mRadius * 0.72f);
        }

        Rect hazardValueBounds = new Rect();
        mHazardValuePaint.getTextBounds(mHazardValueText, 0, mHazardValueText.length(), hazardValueBounds);

        mHazardValuePaint.setTextAlign(Paint.Align.CENTER);

//        float x = mRadius - hazardValueBounds.width() / 2;
//        float y = getPaddingTop() + mRadius + hazardValueBounds.height() / 2;
        float x = getPaddingStart() + mRadius;
        float y = getPaddingTop() + mRadius + hazardValueBounds.height() / 2;
        Log.d("Radius", String.valueOf(mRadius));
        Log.d("bound height", String.valueOf(hazardValueBounds.height()));
        Log.d("bound width", String.valueOf(hazardValueBounds.width()));

        canvas.drawText(mHazardValueText, x, y, mHazardValuePaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = (int) getDip(40);
        int height = (int) getDip(40);

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED: // unspecified
                width = widthMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:  // wrap_content
                break;
            case MeasureSpec.EXACTLY:  // match_parent
                width = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }

        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED: // unspecified
                height = heightMeasureSpec;
                break;
            case MeasureSpec.AT_MOST:  // wrap_content
                break;
            case MeasureSpec.EXACTLY:  // match_parent
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
        }

        setMeasuredDimension(width, height);

        mUsableWidth = width - (getPaddingStart() + getPaddingEnd());
        mUsableHeight = height - (getPaddingTop() + getPaddingBottom());

        mRadius = Math.min(width, height) / 2;
        mCenterX = getPaddingStart() + mUsableWidth / 2;
        mCenterY = getPaddingTop() + mUsableHeight / 2;
    }

    public float getDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

}
