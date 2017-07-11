package com.android.sample.customalertdialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.android.sample.customalertdialog.CustomAlertDialogFragment.A_REQUEST_DIALOG;

/**
 * Created by yoon on 2017. 7. 11..
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Button mButtonA;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mButtonA = (Button) view.findViewById(R.id.button_a);
        mButtonA.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_a:
                CustomAlertDialogFragment alertDialogFragment = CustomAlertDialogFragment.newInstance();
                alertDialogFragment.show(getChildFragmentManager(), A_REQUEST_DIALOG);
                break;
        }
    }
}
