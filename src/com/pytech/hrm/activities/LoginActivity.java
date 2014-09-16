package com.pytech.hrm.activities;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.pytech.hrm.R;
import com.pytech.hrm.events.LoginFailEvent;
import com.pytech.hrm.events.LoginSuccessEvent;
import com.pytech.hrm.models.user.UserVO;
import com.pytech.hrm.util.ModalUtils;
import com.pytech.hrm.util.UIUtils;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.REST;

import de.greenrobot.event.EventBus;

public class LoginActivity extends HRMActivity {
	private EditText usernameInput;
	private EditText passwordInput;
	private ToggleButton remember;
	private Button loginButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		this.initialize();	
		EventBus.getDefault().register(this);
	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();   
        EventBus.getDefault().unregister(this);
    }
	
	public void login(View view) {
		String username = this.usernameInput.getText().toString();
		String password = this.passwordInput.getText().toString();
		this.userManager.login(username, password, this);
		
		// 判斷是否儲存自動登入資訊
		if(this.remember.isChecked()) {
			this.userManager.keepLoginInfo(username, password, this);
		} else {
			this.userManager.cleanLoginInfo(this);
		}
	}
	
	public void onEventMainThread(LoginSuccessEvent event) {
		Intent intent = this.getIntent();
		this.setResult(Activity.RESULT_OK, intent);
		this.finish();
	}
	
	public void onEventMainThread(LoginFailEvent event) {
		String errReason = event.getReason();
		String errMsg = null;
		if(StringUtils.equals(errReason, HRM.TASK_RESULT_AUTH_FAIL)) {
			errMsg = this.getString(R.string.msg_login_fail_auth);
		} else if(StringUtils.equals(errReason, HRM.TASK_RESULT_CONN_FAIL)) {
			errMsg = this.getString(R.string.msg_conn_fail);
		} else {
			// Should NOT be here...
			Log.e(this.getClass().getName(), String.format("Unexpected error reason[%s] from login task, please check!", errReason));
			errMsg = this.getString(R.string.msg_conn_fail);
		}
		// Show alert dialog.
		ModalUtils.showAlarm(this, this.getString(R.string.msg_login_fail), errMsg, null);
		
		// Clear previous input content.
		this.usernameInput.setText(StringUtils.EMPTY);
		this.passwordInput.setText(StringUtils.EMPTY);
	}
	
	public void helpMe(View view) {
		String url = REST.SERVER_URL + REST.PATH_HELP_CENTER;
		this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}
	
	@Override
	public void onBackPressed() {
		this.exitApp();
	}
	
	@Override
	protected void processViews() {
		this.usernameInput = (EditText) this.findViewById(R.id.input_account);
		this.passwordInput = (EditText) this.findViewById(R.id.input_password);
		this.remember = (ToggleButton) this.findViewById(R.id.toggle_remember_me);
		this.loginButton = (Button) this.findViewById(R.id.login_go);
		
		UserVO localUser = this.userManager.getLoginInfo(this);
		this.usernameInput.setText(localUser.getId());
		this.passwordInput.setText(localUser.getPassword());
		this.remember.setChecked(localUser.isAutoLogin());
		
		boolean autoLogin = this.getIntent().getExtras().getBoolean(HRM.KEY_INTENT_AUTO_LOGIN);
		if(localUser.isAutoLogin() && autoLogin) {
			this.userManager.login(localUser.getId(), localUser.getPassword(), this);
		}
	}

	@Override
	protected void processControllers() {
		
	}
	
	protected void enableInputs() {
		UIUtils.setEditable(this.usernameInput, true);
		UIUtils.setEditable(this.passwordInput, true);
		UIUtils.setEditable(this.remember, true);
		UIUtils.setEditable(this.loginButton, true);
	}
	
	protected void disableInputs() {
		UIUtils.setEditable(this.usernameInput, false);
		UIUtils.setEditable(this.passwordInput, false);
		UIUtils.setEditable(this.remember, false);
		UIUtils.setEditable(this.loginButton, false);
	}
}
