package com.demoapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.demoapp.ui.demoapp.R;

public class OmniPayFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder omniPayDialog = new AlertDialog.Builder(getActivity());
        omniPayDialog.setTitle(R.string.dialog_title);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_omni_pay, null);
        omniPayDialog.setView(view);
        omniPayDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        omniPayDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return omniPayDialog.create();

    }

}
