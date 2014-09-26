package com.pytech.hrm.managers;

import java.util.List;

import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.util.constants.enums.MissionAction;

import android.content.Context;

public interface IMissionManager {
	void getAllMissions(Context context, boolean showLoading);
	
	void getMissionsByProject(Context context, boolean showLoading, String projectOid);
	
	void doMissionAction(Context context, MissionAction action, List<Mission> missionList);
	
	void showResultMovie(Context context, Mission mission);
	
	void downloadResultFiles(Context context, List<Mission> missionList);
}
