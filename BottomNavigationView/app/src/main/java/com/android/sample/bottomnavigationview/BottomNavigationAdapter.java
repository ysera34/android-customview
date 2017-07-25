package com.android.sample.bottomnavigationview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by yoon on 2017. 6. 25..
 */

public class BottomNavigationAdapter extends ArrayAdapter<BottomNavigationItem> {

    private Context mContext;
    private int mLayoutResId;
    private List<BottomNavigationItem> mItems;

    public BottomNavigationAdapter(Context context, List<BottomNavigationItem> items) {
        super(context, R.layout.layout_bottom_navigation_item, items);
    }

    private BottomNavigationAdapter(Context context, int resource, List<BottomNavigationItem> items) {
        super(context, resource, items);
        mContext = context;
        mLayoutResId = resource;
        mItems = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        BottomNavigationItemHolder holder;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(mLayoutResId, parent, false);
            holder = new BottomNavigationItemHolder(view);
            view.setTag(holder);
        } else {
            holder = (BottomNavigationItemHolder) view.getTag();
        }
        holder.bindHolder(getItem(position));
        return view;
    }

    private class BottomNavigationItemHolder implements View.OnClickListener {

        private BottomNavigationItem mItem;

        private ImageView mIconImageView;
        private TextView mTitleTextView;

        public BottomNavigationItemHolder(View view) {
            view.setOnClickListener(this);
            mIconImageView = (ImageView) view.findViewById(R.id.item_icon_image_view);
            mTitleTextView = (TextView) view.findViewById(R.id.item_title_text_view);
        }

        public void bindHolder(BottomNavigationItem item) {
            mItem = item;
            mIconImageView.setImageResource(mItem.getImageResId());
            mTitleTextView.setText(mContext.getResources().getString(mItem.getTitleResId()));
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), mTitleTextView.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
