package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pytech.hrm.R;
import com.pytech.hrm.util.constants.HRM;

public class MainActivity extends HRMActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.initialize();

		// Page indirect.
		this.gotoLoginPage();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.item_logout:
				this.userManager.logout();
				this.gotoLoginPage();
				break;
			case R.id.item_settings:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(Activity.RESULT_OK == resultCode) {
			switch(requestCode) {
				case HRM.REQ_CODE_LOGIN:
					// TODO: 顯示功能選單 (目前先直接跳到任務列表)
					this.gotoMissionPage();
					break;
				case HRM.REQ_CODE_MISSION:
					// TODO:
					break;
			}
		} else if(Activity.RESULT_CANCELED == resultCode) {
			// TODO: 顯示是否確認退出的窗格
			this.finish();
		} else {
			// Do nothing.
		}
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

	private void gotoLoginPage() {
		this.startActivityForResult(new Intent(this, LoginActivity.class), HRM.REQ_CODE_LOGIN);
	}

	private void gotoMissionPage() {
		this.startActivityForResult(new Intent(this, MissionActivity.class), HRM.REQ_CODE_MISSION);
	}
}
