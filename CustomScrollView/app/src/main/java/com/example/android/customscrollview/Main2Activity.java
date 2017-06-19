package com.example.android.customscrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    private LinearLayout mParentLayout;
    private LayoutInflater mLayoutInflater;
    private LinearLayout mChildView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mParentLayout = (LinearLayout) findViewById(R.id.parent_layout);
        mLayoutInflater = LayoutInflater.from(getApplicationContext());
//        mChildView = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_target, null);

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mParentLayout.addView(mChildView, mParentLayout.getChildCount());

                View view = mLayoutInflater.inflate(R.layout.layout_target, null);
                mParentLayout.addView(view);
            }
        });

        findViewById(R.id.remove_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = mParentLayout.getChildAt(mParentLayout.getChildCount() - 1);
                mParentLayout.removeView(view);
            }
        });
    }
}
