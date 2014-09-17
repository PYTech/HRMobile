package com.pytech.hrm.activities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.pytech.hrm.R;
import com.pytech.hrm.adapters.MissionAdapter;
import com.pytech.hrm.events.GetMissionDoneEvent;
import com.pytech.hrm.events.GetMissionFailEvent;
import com.pytech.hrm.events.LoginRequiredEvent;
import com.pytech.hrm.events.MissionActDoneEvent;
import com.pytech.hrm.events.MissionActFailEvent;
import com.pytech.hrm.events.MissionClickedEvent;
import com.pytech.hrm.events.MissionSelectedChangedEvent;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.util.ModalUtils;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.enums.MissionAction;
import com.pytech.hrm.util.constants.enums.MissionState;

import de.greenrobot.event.EventBus;

public class MissionActivity extends HRMActivity {
	private MissionAdapter missionAdapter;
	private List<Mission> missionList = new ArrayList<Mission>();
	private int selectedCount = 0;

	private ListView missionListView;
	private MenuItem actRefresh, actPause, actPlay, actStop, actReplay, settings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission);
		this.initialize();
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		this.missionManager.getAllMissions(this, true);
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
				this.missionManager.getAllMissions(this, true);
				break;
			case R.id.act_pause:
				this.missionManager.doMissionAction(this, MissionAction.HOLD, this.extractTargets(MissionAction.HOLD));
				break;
			case R.id.act_resume:
				this.missionManager.doMissionAction(this, MissionAction.RESUME, this.extractTargets(MissionAction.RESUME));
				break;
			case R.id.act_cancel:
				this.missionManager.doMissionAction(this, MissionAction.CANCEL, this.extractTargets(MissionAction.CANCEL));
				break;
			case R.id.act_restart:
				this.missionManager.doMissionAction(this, MissionAction.RERUN, this.extractTargets(MissionAction.RERUN));
				break;
			case R.id.item_logout:
				this.userManager.logout();
				EventBus.getDefault().post(new LoginRequiredEvent());
				this.finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onEventMainThread(GetMissionDoneEvent event) {
		this.missionList.clear();
		this.cleanSelect();
		this.missionList.addAll(event.getMissionList());
		this.missionAdapter.notifyDataSetChanged();
	}

	public void onEventMainThread(GetMissionFailEvent event) {
		String errReason = event.getReason();
		String errMsg = this.processErrMsg(errReason);
		OnClickListener listener = this.genListener(errReason);
		
		// Show alert dialog.
		ModalUtils.showAlarm(this, this.getString(R.string.msg_mission_get_fail), errMsg, listener);
	}

	public void onEventMainThread(MissionActDoneEvent event) {
		// Refresh mission list.
		this.missionManager.getAllMissions(this, false);
	}

	public void onEventMainThread(MissionActFailEvent event) {
		String errReason = event.getReason();
		String errMsg = this.processErrMsg(errReason);
		OnClickListener listener = this.genListener(errReason);
		
		// Show alert dialog.
		ModalUtils.showAlarm(this, this.getString(R.string.msg_mission_act_fail), errMsg, listener);
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
		this.processMenu(null);
	}
	
	public void onEventMainThread(MissionClickedEvent event) {
		int position = event.getPosition();
		Mission mission = this.missionList.get(position);
		if(selectedCount > 0) {
			processMenu(mission);
			missionAdapter.notifyDataSetChanged();
		} else {
			// TODO: 顯示 mission 明細
			Log.i(MissionActivity.class.getName(), "Show mission detail info.");
		}
	}

	@Override
	public void onBackPressed() {
		if(this.selectedCount <= 0) {
			this.exitApp();
		} else {
			this.cleanSelect();
		}
	}

	protected void processViews() {
		this.missionListView = (ListView) this.findViewById(R.id.mission_list);
		
		this.missionAdapter = new MissionAdapter(this, R.layout.mission_record, this.missionList);
		this.missionListView.setAdapter(missionAdapter);
	}

	protected void processMenuViews(Menu menu) {
		this.actRefresh = menu.findItem(R.id.act_refresh);
		this.actPause = menu.findItem(R.id.act_pause);
		this.actPlay = menu.findItem(R.id.act_resume);
		this.actStop = menu.findItem(R.id.act_cancel);
		this.actReplay = menu.findItem(R.id.act_restart);
		this.settings = menu.findItem(R.id.act_settings);

		this.processMenu(null);
	}

	protected void processControllers() {
		
	}

	protected void processMenu(Mission mission) {
		if(mission != null) {
			if(mission.isSelected()) {
				mission.setSelected(false);
			} else {
				mission.setSelected(true);
			}
		}

		// Handle menu visibility.
		this.actRefresh.setVisible(this.selectedCount == 0);
		this.actPause.setVisible(this.selectedCount > 0);
		this.actPlay.setVisible(this.selectedCount > 0);
		this.actStop.setVisible(this.selectedCount > 0);
		this.actReplay.setVisible(this.selectedCount > 0);
		this.settings.setVisible(this.selectedCount == 0);
	}

	protected String processErrMsg(String errReason) {
		String errMsg = null;
		if(StringUtils.equals(errReason, HRM.TASK_RESULT_AUTH_FAIL)) {
			errMsg = this.getString(R.string.msg_fail_auth);
		} else if(StringUtils.equals(errReason, HRM.TASK_RESULT_CONN_FAIL)) {
			errMsg = this.getString(R.string.msg_conn_fail);
		} else {
			// Should NOT be here...
			Log.e(this.getClass().getName(), String.format("Unexpected error reason[%s] from get all missions task, please check!", errReason));
			errMsg = this.getString(R.string.msg_conn_fail);
		}
		return errMsg;
	}
	
	protected OnClickListener genListener(final String errReason) {
		OnClickListener listener = null;
		if(StringUtils.equals(errReason, HRM.TASK_RESULT_AUTH_FAIL)) {
			listener = new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					finish();
					EventBus.getDefault().post(new GetMissionFailEvent(errReason));
				}
			};
		} 
		return listener;
	}

	private List<Mission> extractTargets(MissionAction action) {
		List<Mission> targets = new LinkedList<Mission>();
		for(Mission mission : this.missionList) {
			if(mission.isSelected()) {
				MissionState state = mission.getState();
				switch(action) {
					case HOLD:
						if(this.isRunning(state)) {
							targets.add(mission);
						}
						break;
					case RESUME:
						if(this.isHold(state)) {
							targets.add(mission);
						}
						break;
					case CANCEL:
						if(this.isHold(state) || this.isRunning(state)) {
							targets.add(mission);
						}
						break;
					case RERUN:
						targets.add(mission);
						break;
					default:
						// Do nothing.
				}
			}
		}
		return targets;
	}

	private boolean isRunning(MissionState state) {
		switch(state) {
			case RDY:
			case WD:
			case WDP:
			case WT:
			case WTM:
			case RUN:
			case GM:
			case CMP:
			case MOVGEN:
			case ASSEMBLE:
				return true;
			default:
				// Nothing.
		}
		return false;
	}

	private boolean isHold(MissionState state) {
		switch(state) {
			case PAUSE:
			case SYS_HOLDING:
			case SYS_PAUSE:
			case HOLDING:
				return true;
			default:
				// Nothing.
		}
		return false;
	}
	
	private void cleanSelect() {
		for(Mission mission : this.missionList) {
			mission.setSelected(false);
		}
		this.missionAdapter.notifyDataSetChanged();
		this.selectedCount = 0;
	}

}
