package com.pytech.hrm.util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.pytech.hrm.R;

import android.content.Context;

public class TimeUtils {
	public static String toDurationString(Context context, long timeUsed) {
		long hours = TimeUnit.MILLISECONDS.toHours(timeUsed);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUsed);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUsed);
		
		seconds -= minutes * 60;
		minutes -= hours * 60;
		
		if(hours > 0) {
			return String.format(context.getString(R.string.label_duration, StringUtils.EMPTY), hours, minutes, seconds);
		} else {
			return String.format(context.getString(R.string.label_duration_no_hour, StringUtils.EMPTY), minutes, seconds);
		}
	}
}
