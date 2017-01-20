package com.example.android.combinehexagonview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HexagonFilterView[] mHexagonFilterViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHexagonFilterViews = new HexagonFilterView[5];

        int[] filterViewIds = new int[]{
                R.id.hexagon1, R.id.hexagon2, R.id.hexagon3, R.id.hexagon4, R.id.hexagon5
        };

        for (int i = 0; i < mHexagonFilterViews.length; i++) {
            mHexagonFilterViews[i] = (HexagonFilterView) findViewById(filterViewIds[i]);
            mHexagonFilterViews[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hexagon1:
                arrangeHexagonFilterViews(0);
                break;
            case R.id.hexagon2:
                arrangeHexagonFilterViews(1);
                break;
            case R.id.hexagon3:
                arrangeHexagonFilterViews(2);
                break;
            case R.id.hexagon4:
                arrangeHexagonFilterViews(3);
                break;
            case R.id.hexagon5:
                arrangeHexagonFilterViews(4);
                break;
        }
    }

    private void arrangeHexagonFilterViews(int index) {

        for (int i = 0; i < mHexagonFilterViews.length; i++) {
            if (i == index) {
                mHexagonFilterViews[i].scaleUpAnimate();
            } else {
                mHexagonFilterViews[i].scaleDownAnimate();
            }
        }
    }
}
