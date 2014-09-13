package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pytech.hrm.R;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.rest.RestManager;

public class MainActivity extends HRMActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Initializations.
		this.processViews();
		this.processActions();

		// TODO: 判斷是否設定為自動登入，如果未設定或是登入失敗，才顯示登入畫面
		if(true) {
			this.gotoLoginPage();
		}
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
				RestManager.cleanCookies();
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
					break;
			}
		} else {
			// TODO: 顯示是否確認退出的窗格
			this.finish();
		}
	}
	
	public void general() {
		this.missionManager.getAllMissions(this);
		try {
			Thread.sleep(500000);
		} catch(InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void processViews() {
		
	}
	
	protected void processActions() {
		
	}
	
	protected void initializeMenu(Menu menu) {
		
	}
	
	protected void gotoLoginPage() {
		startActivityForResult(new Intent(this, LoginActivity.class), HRM.REQ_CODE_LOGIN);
	}
}
