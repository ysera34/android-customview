package org.android.study.compoundwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 26019 on 2016-05-31.
 */

//padding margin을 잡기 위해 framelayout 사용
public class CardView extends FrameLayout {

    public CardView(Context context) {
        super(context);
        init();
    }

    public CardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    ImageView iconView;
    TextView nameView;

    private void init() {
        // inflate 무엇을, 어디다가, child할거니 말거니
        LayoutInflater.from(getContext()).inflate(R.layout.card_layout, this);
        iconView = (ImageView) findViewById(R.id.image_profile);
        nameView = (TextView) findViewById(R.id.text_name);
    }

    private DataVO dataVO;
    public void setCardData(DataVO dataVO) {
        this.dataVO = dataVO;
        iconView.setId(dataVO.getImageID());
        nameView.setText(dataVO.getName());
    }

}
