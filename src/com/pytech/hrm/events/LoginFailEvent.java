package com.pytech.hrm.events;

public class LoginFailEvent {
	private String reason;
	
	public LoginFailEvent(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
