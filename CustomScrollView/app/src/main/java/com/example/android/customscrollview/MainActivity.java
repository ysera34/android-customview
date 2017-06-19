package com.example.android.customscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private LinearLayout mHeaderLayout;
    private LinearLayout mToolbarLayout;
    private CustomScrollView mCustomScrollView;
    private LinearLayout mTargetLayout;
    private LinearLayout mTarget2Layout;
    private boolean isTargetSelected;

    private LayoutInflater mLayoutInflater;
    private LayoutInflater mLayoutInflater2;
    private LinearLayout mAddView;
    private LinearLayout mAddView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHeaderLayout = (LinearLayout) findViewById(R.id.header_layout);
        mToolbarLayout = (LinearLayout) findViewById(R.id.toolbar_layout);
        mCustomScrollView = (CustomScrollView) findViewById(R.id.custom_scroll_view);
        mTargetLayout = (LinearLayout) findViewById(R.id.target_layout);
        mTarget2Layout = (LinearLayout) findViewById(R.id.target2_layout);
        isTargetSelected = false;
        mLayoutInflater = LayoutInflater.from(getApplicationContext());
        mLayoutInflater2 = LayoutInflater.from(getApplicationContext());
        mAddView = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_target, mHeaderLayout, false);
        mAddView2 = (LinearLayout) mLayoutInflater2.inflate(R.layout.layout_target_2, mHeaderLayout, false);

        mCustomScrollView.setOnScrollChangedListener(new CustomScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(CustomScrollView scrollView, int l, int t) {
//                float value = (float) l / view.getMaxScrollAmount();
//                Log.i(TAG, "getMaxScrollAmount : " + value);

//                View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
//                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
//                Log.i(TAG, "diff: " + diff);
//                if diff is zero, then the bottom has been reached
//                if (diff == 0) {
//                }

                int currentYOffset = scrollView.getScrollY();
                Log.i(TAG, "current Y Offset : " + currentYOffset);

                float targetLayoutPositionY = mTargetLayout.getY();
                Log.i(TAG, "targetLayoutPositionY : " + targetLayoutPositionY);

                int headerLayoutMeasuredHeight;
                if (!isTargetSelected) {
                    headerLayoutMeasuredHeight = mHeaderLayout.getMeasuredHeight();
                } else {
                    headerLayoutMeasuredHeight = mHeaderLayout.getMeasuredHeight() - mAddView.getMeasuredHeight();
                }
                Log.i(TAG, "headerLayoutMeasuredHeight : " + headerLayoutMeasuredHeight);

                if (currentYOffset >= (int) targetLayoutPositionY - headerLayoutMeasuredHeight) {
                    if (!isTargetSelected) {
//                        Toast.makeText(getApplicationContext(), "target selected", Toast.LENGTH_SHORT).show();
                        mHeaderLayout.addView(mAddView);
                        isTargetSelected = true;
                    } else {
                        mHeaderLayout.animate().translationY(-mToolbarLayout.getMeasuredHeight())
                                .setInterpolator(new AccelerateInterpolator(2));
                    }
                }
                if (currentYOffset < (int) targetLayoutPositionY - headerLayoutMeasuredHeight) {
                    if (isTargetSelected) {
//                        Toast.makeText(getApplicationContext(), "target released", Toast.LENGTH_SHORT).show();
                        mHeaderLayout.removeView(mAddView);
                        isTargetSelected = false;
                    } else {
                        mHeaderLayout.animate().translationY(0)
                                .setInterpolator(new AccelerateInterpolator(2));
                    }
                }

                float target2LayoutPositionY = mTarget2Layout.getY();
//                int headerLayout2MeasuredHeight = mHeaderLayout.getMeasuredHeight();
                int headerLayout2MeasuredHeight = mHeaderLayout.getMeasuredHeight() - mAddView.getMeasuredHeight();

                if (isTargetSelected) {
                    if (currentYOffset >= (int) target2LayoutPositionY - headerLayout2MeasuredHeight) {
                        mHeaderLayout.addView(mAddView2, mHeaderLayout.getChildCount());
                    }
                }
            }
        });
    }
}
