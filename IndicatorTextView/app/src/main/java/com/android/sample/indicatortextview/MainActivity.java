package com.android.sample.indicatortextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IndicatorTextView mIndicatorTextView;
    private int mCurrentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.add_button).setOnClickListener(this);
        findViewById(R.id.sub_button).setOnClickListener(this);

        mIndicatorTextView = (IndicatorTextView) findViewById(R.id.indicator_text_view);
        mIndicatorTextView.setPageSize(10);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                addNumber();
                break;
            case R.id.sub_button:
                subNumber();
                break;
        }
    }

    private void addNumber() {
        mIndicatorTextView.setCurrentPage(++mCurrentNumber);
    }

    private void subNumber() {
        mIndicatorTextView.setCurrentPage(--mCurrentNumber);
    }
}
