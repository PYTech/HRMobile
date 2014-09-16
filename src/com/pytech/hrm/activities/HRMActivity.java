package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

import com.pytech.hrm.R;
import com.pytech.hrm.managers.FrameManager;
import com.pytech.hrm.managers.MissionManager;
import com.pytech.hrm.managers.UserManager;
import com.pytech.hrm.util.ModalUtils;
import com.pytech.hrm.util.constants.HRM;

public abstract class HRMActivity extends Activity {
	protected UserManager userManager;
	protected MissionManager missionManager;
	protected FrameManager frameManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.userManager = new UserManager();
		this.missionManager = new MissionManager();
		this.frameManager = new FrameManager();

	}

	protected void initialize() {
		// Initializations.
		this.processViews();
		this.processControllers();
	}

	abstract protected void processViews();

	abstract protected void processControllers();
	
	protected void exitApp() {
		OnClickListener positiveListener = new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = getIntent();
				setResult(HRM.ACTIVITY_RESULT_GOBACK, intent);
				dialog.dismiss();
				finish();
			}
		};
		ModalUtils.showAlarm(this, this.getString(R.string.app_exit_title), this.getString(R.string.app_exit_confirm), positiveListener);
	}
}
