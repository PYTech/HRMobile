package com.pytech.hrm.util.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import com.pytech.hrm.models.rest.RestTask;
import com.pytech.hrm.util.constants.enums.ReqMethod;

public class RestManager {
	private static List<Cookie> cookieList;

	public static void login(String username, String password) throws AuthenticationException, IOException {
		prepareCookies(username, password);
	}
	
	public static RestTask sendHttpRequest(ReqMethod method, String type, Object content) {
		DefaultHttpClient httpClient = HRMHttpClientFactory.createClient();
		
		switch(method) {
			case GET:
				break;
			case POST:
				break;
			case DELETE:
				break;
			case PUT:
				break;
			default:
		}
		
		return null;
	}

	public static String getEntityAsString(HttpResponse response) throws IllegalStateException, IOException {
		// Extract login status from response entity.
		StringBuffer buffer = new StringBuffer();
		InputStream input = response.getEntity().getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
	
	public static void cleanCookies() {
		cookieList = null;
	}

	protected synchronized static void prepareCookies(String username, String password) throws AuthenticationException, IOException {
		if(cookieList == null) {
			cookieList = CookieManager.getCookies(username, password);
		}
	}
}
