package com.example.android.rotateimageviewpager;

/**
 * Created by yoon on 2017. 6. 15..
 */

public class CardItem {

    private int mId;
    private String mImagePath;
    private String mTitle;
    private String mDescription;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public void setImagePath(String imagePath) {
        mImagePath = imagePath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
