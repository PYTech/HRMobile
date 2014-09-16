package com.pytech.hrm.events;

public class MissionActFailEvent {
	private String reason;
	
	public MissionActFailEvent(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
