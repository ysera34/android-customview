package com.android.sample.bottomnavigationview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by yoon on 2017. 6. 24..
 */

public class BottomNavigationView extends LinearLayout {

    public BottomNavigationView(Context context) {
        super(context);
        initializeView(context);
    }

    public BottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    private View mRootView;

    private void initializeView(Context context) {
        mRootView = inflate(context, R.layout.layout_bottom_navigation, this);
    }


}
