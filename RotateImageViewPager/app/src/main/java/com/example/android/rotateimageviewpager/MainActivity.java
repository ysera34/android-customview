package com.example.android.rotateimageviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RotateImageViewPager mRotateImageViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRotateImageViewPager =
                (RotateImageViewPager) findViewById(R.id.content_banner_rotate_image_view_pager);
        mRotateImageViewPager.setImageViewPagerAdapter(setImageUrl(6));
    }

    private ArrayList<String> setImageUrl(int imageCount) {

        ArrayList<String> imagePaths = new ArrayList<>();
        for (int i = 0; i < imageCount; i++) {
            imagePaths.add("https://unsplash.it/360/220/?random");
        }
        return imagePaths;
    }
}
