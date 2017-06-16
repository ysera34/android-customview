package com.example.android.customscrollview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.height;
import static android.R.attr.width;

/**
 * Created by yoon on 2017. 6. 16..
 */

public class ScrollView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private GestureDetector mGestureDetector;
    private int mMaxWidth;
    private int mMaxHeight;

    public ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
//                return super.onDown(e);
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//                return super.onScroll(e1, e2, distanceX, distanceY);
                scrollBy((int) distanceX, (int) distanceY);
                invalidate();
                return true;
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();

        int x = getScrollX();
        int y = getScrollY();
        if (x < 0 || x > mMaxWidth - getMeasuredWidth() || y < 0 | y > mMaxHeight - getMeasuredHeight()) {
            x = Math.max(0, Math.min(mMaxWidth - getMeasuredWidth(), x));
            y = Math.max(0, Math.min(mMaxHeight - getMeasuredHeight(), y));
            scrollTo(x, y);
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        boolean isConsumed = mGestureDetector.onTouchEvent(event);
        return isConsumed || super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        }
    }
}
