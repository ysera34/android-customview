package com.android.sample.bottomnavigationview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
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
    private ArrayList<BottomNavigationItem> mNavigationItems;
    private ArrayList<BottomNavigationItemView> mNavigationItemViews;
    private OnNavigationItemClickListener mNavigationItemClickListener;

    private void initializeView(Context context) {
        mContext = context;
        mNavigationItems = new ArrayList<>();
        mNavigationItemViews = new ArrayList<>();
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setBackgroundColor(getResources().getColor(android.R.color.white));
        setElevation(8.0f);
    }

    public void setBottomNavigationItemViews(ArrayList<BottomNavigationItem> items) {
        mNavigationItems = items;
        for (int i = 0; i < mNavigationItems.size(); i++) {
            final BottomNavigationItemView itemView = new BottomNavigationItemView(mContext, mNavigationItems.get(i));
            mNavigationItemViews.add(itemView);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mNavigationItemViews.indexOf(v);
                    getNavigationItemClickListener().onNavigationItemClick(position);
                    setSelectedItemView(position);
//                    for (int j = 0; j < mNavigationItems.size(); j++) {
//                        if (j == position) {
//                            mNavigationItemViews.get(j).setSelectedItemView(true);
//                        } else {
//                            mNavigationItemViews.get(j).setSelectedItemView(false);
//                        }
//                    }
                }
            });
            addView(itemView);
        }
    }

    public void setSelectedItemView(int position) {
        for (int i = 0; i < mNavigationItems.size(); i++) {
            if (i == position) {
                mNavigationItemViews.get(i).setSelectedItemView(true);
            } else {
                mNavigationItemViews.get(i).setSelectedItemView(false);
            }
        }
    }

    public interface OnNavigationItemClickListener {
        void onNavigationItemClick(int position);
    }

    public OnNavigationItemClickListener getNavigationItemClickListener() {
        return mNavigationItemClickListener;
    }

    public void setNavigationItemClickListener(OnNavigationItemClickListener navigationItemClickListener) {
        mNavigationItemClickListener = navigationItemClickListener;
    }
}
