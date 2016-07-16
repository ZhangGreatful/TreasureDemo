package com.example.administrator.treasuredemo.components;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.administrator.treasuredemo.R;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class AlertDialogFragment extends DialogFragment {
    private static final String KEY_TITLE   = "title";
    private static final String KEY_MESSAGE = "message";

    public static AlertDialogFragment newInstance(String title, String msg) {
        AlertDialogFragment fragment = new AlertDialogFragment();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE, title);
        args.putString(KEY_MESSAGE, msg);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(KEY_TITLE);
        String msg = getArguments().getString(KEY_MESSAGE);
        return new AlertDialog.Builder(getActivity(), getTheme())
                .setTitle(title)
                .setMessage(msg)
                .setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
    }
}
