package com.pytech.hrm.activities;

import com.pytech.hrm.managers.FrameManager;
import com.pytech.hrm.managers.MissionManager;
import com.pytech.hrm.managers.UserManager;

import android.app.Activity;
import android.os.Bundle;

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
}
