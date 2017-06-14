package com.example.android.rotateimageviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RotateImageViewPager mRotateImageViewPager1;
    private RotateImageViewPager mRotateImageViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRotateImageViewPager1 =
                (RotateImageViewPager) findViewById(R.id.content_banner_rotate_image_view_pager);
        mRotateImageViewPager1.setImageViewPagerAdapter(setImageUrl(6));

        mRotateImageViewPager2 =
                (RotateImageViewPager) findViewById(R.id.review_rotate_image_view_pager);
        mRotateImageViewPager2.setImageViewPagerAdapter(setImageUrl(4));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRotateImageViewPager1.addOnImagePageChangeListener();
        mRotateImageViewPager2.addOnImagePageChangeListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRotateImageViewPager1.removeOnImagePageChangeListener();
        mRotateImageViewPager2.removeOnImagePageChangeListener();
    }

    private ArrayList<String> setImageUrl(int imageCount) {

        ArrayList<String> imagePaths = new ArrayList<>();
        for (int i = 0; i < imageCount; i++) {
            imagePaths.add("https://unsplash.it/360/220/?random");
        }
        return imagePaths;
    }
}
