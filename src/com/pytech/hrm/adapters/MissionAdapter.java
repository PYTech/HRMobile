package com.pytech.hrm.adapters;

import java.util.List;

import com.pytech.hrm.models.mission.Mission;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MissionAdapter extends ArrayAdapter<Mission> {
	private int resource;
	private List<Mission> missionList;

	public MissionAdapter(Context context, int resource, List<Mission> missions) {
		super(context, resource, missions);
		this.resource = resource;
		this.missionList = missions;
	}

}
