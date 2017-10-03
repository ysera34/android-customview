package org.inframincer.measurementview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yoon on 2017. 10. 3..
 */

public class MeasurementView extends View {

    private int mMeasurement;
    private int mStartMeasurement;
    private int mEndMeasurement;
    private ColorStateList mStartColor;
    private ColorStateList mCenterColor;
    private ColorStateList mEndColor;
    private int mNeedleImageResId;
    private int mNeddleBackgroundResId;

    private Paint mRingPaint;
    private RectF mRingRectF;

    public MeasurementView(Context context) {
        super(context);
    }

    public MeasurementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MeasurementView,
                0, 0
        );

        try {
            mStartMeasurement = typedArray.getInteger(R.styleable.MeasurementView_startMeasurement, 0);
            mEndMeasurement = typedArray.getInteger(R.styleable.MeasurementView_endMeasurement, 1000);
            mStartColor = typedArray.getColorStateList(R.styleable.MeasurementView_startColor);
            mCenterColor = typedArray.getColorStateList(R.styleable.MeasurementView_centerColor);
            mEndColor = typedArray.getColorStateList(R.styleable.MeasurementView_endColor);
            mNeedleImageResId = typedArray.getResourceId(R.styleable.MeasurementView_needleImage, R.drawable.needle);
            mNeddleBackgroundResId = typedArray.getResourceId(R.styleable.MeasurementView_needleBackground, R.drawable.bg_needle);
        } finally {
            typedArray.recycle();
        }

        initializeView();
    }

    private void initializeView() {

        mRingPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRingPaint.setColor(Color.argb(255, 100, 100, 100));
        mRingPaint.setStrokeWidth(20);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setAntiAlias(true);

        mRingRectF = new RectF(0f, 0f, 100f, 100f);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRingRectF, 0, -180, false, mRingPaint);

    }

    public int getMeasurement() {
        return mMeasurement;
    }

    public void setMeasurement(int measurement) {
        mMeasurement = measurement;
    }
}
