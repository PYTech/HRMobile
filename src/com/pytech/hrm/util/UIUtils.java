package com.pytech.hrm.util;

import android.view.View;

public class UIUtils {
	public static void setEditable(View view, boolean editable) {
		view.setFocusable(editable);
		view.setClickable(editable);
	}
}
