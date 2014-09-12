package com.pytech.hrm.activities;

import org.apache.commons.lang3.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.pytech.hrm.R;
import com.pytech.hrm.events.LoginFailEvent;
import com.pytech.hrm.events.LoginSuccessEvent;
import com.pytech.hrm.util.PreferenceUtils;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.REST;

import de.greenrobot.event.EventBus;

public class LoginActivity extends HRMActivity {
	private EditText usernameInput;
	private EditText passwordInput;
	private ToggleButton remember;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		this.processViews();
		
		EventBus.getDefault().register(this);
	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
        
        EventBus.getDefault().unregister(this);
    }
	
	public void login(View view) {
		// TODO: 加入帳密初步檢驗機制
		String username = this.usernameInput.getText().toString();
		String password = this.passwordInput.getText().toString();
		this.userManager.login(username, password, this);
		
		// 判斷是否儲存自動登入資訊
		if(this.remember.isChecked()) {
			Editor editor = PreferenceUtils.getEditor(this);
			editor.putString(HRM.KEY_AUTO_LOGIN_ACCOUNT, username);
			editor.putString(HRM.KEY_AUTO_LOGIN_PASSWORD, password);
			editor.commit();
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
		if(StringUtils.equals(errReason, HRM.RESULT_LOGIN_FAIL_AUTH)) {
			errMsg = this.getString(R.string.msg_login_fail_auth);
		} else if(StringUtils.equals(errReason, HRM.RESULT_CONN_FAIL)) {
			errMsg = this.getString(R.string.msg_conn_fail);
		} else {
			// Should NOT be here...
			Log.e(this.getClass().getName(), String.format("Unexpected error reason[%s] from login task, please check!", errReason));
			errMsg = this.getString(R.string.msg_conn_fail);
		}
		// Show alert dialog.
		Builder alertBuilder = new Builder(this);
		alertBuilder.setPositiveButton(android.R.string.ok, null);
		AlertDialog alert = alertBuilder.create();
		alert.setCancelable(true);
		alert.setCanceledOnTouchOutside(false);
		alert.setTitle(this.getString(R.string.msg_login_fail));
		alert.setMessage(errMsg);
		alert.show();
	}
	
	public void helpMe(View view) {
		String url = REST.SERVER_URL + REST.PATH_HELP_CENTER;
		this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}
	
	@Override
	public void onBackPressed() {
		// TODO: show alarm dialog: are you sure to quit?
		Intent intent = this.getIntent();
		this.setResult(Activity.RESULT_CANCELED, intent);
		this.finish();
	}
	
	private void processViews() {
		this.usernameInput = (EditText) this.findViewById(R.id.input_account);
		this.passwordInput = (EditText) this.findViewById(R.id.input_password);
		this.remember = (ToggleButton) this.findViewById(R.id.toggle_remember_me);
	}
}
