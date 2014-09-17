package com.pytech.hrm.managers;

import java.util.List;

import android.content.Context;

import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.tasks.GetAllMissionTask;
import com.pytech.hrm.tasks.MissionActTask;
import com.pytech.hrm.util.constants.enums.MissionAction;

public class MissionManager {
	public void getAllMissions(Context context, boolean showLoading) {
		GetAllMissionTask missionTask = new GetAllMissionTask(showLoading);
		missionTask.setContext(context);
		missionTask.execute();
	}
	
	public void getMissionsByProject(Context context, boolean showLoading, String projectOid) {
		// TODO
	}
	
	public void doMissionAction(Context context, MissionAction action, List<Mission> missionList) {
		MissionActTask actTask = new MissionActTask(action);
		actTask.setContext(context);
		actTask.execute(missionList.toArray(new Mission[missionList.size()]));
	}
	
	public void showResultMovie(Context context, Mission mission) {
		// TODO
	}
	
	public void downloadResultFiles(Context context, List<Mission> missionList) {
		// TODO
	}
}
