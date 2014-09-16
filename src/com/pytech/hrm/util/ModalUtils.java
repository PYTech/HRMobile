package com.pytech.hrm.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

public class ModalUtils {
	public static void showAlarm(Context context, String title, String message, OnClickListener positiveListener) {
		// Show alert dialog.
		Builder alertBuilder = new Builder(context);
		alertBuilder.setPositiveButton(android.R.string.ok, positiveListener);
		AlertDialog alert = alertBuilder.create();
		alert.setCancelable(true);
		alert.setCanceledOnTouchOutside(false);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.show();
	}
}
