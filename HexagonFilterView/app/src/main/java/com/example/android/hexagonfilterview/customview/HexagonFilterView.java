package com.example.android.hexagonfilterview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.android.hexagonfilterview.R;

/**
 * Created by yoon on 2017. 1. 19..
 */

public class HexagonFilterView extends View {

    /*
    format : boolean , java primitive type : boolean
    format : string, java primitive, reference type : String
    format : dimension , java primitive type : float
    format : color , java primitive type : int
     */
    private float mWidth, mHeight;
    private float mHexagonWidth;
    private float mHexagonHeight;
    private int mHexagonColor;
    private String mInsideCountText;
    private float mInsideCountTextSize;
    private int mInsideCountTextColor;
    private String mInsideUnitText;
    private float mInsideUnitTextSize;
    private int mInsideUnitTextColor;
    private float mHexagonLabelMarginBottom;
    private String mHexagonLabelText;
    private float mHexagonLabelTextSize;
    private int mHexagonLabelTextColor;

    private Path mHexagonPath;

    private Paint mHexagonPaint;
    private Paint mInsideCountPaint;
    private Paint mInsideUnitPaint;
    private Paint mHexagonLabelPaint;

    public HexagonFilterView(Context context) {
        super(context);
        init();
    }

    public HexagonFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.HexagonFilterView,
                0,0
        );

        try {
            mHexagonWidth = a.getDimension(R.styleable.HexagonFilterView_hexagonWidth, 56.0f);
            mHexagonColor = a.getColor(R.styleable.HexagonFilterView_hexagonColor, 0);
            mInsideCountText = a.getNonResourceString(R.styleable.HexagonFilterView_insideCountText);
            mInsideCountTextSize = a.getDimension(R.styleable.HexagonFilterView_insideCountTextSize, 19.0f);
            mInsideCountTextColor = a.getColor(R.styleable.HexagonFilterView_insideCountTextColor, 0);
            mInsideUnitText = a.getNonResourceString(R.styleable.HexagonFilterView_insideUnitText);
            mInsideUnitTextSize = a.getDimension(R.styleable.HexagonFilterView_insideUnitTextSize, 13.0f);
            mInsideUnitTextColor = a.getColor(R.styleable.HexagonFilterView_insideUnitTextColor, 0);
            mHexagonLabelText = a.getNonResourceString(R.styleable.HexagonFilterView_hexagonLabelText);
            mHexagonLabelTextSize = a.getDimension(R.styleable.HexagonFilterView_hexagonLabelTextSize, 12.0f);
            mHexagonLabelTextColor = a.getColor(R.styleable.HexagonFilterView_hexagonLabelTextColor, 0);
        } finally {
            a.recycle();
        }

        init();
    }

    private void init() {

        mHexagonHeight = mHexagonWidth / 8f * 9;

        mHexagonPath = new Path();

        mHexagonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHexagonPaint.setColor(mHexagonColor);
        mHexagonPaint.setStyle(Paint.Style.FILL);
        mHexagonPaint.setAntiAlias(true);

        mInsideCountPaint = new Paint();
        mInsideCountPaint.setTextSize(mInsideCountTextSize);
        mInsideCountPaint.setColor(mInsideCountTextColor);

        mInsideUnitPaint = new Paint();
        mInsideUnitPaint.setTextSize(mInsideUnitTextSize);
        mInsideUnitPaint.setColor(mInsideUnitTextColor);

        mHexagonLabelMarginBottom = 20.0f;

        mHexagonLabelPaint = new Paint();
        mHexagonLabelPaint.setTextSize(mHexagonLabelTextSize);
        mHexagonLabelPaint.setColor(mHexagonLabelTextColor);
    }

    private void calculateHexagonPath() {
        float triangleHeight = mHexagonHeight / 4f;
        float rectangleWidth = mHexagonWidth;
        float rectangleHeight = mHexagonHeight / 2f;
        float centerX = mHexagonWidth / 2f;
        float centerY = mHexagonHeight / 2f;

        mHexagonPath.moveTo(centerX, centerY - mHexagonHeight / 2f);
        mHexagonPath.lineTo(centerX - rectangleWidth / 2f, centerY - rectangleHeight / 2f);
        mHexagonPath.lineTo(centerX - rectangleWidth / 2f, centerY + rectangleHeight / 2f);
        mHexagonPath.lineTo(centerX, centerY + mHexagonHeight / 2f);
        mHexagonPath.lineTo(centerX + rectangleWidth / 2f, centerY + rectangleHeight / 2f);
        mHexagonPath.lineTo(centerX + rectangleWidth / 2f, centerY - rectangleHeight / 2f);
        mHexagonPath.moveTo(centerX, centerY - mHexagonHeight / 2f);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPath(mHexagonPath, mHexagonPaint);
        drawInsideLabel(canvas);
        drawOutsideLabel(canvas);
    }

    private void drawInsideLabel(Canvas canvas) {

        Rect countBounds = new Rect();
        mInsideCountPaint.getTextBounds(mInsideCountText, 0, mInsideCountText.length(), countBounds);

        Rect unitBounds = new Rect();
        mInsideUnitPaint.getTextBounds(mInsideUnitText, 0,  mInsideUnitText.length(), unitBounds);

        float x = (mHexagonWidth / 2) - ((countBounds.width() + unitBounds.width()) / 2) ;
        float y = (countBounds.height() / 2) + mHexagonHeight / 2;
        canvas.drawText(mInsideCountText, x, y, mInsideCountPaint);

        x += countBounds.width() + 10;
        y -= 5;
        canvas.drawText(mInsideUnitText, x, y, mInsideUnitPaint);
    }

    private void drawOutsideLabel(Canvas canvas) {

        Rect labelBounds = new Rect();
        mHexagonLabelPaint.getTextBounds(mHexagonLabelText, 0, mHexagonLabelText.length(), labelBounds);

        float x = (mHexagonWidth / 2) - labelBounds.width() / 2;
        float y = mHexagonHeight + mHexagonLabelMarginBottom + labelBounds.height();

        canvas.drawText(mHexagonLabelText, x, y, mHexagonLabelPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
        calculateHexagonPath();
    }

    private int measureWidth(int measureSpec) {
        int size = (int) mHexagonWidth;
        return resolveSizeAndState(size, measureSpec, 0);
    }

    private int measureHeight(int measureSpec) {
        int size = getPaddingTop() + getPaddingBottom();
        size += mHexagonHeight + mHexagonLabelMarginBottom + mHexagonLabelPaint.getFontSpacing();
        return resolveSizeAndState(size, measureSpec, 0);
    }
}
