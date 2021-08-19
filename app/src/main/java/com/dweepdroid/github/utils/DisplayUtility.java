package com.dweepdroid.github.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

public class DisplayUtility {

    private ProgressDialog sProgressDialog;

    private static DisplayUtility instance;

    private DisplayUtility() {
    }

    public static DisplayUtility getInstance() {
        if (instance == null) {
            instance = new DisplayUtility();
        }

        return instance;
    }

    public void displayProgressDialog(final Context context, String title, String message) {
        if (sProgressDialog != null && sProgressDialog.isShowing())
            return;

        if (context != null) {
            sProgressDialog = new ProgressDialog(context);
            sProgressDialog.setCancelable(true);
            //sProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            sProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            try {
                sProgressDialog.show();
            }
            catch (WindowManager.BadTokenException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void hideProgressDialog() {
        if (sProgressDialog != null && sProgressDialog.isShowing()) {
            try {
                sProgressDialog.dismiss();

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            sProgressDialog = null;
        }
    }
}
