package com.pytech.hrm.util.constants;

import android.graphics.Color;

public enum Colors {
	CANCELLED("#D3D3D3"), // Grey
	RUNNING("#33B5E5"), // Blue
	PAUSED("#AA66CC"), // Purple
	DONE("#99CC00"), // Green
	WAITING("#FFBB33"), // Orange
	ERROR("#FF4444"); // Red

	private String code;

	private Colors(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public int parseColor() {
		return Color.parseColor(this.code);
	}
	
	public static Colors fromColorId(int id) {
		for(Colors color : values()) {
			if(color.parseColor() == id) {
				return color;
			}
		}
		return CANCELLED;
	}
}
