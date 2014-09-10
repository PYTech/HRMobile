package com.pytech.hrm.util.constants.enums;

import java.util.HashMap;
import java.util.Map;

public enum RenderLevel {
	LEVEL_HIGH(20), LEVEL_MID(10), LEVEL_LOW(0);
	
	private int value;
	private static final Map<Integer, RenderLevel> intToTypeMap = new HashMap<Integer, RenderLevel>();
	static {
		for(RenderLevel type : RenderLevel.values()) {
			intToTypeMap.put(type.getValue(), type);
		}
	}

	private RenderLevel(int value) {
		this.value = value;
	}
	
	public static RenderLevel getType(int value) {
		return intToTypeMap.get(value);
	}
	
	public int getValue(){
       return this.value;
	}
}
