package com.android.sample.customalertdialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.android.sample.customalertdialog.CustomAlertDialogFragment.A_REQUEST_DIALOG;
import static com.android.sample.customalertdialog.CustomAlertDialogFragment.B_REQUEST_DIALOG;

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

    private Button mButtonA, mButtonB;

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
        mButtonB = (Button) view.findViewById(R.id.button_b);
        mButtonA.setOnClickListener(this);
        mButtonB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_a:
                CustomAlertDialogFragment alertDialogFragmentA =
                        CustomAlertDialogFragment.newInstance(0,R.string.title,R.string.description,0,0);
                alertDialogFragmentA.show(getChildFragmentManager(), A_REQUEST_DIALOG);
                break;
            case R.id.button_b:
                CustomAlertDialogFragment alertDialogFragmentB =
                        CustomAlertDialogFragment.newInstance(0,R.string.title,0,0,0);
                alertDialogFragmentB.show(getChildFragmentManager(), B_REQUEST_DIALOG);
                break;
        }
    }
}
