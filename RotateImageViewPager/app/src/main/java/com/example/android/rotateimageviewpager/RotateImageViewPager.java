package com.example.android.rotateimageviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by yoon on 2017. 6. 14..
 */

public class RotateImageViewPager extends RelativeLayout {

    public RotateImageViewPager(Context context) {
        super(context);
        initializeView(context);
    }

    public RotateImageViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RotateImageViewPager,
                0, 0
        );

        try {
            mTitleIconResId = a.getResourceId(R.styleable.RotateImageViewPager_titleIcon, 0);
            mTitle = a.getNonResourceString(R.styleable.RotateImageViewPager_title);
            mIndicatorPositionId = a.getInt(R.styleable.RotateImageViewPager_indicatorPosition, -1);
        } finally {
            a.recycle();
        }

        initializeView(context);
    }

    private int mTitleIconResId;
    private String mTitle;
    private int mIndicatorPositionId;

    private View mRootView;
    private ViewPager mRotateImageViewPager;
    private LinearLayout mTitleLayout;
    private ImageView mTitleIconImageView;
    private TextView mTitleTextView;
    private LinearLayout mIndicatorLayout;

    private void initializeView(Context context) {
        mRootView = inflate(context, R.layout.layout_rotate_image_view_pager, this);
        mRotateImageViewPager = (ViewPager) mRootView.findViewById(R.id.rotate_image_view_pager);
        mTitleLayout = (LinearLayout) mRootView.findViewById(R.id.title_layout);
        mTitleIconImageView = (ImageView) mRootView.findViewById(R.id.title_icon_image_view);
        mTitleIconImageView.setImageResource(mTitleIconResId);
        mTitleTextView = (TextView) mRootView.findViewById(R.id.title_text_view);
        mTitleTextView.setText(String.valueOf(mTitle));
        mIndicatorLayout = (LinearLayout) mRootView.findViewById(R.id.indicator_layout);
    }

}
