package com.pytech.hrm.models.usage;

import com.pytech.hrm.models.mission.Mission;

public class Umission {
	private String oid;
	private Mission mission = new Mission();
	private String period;
	private float averageFrameTime;
	private float coreHours;
	private float cost;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getAverageFrameTime() {
		return averageFrameTime;
	}

	public void setAverageFrameTime(float averageFrameTime) {
		this.averageFrameTime = averageFrameTime;
	}

	public float getCoreHours() {
		return coreHours;
	}

	public void setCoreHours(float coreHours) {
		this.coreHours = coreHours;
	}

	@Override
	public String toString() {
		return "Umission [mission=" + mission + ", averageFrameTime=" + averageFrameTime + ", coreHours=" + coreHours + ", cost=" + cost + "]";
	}

}
