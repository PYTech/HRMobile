package com.pytech.hrm.util.constants.enums;

public enum ProjectPriority
{
	LOW,
	MEDIUM,
	HIGH;
	
	public  int getInt() {
		switch(this){
			case LOW:
				return 10;
			case MEDIUM:
				return 50;
			case HIGH:
				return 90;
		}
		return 10;
	}
}
