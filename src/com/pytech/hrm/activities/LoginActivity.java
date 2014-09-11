package com.pytech.hrm.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.pytech.hrm.R;
import com.pytech.hrm.util.constants.REST;

public class LoginActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	public void login(View view) {
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
}
