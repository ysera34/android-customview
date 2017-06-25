package com.android.sample.bottomnavigationview;

/**
 * Created by yoon on 2017. 6. 25..
 */

public class BottomNavigationItem {

    private int mImageResId;
    private String mTitle;

    public int getImageResId() {
        return mImageResId;
    }

    public void setImageResId(int imageResId) {
        mImageResId = imageResId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
