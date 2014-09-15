package com.pytech.hrm.events;

public class GetMissionFailEvent {
	private String reason;
	
	public GetMissionFailEvent(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
