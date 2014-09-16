package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.pytech.hrm.R;
import com.pytech.hrm.events.LoginRequiredEvent;
import com.pytech.hrm.util.constants.HRM;

import de.greenrobot.event.EventBus;

public class MainActivity extends HRMActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.initialize();
		EventBus.getDefault().register(this);

		// Page indirect.
		this.gotoLoginPage(true);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(Activity.RESULT_OK == resultCode) {
			switch(requestCode) {
				case HRM.REQ_CODE_LOGIN:
					// TODO: 暫定直接跳到任務列表
					this.gotoMissionPage();
					break;
				case HRM.REQ_CODE_MISSION:
					// TODO: 暫定直接結束 APP
					this.exitApp();
					break;
			}
		} else if(HRM.ACTIVITY_RESULT_GOBACK == resultCode) {
			this.finish();
		} else {
			this.exitApp();
		}
	}
	
	public void onEventMainThread(LoginRequiredEvent event) {
		this.gotoLoginPage(false);
	}

	public void general() {
		
	}

	@Override
	protected void processViews() {

	}

	@Override
	protected void processControllers() {

	}

	protected void initializeMenu(Menu menu) {

	}

	private void gotoLoginPage(boolean autoLogin) {
		Intent intent = new Intent(this, LoginActivity.class);
		intent.putExtra(HRM.KEY_INTENT_AUTO_LOGIN, autoLogin);
		this.startActivityForResult(intent, HRM.REQ_CODE_LOGIN);
	}

	private void gotoMissionPage() {
		this.startActivityForResult(new Intent(this, MissionActivity.class), HRM.REQ_CODE_MISSION);
	}
}
