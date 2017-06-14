package com.example.android.rotateimageviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

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
            mIndicatorType = a.getInt(R.styleable.RotateImageViewPager_indicatorType, -1);
        } finally {
            a.recycle();
        }

        initializeView(context);
    }

    private int mTitleIconResId;
    private String mTitle;
    private int mIndicatorType;

    private View mRootView;
    private ViewPager mRotateImageViewPager;
    private LinearLayout mTitleLayout;
    private ImageView mTitleIconImageView;
    private TextView mTitleTextView;
    private LinearLayout mIndicatorLayout;
    private ArrayList<TextView> mIndicatorTextViews;

    private RotateImageViewPagerAdapter mImageViewPagerAdapter;
    private RotateImageViewPagerPageChangeListener mImageViewPagerPageChangeListener;

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

    public void setImageViewPagerAdapter(ArrayList<String> imagePaths) {
        if (mImageViewPagerAdapter == null) {
            mImageViewPagerAdapter = new RotateImageViewPagerAdapter(imagePaths);
        }
        mRotateImageViewPager.setAdapter(mImageViewPagerAdapter);
        setViewPagerIndicator(imagePaths.size());
    }

    private void setViewPagerIndicator(int size) {
        mIndicatorTextViews = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TextView indicatorTextView = new TextView(getContext());
//            indicatorTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
//            indicatorTextView.setText("\u0020\u0020\u0020\u0020");
            indicatorTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            specifyIndicatorSize(indicatorTextView);
            if (i != size - 1) {
                LinearLayout.LayoutParams params =
                        (LinearLayout.LayoutParams) indicatorTextView.getLayoutParams();
                params.setMarginEnd(20);
                indicatorTextView.setLayoutParams(params);
            }
            mIndicatorLayout.addView(indicatorTextView);
            mIndicatorTextViews.add(indicatorTextView);
        }
        setIndicatorBackground(0);
    }

    public void addOnImagePageChangeListener() {
        if (mImageViewPagerPageChangeListener == null) {
            mImageViewPagerPageChangeListener = new RotateImageViewPagerPageChangeListener();
        }
        mRotateImageViewPager.addOnPageChangeListener(mImageViewPagerPageChangeListener);
    }

    public void removeOnImagePageChangeListener() {
        if (mImageViewPagerPageChangeListener != null) {
            mRotateImageViewPager.removeOnPageChangeListener(mImageViewPagerPageChangeListener);
        }
    }

    private class RotateImageViewPagerAdapter extends PagerAdapter {

        private ArrayList<String> mImagePaths;
        private LayoutInflater mLayoutInflater;

        public RotateImageViewPagerAdapter(ArrayList<String> imagePaths) {
            mImagePaths = imagePaths;
            mLayoutInflater = LayoutInflater.from(getContext());
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mLayoutInflater.inflate(R.layout.layout_rotate_image_view, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.rotate_image_view);

            Glide.with(getContext())
                    .load(mImagePaths.get(position))
                    .into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mImagePaths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    private class RotateImageViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setIndicatorBackground(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void specifyIndicatorSize(TextView indicatorTextView) {
        indicatorTextView.getLayoutParams().width =
                (int) getResources().getDimension(R.dimen.rotate_view_pager_indicator_width);
        indicatorTextView.getLayoutParams().height =
                (int) getResources().getDimension(R.dimen.rotate_view_pager_indicator_height);
    }

    private void setIndicatorBackground(int position) {
        for (int i = 0; i < mIndicatorTextViews.size(); i++) {
            if (i == position) {
                mIndicatorTextViews.get(i)
                        .setBackgroundResource(R.drawable.widget_indicator_circle_true);
            } else {
                mIndicatorTextViews.get(i)
                        .setBackgroundResource(R.drawable.widget_indicator_circle_false);
            }
        }
    }
}
