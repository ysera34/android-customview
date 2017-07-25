package com.android.sample.bottomnavigationview;

/**
 * Created by yoon on 2017. 6. 25..
 */

public class BottomNavigationItem {

    private int mImageResId;
    private int mTitleResId;

    public int getImageResId() {
        return mImageResId;
    }

    public void setImageResId(int imageResId) {
        mImageResId = imageResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public void setTitleResId(int titleResId) {
        mTitleResId = titleResId;
    }
}
