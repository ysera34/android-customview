package com.example.android.rotateimageviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by yoon on 2017. 6. 15..
 */

public class CardView extends LinearLayout {

    public CardView(Context context, CardItem cardItem) {
        super(context);
        mCardItem = cardItem;
        initializeView(context);
    }

    public CardView(Context context, AttributeSet attrs, CardItem cardItem) {
        super(context, attrs);
        mCardItem = cardItem;
        initializeView(context);
    }

    private CardItem mCardItem;
    private View mCardLayout;
    private ImageView mCardImageView;
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;

    private void initializeView(Context context) {
        mCardLayout = inflate(context, R.layout.list_item_card, this);
        mCardImageView = (ImageView) mCardLayout.findViewById(R.id.list_item_card_image_view);
        mTitleTextView = (TextView) mCardLayout.findViewById(R.id.list_item_card_title_text_view);
        mDescriptionTextView = (TextView) mCardLayout.findViewById(R.id.list_item_card_description_text_view);

        Glide.with(getContext())
                .load(mCardItem.getImagePath())
                .into(mCardImageView);
        mTitleTextView.setText(String.valueOf(mCardItem.getTitle()));
        mDescriptionTextView.setText(String.valueOf(mCardItem.getDescription()));
    }
}
