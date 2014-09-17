package com.pytech.hrm.events;

public class MissionClickedEvent {
	private int position;
	
	public MissionClickedEvent(int position) {
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
