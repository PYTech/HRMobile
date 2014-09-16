package com.pytech.hrm.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

public class ModalUtils {
	public static void showAlarm(Context context, boolean positiveBtn, boolean cancelable, String title, String message) {
		// Show alert dialog.
		Builder alertBuilder = new Builder(context);
		if(positiveBtn) {
			alertBuilder.setPositiveButton(android.R.string.ok, null);
		}
		AlertDialog alert = alertBuilder.create();
		alert.setCancelable(cancelable);
		alert.setCanceledOnTouchOutside(false);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.show();
	}
}
