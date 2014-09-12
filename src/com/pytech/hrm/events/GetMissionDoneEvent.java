package com.pytech.hrm.events;

import java.util.List;

import com.pytech.hrm.models.mission.Mission;

public class GetMissionDoneEvent {
	private List<Mission> missionList;
	
	public GetMissionDoneEvent(List<Mission> missionList) {
		this.missionList = missionList;
	}

	public List<Mission> getMissionList() {
		return missionList;
	}

	public void setMissionList(List<Mission> missionList) {
		this.missionList = missionList;
	}
}
