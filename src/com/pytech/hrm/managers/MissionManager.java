package com.pytech.hrm.managers;

import android.content.Context;

import com.pytech.hrm.tasks.MissionTask;

public class MissionManager {
	public void getAllMissions(Context context) {
		MissionTask missionTask = new MissionTask();
		missionTask.setContext(context);
		missionTask.execute();
	}
}
