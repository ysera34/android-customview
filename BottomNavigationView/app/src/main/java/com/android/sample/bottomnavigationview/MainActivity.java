package com.android.sample.bottomnavigationview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView mBottomNavigationView;
    private LinearLayout mBottomNavigationLayout;
    private BottomNavigationItemView mBottomNavigationItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomNavigationView.setBottomNavigationItems(getBottomNavigationItems());
//        mBottomNavigationItemView = (BottomNavigationItemView) findViewById(R.id.bottom_navigation_item_view);

//        mBottomNavigationLayout = (LinearLayout) findViewById(R.id.bottom_navigation_layout);
//        BottomNavigationView bottomNavigationView = new BottomNavigationView(getApplicationContext());
//        bottomNavigationView.setBottomNavigationItems(getBottomNavigationItems());
//        mBottomNavigationLayout.addView(bottomNavigationView);
    }

    private ArrayList<BottomNavigationItem> getBottomNavigationItems() {
        ArrayList<BottomNavigationItem> items = new ArrayList<>();

        int[] titleResIds = {R.string.menu_title_1, R.string.menu_title_2, R.string.menu_title_3,};
        int[] imageResIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};

        for (int i = 0; i < 3; i++) {
            BottomNavigationItem item = new BottomNavigationItem();
            item.setTitleResId(titleResIds[i]);
            item.setImageResId(imageResIds[i]);
            items.add(item);
        }
        return items;
    }
}
