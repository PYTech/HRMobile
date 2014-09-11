package com.pytech.hrm.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenceUtils {
	public static SharedPreferences getSharedPref(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public static Editor getEditor(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context).edit();
	}
}
