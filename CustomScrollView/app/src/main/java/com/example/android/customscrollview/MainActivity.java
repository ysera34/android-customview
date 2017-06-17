package com.example.android.customscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CustomScrollView mCustomScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomScrollView = (CustomScrollView) findViewById(R.id.custom_scroll_view);
        mCustomScrollView.setOnScrollChangedListener(new CustomScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(CustomScrollView scrollView, int l, int t) {
//                float value = (float) l / view.getMaxScrollAmount();
//                Log.i(TAG, "getMaxScrollAmount : " + value);

//                View view = scrollView.getChildAt(scrollView.getChildCount() - 1);
//                int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
//                Log.i(TAG, "diff: " + diff);
                // if diff is zero, then the bottom has been reached
//                if (diff == 0) {
//                }

                int currentYOffset = scrollView.getScrollY();
                Log.i(TAG, "current Y Offset : " + currentYOffset);
            }
        });
    }
}
