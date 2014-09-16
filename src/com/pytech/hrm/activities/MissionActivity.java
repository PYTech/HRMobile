package com.pytech.hrm.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.pytech.hrm.R;
import com.pytech.hrm.adapters.MissionAdapter;
import com.pytech.hrm.events.GetMissionDoneEvent;
import com.pytech.hrm.events.GetMissionFailEvent;
import com.pytech.hrm.events.MissionSelectedChangedEvent;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.util.constants.HRM;

import de.greenrobot.event.EventBus;

public class MissionActivity extends HRMActivity {
	private MissionAdapter missionAdapter;
	private List<Mission> missionList = new ArrayList<Mission>();
	private int selectedCount = 0;

	private ListView missionListView;
	private MenuItem actRefresh, actPause, actPlay, actStop, actReplay;

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
		this.processMenuViews(menu);
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
		// TODO: 顯示取得任務失敗訊息
	}
	
	public void onEventMainThread(MissionSelectedChangedEvent event) {
		int position = event.getPosition();
		boolean selected = event.isSelected();
		Mission mission = this.missionList.get(position);
		mission.setSelected(selected);
		if(selected) {
			++this.selectedCount;
		} else {
			--this.selectedCount;
		}
		Log.i(this.getClass().getName(), String.format("Mission[%s] selected[%s] changed!", mission.getName(), selected));
		this.processMenu(null);
	}

	@Override
	public void onBackPressed() {
		if(this.selectedCount == 0) {
			Intent intent = this.getIntent();
			this.setResult(HRM.ACTIVITY_RESULT_GOBACK, intent);
			this.finish();
		} else {
			for(Mission mission : this.missionList) {
				mission.setSelected(false);
			}
			this.selectedCount = 0;
			this.missionAdapter.notifyDataSetChanged();
		}
	}

	protected void processViews() {
		this.missionListView = (ListView) this.findViewById(R.id.mission_list);
	}
	
	protected void processMenuViews(Menu menu) {
		this.actRefresh = menu.findItem(R.id.act_refresh);
		this.actPause = menu.findItem(R.id.act_pause);
		this.actPlay = menu.findItem(R.id.act_resume);
		this.actStop = menu.findItem(R.id.act_cancel);
		this.actReplay = menu.findItem(R.id.act_restart);
		
		this.processMenu(null);
	}

	protected void processControllers() {
		// Click event handler for mission list view.
		OnItemClickListener itemListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Mission mission = (Mission) parent.getItemAtPosition(position);
				if(selectedCount >= 0) {
					processMenu(mission);
					missionAdapter.set(position, mission);
				} else {
					// TODO: 顯示 mission 明細
				}
			}
		};
		this.missionListView.setOnItemClickListener(itemListener);
	}

	protected void processDatas() {
		this.missionAdapter = new MissionAdapter(this, R.layout.mission_record, this.missionList);
		this.missionListView.setAdapter(missionAdapter);
	}

	protected void processMenu(Mission mission) {
		if(mission != null) {
			if(mission.isSelected()) {
				mission.setSelected(false);
				--this.selectedCount;
			} else {
				mission.setSelected(true);
				++this.selectedCount;
			}
		}
		
		// Handle menu visibility.
		this.actRefresh.setVisible(this.selectedCount == 0);
		this.actPause.setVisible(this.selectedCount > 0);
		this.actPlay.setVisible(this.selectedCount > 0);
		this.actStop.setVisible(this.selectedCount > 0);
		this.actReplay.setVisible(this.selectedCount >0);
	}
}
