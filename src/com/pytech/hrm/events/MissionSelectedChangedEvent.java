package com.pytech.hrm.events;

public class MissionSelectedChangedEvent {
	private int position;
	private boolean selected;
	
	public MissionSelectedChangedEvent(int position, boolean selected) {
		this.position = position;
		this.selected = selected;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
