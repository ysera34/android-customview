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

    private RotateImageViewPagerAdapter mImageViewPagerAdapter;

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
    }

    class RotateImageViewPagerAdapter extends PagerAdapter {

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
}
