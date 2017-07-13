package com.android.sample.bargraphview;

import android.content.Context;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yoon on 2017. 7. 13..
 */

public class BarGraphView extends LinearLayout {

    public BarGraphView(Context context, int barGraphValue, int barGraphTargetWidth, int barColorResId) {
        super(context);
        mBarGraphValue = barGraphValue;
        mBarGraphTargetWidth = barGraphTargetWidth;
        mBarColorResId = barColorResId;
        initializeView();
    }

    public BarGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    private LinearLayout.LayoutParams mLayoutParams;
    private View mBarGraphView;
    private TextView mCountTextView;
    private WidthResizeAnimation mWidthResizeAnimation;
    private int mBarGraphValue;
    private int mBarGraphTargetWidth;
    private int mBarColorResId;

    private void initializeView() {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150));
        setOrientation(HORIZONTAL);
        mBarGraphView = new View(getContext());
        mCountTextView = new TextView(getContext());
        mWidthResizeAnimation = new WidthResizeAnimation(mBarGraphView, 0, mBarGraphTargetWidth);
        mWidthResizeAnimation.setDuration(1000);

        mBarGraphView.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT));
        mLayoutParams = (LinearLayout.LayoutParams) mBarGraphView.getLayoutParams();
        mLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        mBarGraphView.setBackgroundColor(getResources().getColor(mBarColorResId));
        addView(mBarGraphView);

//        mCountTextView.setGravity(CENTER_VERTICAL);
        addView(mCountTextView);
        mLayoutParams = (LinearLayout.LayoutParams) mCountTextView.getLayoutParams();
        mLayoutParams.gravity = Gravity.CENTER_VERTICAL;

        mBarGraphView.startAnimation(mWidthResizeAnimation);
        animateTextView(0, mBarGraphValue, mCountTextView);

    }

    private void animateTextView(int initialValue, final int finalValue, final TextView textView) {
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(0.8f);
        int start = Math.min(initialValue, finalValue);
        int end = Math.max(initialValue, finalValue);
        int difference = Math.abs(finalValue - initialValue);
        Handler handler = new Handler();
        for (int i = start; i <= end; i++) {
            int time = Math.round(decelerateInterpolator.getInterpolation((((float) i) / difference)) * 50) * i;
            final int finalCount = ((initialValue > finalValue) ? initialValue - i : i);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText(highlightText(finalCount, mBarColorResId));
                }
            }, time);
        }
    }

    private SpannableString highlightText(int count, int colorResId) {
        SpannableString spannableString = new SpannableString(
                getContext().getString(R.string.total_count_format, String.valueOf(count)));
        int index = String.valueOf(count).length();
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(colorResId)),
                0, index, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(1.5f),
                0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
