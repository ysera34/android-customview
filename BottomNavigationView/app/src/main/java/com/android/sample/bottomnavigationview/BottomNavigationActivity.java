package com.android.sample.bottomnavigationview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class BottomNavigationActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemClickListener {

    private static final String TAG = BottomNavigationActivity.class.getSimpleName();

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mBottomNavigationView.setBottomNavigationItemViews(getBottomNavigationItems());
        mBottomNavigationView.setNavigationItemClickListener(this);
//        mBottomNavigationView.setSelectedItemView(0);

        mBottomNavigationView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBottomNavigationView.setSelectedItemView(0);
            }
        }, 300);
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

    @Override
    public void onNavigationItemClick(int position) {
        switch (position) {
            case 0:
                Toast.makeText(getApplicationContext(), "position0", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "position1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "position2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getApplicationContext(), "position3", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
