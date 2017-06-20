package com.example.android.customscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private LinearLayout mHeaderLayout;
    private LinearLayout mToolbarLayout;
    private CustomScrollView mCustomScrollView;
    private LinearLayout mTargetLayout;
    private TextView mTargetTextView;
    private LinearLayout mTarget2Layout;
    private boolean isTargetSelected;
    private boolean isTarget2Selected;

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
        mTargetTextView = (TextView) findViewById(R.id.target_text_view);
        mTarget2Layout = (LinearLayout) findViewById(R.id.target2_layout);
        isTargetSelected = false;
        isTarget2Selected = false;
        mLayoutInflater = LayoutInflater.from(getApplicationContext());
        mLayoutInflater2 = LayoutInflater.from(getApplicationContext());
        mAddView = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_target, mHeaderLayout, false);

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
                        mTargetLayout.removeView(mTargetTextView);
                        isTargetSelected = true;
                    } else {
                        mHeaderLayout.animate().translationY(-mToolbarLayout.getMeasuredHeight())
                                .setInterpolator(new AccelerateInterpolator(3));
                    }
                }
                if (currentYOffset < (int) targetLayoutPositionY - headerLayoutMeasuredHeight) {
                    if (isTargetSelected) {
//                        Toast.makeText(getApplicationContext(), "target released", Toast.LENGTH_SHORT).show();
                        mHeaderLayout.removeView(mAddView);
                        mTargetLayout.addView(mTargetTextView);
                        isTargetSelected = false;
                    } else {
                        mHeaderLayout.animate().translationY(0)
                                .setInterpolator(new AccelerateInterpolator(3));
                    }
                }

                float target2LayoutPositionY = mTarget2Layout.getY();
                int headerLayout2MeasuredHeight = 0;
                if (isTargetSelected) {
                    if (!isTarget2Selected) {
                        Log.i(TAG, "isTarget2Selected: " + isTarget2Selected);
                        headerLayout2MeasuredHeight = mHeaderLayout.getMeasuredHeight()
                                - mToolbarLayout.getMeasuredHeight();
                        Log.i(TAG, "headerLayout2MeasuredHeight: " + headerLayout2MeasuredHeight);
                    } else {
                        Log.i(TAG, "isTarget2Selected: " + isTarget2Selected);
                        headerLayout2MeasuredHeight = mHeaderLayout.getMeasuredHeight()
                                - mToolbarLayout.getMeasuredHeight() - mAddView2.getMeasuredHeight();
                        Log.i(TAG, "headerLayout2MeasuredHeight: " + headerLayout2MeasuredHeight);
                    }
                }

                if (currentYOffset >= (int) target2LayoutPositionY - headerLayout2MeasuredHeight) {
                    if (!isTarget2Selected) {
                        mAddView2 = (LinearLayout) mLayoutInflater2.inflate(R.layout.layout_target_2, mHeaderLayout, false);
                        mHeaderLayout.addView(mAddView2);
                        isTarget2Selected = true;
                    }
                }

                if (currentYOffset < (int) target2LayoutPositionY - headerLayout2MeasuredHeight) {
                    if (isTarget2Selected) {
                        mHeaderLayout.removeView(mAddView2);
                        isTarget2Selected = false;
                    }
                }
            }
        });
    }
}
