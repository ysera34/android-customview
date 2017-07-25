package com.android.sample.bottomnavigationview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by yoon on 2017. 6. 24..
 */

public class BottomNavigationView extends LinearLayout {

    private static String TAG = BottomNavigationView.class.getSimpleName();

    public BottomNavigationView(Context context) {
        super(context);
        initializeView(context);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    private Context mContext;

    private void initializeView(Context context) {
        mContext = context;
        setOrientation(HORIZONTAL);
    }

    public void setBottomNavigationItems(ArrayList<BottomNavigationItem> items) {
        for (int i = 0; i < items.size(); i++) {
            BottomNavigationItemView itemView = new BottomNavigationItemView(mContext, items.get(i));
            addView(itemView);
        }
    }

}
