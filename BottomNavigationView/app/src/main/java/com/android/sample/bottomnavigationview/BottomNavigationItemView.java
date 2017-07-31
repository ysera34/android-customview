package com.android.sample.bottomnavigationview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yoon on 2017. 7. 24..
 */

public class BottomNavigationItemView extends LinearLayout {

    public BottomNavigationItemView(Context context) {
        super(context, null);
    }

    public BottomNavigationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initializeView();
    }

    public BottomNavigationItemView(Context context, BottomNavigationItem item) {
        this(context);
        mContext = context;
        mItem = item;
        initializeView();
    }

    private Context mContext;
    private LinearLayout mItemView;
    private LinearLayout.LayoutParams mLayoutParams;

    private BottomNavigationItem mItem;
    private ImageView mIconImageView;
    private TextView mTitleTextView;
    private TextView mBadgeTextView;

    private void initializeView() {
        mItemView = (LinearLayout) inflate(mContext, R.layout.layout_bottom_navigation_item, this);
        mLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mItemView.setLayoutParams(mLayoutParams);
        mLayoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        mLayoutParams.width = 0;
        mLayoutParams.height = getResources().getDimensionPixelSize(R.dimen.bottom_navigation_item_height);
        mLayoutParams.weight = 1;
        // layout_gravity
        mLayoutParams.gravity = Gravity.CENTER;
        // (child view) gravity
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);

        mIconImageView = (ImageView) mItemView.findViewById(R.id.item_icon_image_view);
        mTitleTextView = (TextView) mItemView.findViewById(R.id.item_title_text_view);
        mBadgeTextView = (TextView) mItemView.findViewById(R.id.item_badge_text_view);

        if (mItem == null) {
            mTitleTextView.setText(R.string.menu_title_1);
            mIconImageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            mIconImageView.setImageResource(mItem.getImageResId());
            mTitleTextView.setText(mContext.getResources().getString(mItem.getTitleResId()));
            mTitleTextView.setVisibility(View.GONE);
            mBadgeTextView.setText(String.valueOf(10));
        }
    }

    public void setSelectedItemView(boolean isSelected) {
        if (isSelected) {
            mTitleTextView.setVisibility(View.VISIBLE);
        } else {
            mTitleTextView.setVisibility(View.GONE);
        }
    }

    public ImageView getIconImageView() {
        return mIconImageView;
    }

    public void setIconImageView(ImageView iconImageView) {
        mIconImageView = iconImageView;
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        mTitleTextView = titleTextView;
    }

    public TextView getBadgeTextView() {
        return mBadgeTextView;
    }

    public void setBadgeTextView(TextView badgeTextView) {
        mBadgeTextView = badgeTextView;
    }
}
