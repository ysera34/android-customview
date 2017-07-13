package com.android.sample.bargraphview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by yoon on 2017. 7. 11..
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = MainFragment.class.getSimpleName();

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private LinearLayout mResultLayout;
    private ArrayList<EditText> mHazardEditTexts;
    private ArrayList<Integer> mHazards;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHazardEditTexts = new ArrayList<>();
        mHazards = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.start_button).setOnClickListener(this);
        view.findViewById(R.id.compound_view_start_button).setOnClickListener(this);
        mResultLayout = (LinearLayout) view.findViewById(R.id.result_layout);
        mHazardEditTexts.add((EditText) view.findViewById(R.id.hazard1_edit_text));
        mHazardEditTexts.add((EditText) view.findViewById(R.id.hazard2_edit_text));
        mHazardEditTexts.add((EditText) view.findViewById(R.id.hazard3_edit_text));
        mHazardEditTexts.add((EditText) view.findViewById(R.id.hazard4_edit_text));
    }

    @Override
    public void onClick(View v) {
        if (mHazards.size() > 0) {
            mHazards.clear();
        }
        for (int i = 0; i < mHazardEditTexts.size(); i++) {
            String hazardValue = mHazardEditTexts.get(i).getText().toString();
            Log.i(TAG, "Hazard" + (i + 1) + ": " + hazardValue);
            if (hazardValue.equals("")) {
                hazardValue = "0";
            }
            mHazards.add(Integer.valueOf(hazardValue));
        }
        switch (v.getId()) {
            case R.id.start_button:
                calculateHazard();
                break;
            case R.id.compound_view_start_button:
                addHazardView();
                break;
        }
    }

    private void calculateHazard() {
        int sum = 0;
        for (int i = 0; i < mHazards.size(); i++) {
            Log.i(TAG, "mHazards: integer: " + mHazards.get(i));
            sum += mHazards.get(i);
        }
        Log.i(TAG, "sum: " + sum);

        int hazardColorResIdArr[] = {
                R.color.hazard1, R.color.hazard2, R.color.hazard3, R.color.hazard4,};

        WidthResizeAnimation widthResizeAnimation;

        mResultLayout.removeAllViews();
        for (int i = 0; i < mHazards.size(); i++) {
            View view = new View(getContext());
            view.setBackgroundColor(getResources().getColor(hazardColorResIdArr[i]));
//            view.setLayoutParams(new LinearLayout.LayoutParams(
//                    getPixelFromDp(getHazardViewWidth(360, mHazards.get(i), sum)), getPixelFromDp(10)));
            view.setLayoutParams(new LinearLayout.LayoutParams(getPixelFromDp(0), getPixelFromDp(10)));
            widthResizeAnimation = new WidthResizeAnimation(view, 0, getPixelFromDp(getHazardViewWidth(360, mHazards.get(i), sum)));
            widthResizeAnimation.setDuration(1000);
            mResultLayout.addView(view);
            view.startAnimation(widthResizeAnimation);
        }
    }

    private void addHazardView() {
        int sum = 0;
        for (int i = 0; i < mHazards.size(); i++) {
            Log.i(TAG, "mHazards: integer: " + mHazards.get(i));
            sum += mHazards.get(i);
        }
        Log.i(TAG, "sum: " + sum);

        int hazardColorResIdArr[] = {
                R.color.hazard1, R.color.hazard2, R.color.hazard3, R.color.hazard4,};

        for (int i = 0; i < mHazards.size(); i++) {
            BarGraphView barGraphView = new BarGraphView(getContext(),
                    mHazards.get(i), getPixelFromDp(getHazardViewWidth(360, mHazards.get(i), sum)), hazardColorResIdArr[i]);
            mResultLayout.addView(barGraphView);
        }
    }

    private int getHazardViewWidth(int maxLength, int hazardCount, int allCount) {
        return maxLength * hazardCount / allCount;
    }

    private int getPixelFromDp(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
