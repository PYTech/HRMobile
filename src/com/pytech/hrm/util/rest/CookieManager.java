package com.pytech.hrm.util.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.pytech.hrm.exception.ErrorKey;
import com.pytech.hrm.models.rest.LoginStatus;
import com.pytech.hrm.util.constants.REST;

public class CookieManager {
	public static synchronized List<Cookie> getCookies(String username, String password) throws IOException, AuthenticationException {
		List<Cookie> cookieList;
		DefaultHttpClient httpClient = HRMHttpClientFactory.createClient();

		List<NameValuePair> form = new ArrayList<NameValuePair>();
		form.add(new BasicNameValuePair(REST.KEY_FORM_USERNAME, username));
		form.add(new BasicNameValuePair(REST.KEY_FORM_MIMA, password));

		HttpPost request = new HttpPost(REST.SERVER_URL + REST.LOGIN_PATH);
		request.setEntity(new UrlEncodedFormEntity(form, HTTP.UTF_8));
		HttpResponse response = httpClient.execute(request);

		// Extract login status from response entity.
		String responseContent = RestManager.getEntityAsString(response);
		Log.d(CookieManager.class.getName(), String.format("Get response[%s] for login request from web server.", responseContent));
		LoginStatus loginStatus = JsonConverter.convertFromJson(LoginStatus.class, responseContent);

		// Check login successful.
		if(loginStatus != null && loginStatus.isSuccess()) {
			cookieList = httpClient.getCookieStore().getCookies();
		} else {
			// Check login fail reason.
			String errMsg = loginStatus.getErrorMessage();
			ErrorKey key;
			try {
				key = ErrorKey.valueOf(errMsg);
			} catch(Exception e) {
				key = ErrorKey.U004;
			}
			throw new AuthenticationException(key.getAnnotation());
		}
		return cookieList;
	}
}
