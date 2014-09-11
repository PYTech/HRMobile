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
		
		// Initializations.
		this.processViews();
		this.processActions();

		// TODO: 判斷是否設定為自動登入，如果未設定或是登入失敗，才顯示登入畫面
		if(true) {
			startActivityForResult(new Intent(this, LoginActivity.class), HRM.REQ_CODE_LOGIN);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.action_settings) {
			return true;
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
			// login failed, quit app.
			this.finish();
		}
	}

	public void general() {
		
	}
	
	protected void processViews() {
		
	}
	
	protected void processActions() {
		
	}
}
