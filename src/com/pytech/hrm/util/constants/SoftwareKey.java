package com.pytech.hrm.util.constants;

import org.apache.commons.lang3.StringUtils;

import com.pytech.hrm.R;

public class SoftwareKey {
	public static final String MAYA_KEY = "Maya";
	public static final String BLENDER_KEY = "Blender";
	public static final String MAX_KEY = "MAX";
	
	public static int convertIconImage(String product) {
		int icon = R.drawable.icon_hrm;
		if(StringUtils.containsIgnoreCase(product, MAYA_KEY)) {
			icon = R.drawable.logo_maya;
		} else if(StringUtils.containsIgnoreCase(product, BLENDER_KEY)) {
			icon = R.drawable.logo_blender;
		} else if(StringUtils.containsIgnoreCase(product, MAX_KEY)) {
			icon = R.drawable.logo_3dsmax;
		}
		return icon;
	}
}
