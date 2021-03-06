package com.example.danilochagov.calc_3000.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.danilochagov.calc_3000.R;
import com.example.danilochagov.calc_3000.helpers.SharPref;

public class ListDialogFragment extends DialogFragment {

    private static final String[] THEMES = {"Green", "Orange", "Blue"};
    private static int sCurrentTheme;
    private SelectThemeListener mSelectThemeListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        sCurrentTheme = new SharPref(getContext()).getCurrentTheme();
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.dialog_list_themes)
                .setCancelable(false)
                .setSingleChoiceItems(THEMES,
                        getArguments().getInt("theme", 0),
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sCurrentTheme = which;
                    }
                })
                .setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(getString(R.string.dialog_accept), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectThemeListener.setSelectThemeListener(sCurrentTheme);
                    }
                });

        return builder.create();
    }

    public void setOnSelectThemeListener(SelectThemeListener selectThemeListener) {
        mSelectThemeListener = selectThemeListener;
    }

    public interface SelectThemeListener {
        void setSelectThemeListener(int theme);
    }

}
