package com.pytech.hrm.util;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
	public static String toDurationString(long timeUsed) {
		long hours = TimeUnit.MILLISECONDS.toHours(timeUsed);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUsed);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUsed);
		
		seconds -= minutes * 60;
		minutes -= hours * 60;
		
		return String.format("%02dH:%02dM:%02dS", hours, minutes, seconds);
	}
}
