package com.android.sample.bottomnavigationview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by yoon on 2017. 6. 24..
 */

public class BottomNavigationVIew extends LinearLayout {

    public BottomNavigationVIew(Context context) {
        super(context);
        initializeView(context);
    }

    public BottomNavigationVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    private View mRootView;

    private void initializeView(Context context) {
        mRootView = inflate(context, R.layout.layout_bottom_navigation, this);
    }


}
