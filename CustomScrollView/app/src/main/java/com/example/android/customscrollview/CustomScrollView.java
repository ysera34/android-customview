package com.example.android.customscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by yoon on 2017. 6. 17..
 */

public class CustomScrollView extends ScrollView {

    public interface OnScrollChangedListener {
        /**
         * Called when the scroll position of <code>view</code> changes.
         *
         * @param view The view whose scroll position changed.
         * @param l Current horizontal scroll origin.
         * @param t Current vertical scroll origin.
         */
        void onScrollChanged(CustomScrollView view, int l, int t);
    }

    private OnScrollChangedListener mScrollChangedListener;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener scrollChangedListener) {
        mScrollChangedListener = scrollChangedListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollChangedListener != null) {
            mScrollChangedListener.onScrollChanged(this, l, t);
        }
    }
}
