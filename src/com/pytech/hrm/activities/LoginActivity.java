package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.pytech.hrm.R;
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
		
		EventBus.getDefault().unregister(this);
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
	
	public void loginSuccess(LoginSuccessEvent event) {
		Intent intent = this.getIntent();
		this.setResult(Activity.RESULT_OK, intent);
		this.finish();
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
