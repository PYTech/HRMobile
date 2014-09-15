package com.pytech.hrm.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pytech.hrm.R;
import com.pytech.hrm.adapters.MissionAdapter;
import com.pytech.hrm.events.GetMissionDoneEvent;
import com.pytech.hrm.events.GetMissionFailEvent;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.util.constants.HRM;

import de.greenrobot.event.EventBus;

public class MissionActivity extends HRMActivity {
	private MissionAdapter missionAdapter;
	private List<Mission> missionList = new ArrayList<Mission>();
	
	private ListView missionListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission);
		
		this.initialize();
		this.processDatas();
		
		EventBus.getDefault().register(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		this.missionManager.getAllMissions(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		EventBus.getDefault().unregister(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mission, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.act_refresh:
				this.missionManager.getAllMissions(this);
				break;
			case R.id.act_pause:
				break;
			case R.id.act_resume:
				break;
			case R.id.act_cancel:
				break;
			case R.id.act_restart:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onEventMainThread(GetMissionDoneEvent event) {
		this.missionList.clear();
		this.missionList.addAll(event.getMissionList());
		this.missionAdapter.notifyDataSetChanged();
	}
	
	public void onEventMainThread(GetMissionFailEvent event) {
		
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = this.getIntent();
		this.setResult(HRM.ACTIVITY_RESULT_GOBACK, intent);
		this.finish();
	}

	protected void processViews() {
		this.missionListView = (ListView) this.findViewById(R.id.mission_list);
	}

	protected void processControllers() {
		
	}	
	
	protected void processDatas() {
		this.missionAdapter = new MissionAdapter(this, R.layout.mission_record, this.missionList);
		this.missionListView.setAdapter(missionAdapter);
	}
}
