package com.android.sample.bottomnavigationview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomNavigationView.setBottomNavigationItems(getBottomNavigationItems());
    }

    private ArrayList<BottomNavigationItem> getBottomNavigationItems() {
        ArrayList<BottomNavigationItem> items = new ArrayList<>();

        int[] titleResIds = {R.string.menu_title_1, R.string.menu_title_2,
                R.string.menu_title_3, R.string.menu_title_4,};
        int[] imageResIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher,};

        for (int i = 0; i < imageResIds.length; i++) {
            BottomNavigationItem item = new BottomNavigationItem();
            item.setTitleResId(titleResIds[i]);
            item.setImageResId(imageResIds[i]);
            items.add(item);
        }
        return items;
    }
}
