package com.example.android.rotateimageviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RotateImageViewPager mRotateImageViewPager1;
    private RotateImageViewPager mRotateImageViewPager2;
    private RotateImageViewPager mCardInfiniteRotateImageViewPager;

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

        mCardInfiniteRotateImageViewPager =
                (RotateImageViewPager) findViewById(R.id.card_rotate_image_view_pager);
        mCardInfiniteRotateImageViewPager.setInfiniteRotateViewPagerAdapter(setCardItems(7));

    }

    @Override
    protected void onStart() {
        super.onStart();
        mRotateImageViewPager1.addOnImagePageChangeListener();
        mRotateImageViewPager2.addOnImagePageChangeListener();

        mRotateImageViewPager1.startRotateViewPager();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRotateImageViewPager1.removeOnImagePageChangeListener();
        mRotateImageViewPager2.removeOnImagePageChangeListener();

        mRotateImageViewPager1.stopRotateViewPager();
    }

    private ArrayList<String> setImageUrl(int imageCount) {

        ArrayList<String> imagePaths = new ArrayList<>();
        for (int i = 0; i < imageCount; i++) {
            imagePaths.add("https://unsplash.it/360/220/?random");
        }
        return imagePaths;
    }

    private ArrayList<CardItem> setCardItems(int cardSize) {

        ArrayList<CardItem> cardItems = new ArrayList<>();
        for (int i = 0; i < cardSize; i++) {
            CardItem cardItem = new CardItem();
            cardItem.setImagePath("https://unsplash.it/360/220/?random");
            cardItem.setTitle("title : " + i);
            cardItem.setDescription("description : " + i);
            cardItems.add(cardItem);
        }
        return cardItems;
    }
}
