package com.android.sample.customalertdialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yoon on 2017. 7. 11..
 */

public class CustomAlertDialogFragment extends DialogFragment {

    private static final String TAG = CustomAlertDialogFragment.class.getSimpleName();

    public static final String A_REQUEST_DIALOG = "a_request_dialog";
    public static final String B_REQUEST_DIALOG = "b_request_dialog";
    public static final String C_REQUEST_DIALOG = "c_request_dialog";
    public static final String D_REQUEST_DIALOG = "d_request_dialog";

    private static final String ARG_REQUEST_CODE = "request_code";
    private static final String ARG_TITLE_RES_ID = "title_res_id";
    private static final String ARG_DESCRIPTION_RES_ID = "description_res_id";
    private static final String ARG_POSITIVE_RES_ID = "positive_res_id";
    private static final String ARG_NEGATIVE_RES_ID = "negative_res_id";

    public static CustomAlertDialogFragment newInstance() {

        Bundle args = new Bundle();

        CustomAlertDialogFragment fragment = new CustomAlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CustomAlertDialogFragment newInstance(int requestCode,
            int titleResId, int descriptionResId, int positiveMessageResId, int negativeMessageResId) {

        Bundle args = new Bundle();
        args.putInt(ARG_REQUEST_CODE, requestCode);
        args.putInt(ARG_TITLE_RES_ID, titleResId);
        args.putInt(ARG_DESCRIPTION_RES_ID, descriptionResId);
        args.putInt(ARG_POSITIVE_RES_ID, positiveMessageResId);
        args.putInt(ARG_NEGATIVE_RES_ID, negativeMessageResId);

        CustomAlertDialogFragment fragment = new CustomAlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private AlertDialog.Builder mBuilder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextView mTitleTextView, mDescriptionTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_custom_alert_dialog, null);

        mTitleTextView = (TextView) view.findViewById(R.id.alert_dialog_title_text_view);
        mDescriptionTextView = (TextView) view.findViewById(R.id.alert_dialog_description_text_view);

        int titleResId = getArguments().getInt(ARG_TITLE_RES_ID, -1);
        int descriptionResId = getArguments().getInt(ARG_DESCRIPTION_RES_ID, -1);

        if (titleResId != 0) {
            mTitleTextView.setText(getString(titleResId));
        } else {

        }

        if (descriptionResId != 0) {
            mDescriptionTextView.setText(getString(descriptionResId));
        } else {
            mDescriptionTextView.setVisibility(View.GONE);
        }

        mBuilder = new AlertDialog.Builder(getActivity()).setView(view);
        return mBuilder.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.widget_solid_rectangle_white);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
