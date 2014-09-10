package com.pytech.hrm.util.constants.enums;

public enum NotificationEventType {
	PROBLEM("PROBLEM"),
	SYSTEM("SYSTEM"),
	SERVICE("SERVICE"),
	POOL_EXPIRATION("POOL_EXPIRATION"),
	MONITOR("MONITOR"),
	RENDER_FAIL("RENDER_FAIL");
	
	private String notificationEventType;
	
	private NotificationEventType(String notificationEventType) {
		this.notificationEventType = notificationEventType;
	}
	
	public String getValue() {
		return this.notificationEventType;
	}
}

