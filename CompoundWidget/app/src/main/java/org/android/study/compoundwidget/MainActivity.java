package org.android.study.compoundwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CardView cardView01;
    CardView cardView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardView01 = (CardView) findViewById(R.id.view01);
        cardView02 = (CardView) findViewById(R.id.view02);
        initData();
    }

    private void initData() {
        DataVO dataVO = new DataVO("ysera", 34);
        cardView01.setCardData(dataVO);
        dataVO.setImageID(22);
        dataVO.setName("Kershaw");
        cardView02.setCardData(dataVO);
    }
}
