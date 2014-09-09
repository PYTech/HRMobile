package com.pytech.hrm.activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.pytech.hrm.R;
import com.pytech.hrm.util.constants.REST;
import com.pytech.hrm.util.rest.HRMHttpClientFactory;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if(true) {
			startActivity(new Intent(this, LoginActivity.class));
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

	public void general() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				DefaultHttpClient httpClient = HRMHttpClientFactory.createClient();
				
				HttpPost request = new HttpPost(REST.SERVER_URL + REST.LOGIN_PATH);
				List<NameValuePair> form = new ArrayList<NameValuePair>();
				form.add(new BasicNameValuePair(REST.KEY_FORM_USERNAME, "benny"));
				form.add(new BasicNameValuePair(REST.KEY_FORM_MIMA, "123456"));

				try {
					request.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
					HttpResponse response = httpClient.execute(request);
					List<Cookie> cookies = httpClient.getCookieStore().getCookies();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
