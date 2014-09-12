package com.pytech.hrm.util.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.cookie.Cookie;

public class RestManager {
	private static List<Cookie> cookieList;

	public static void login(String username, String password) throws AuthenticationException, IOException {
		prepareCookies(username, password);
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

	protected synchronized static void prepareCookies(String username, String password) throws AuthenticationException, IOException {
		if(cookieList == null) {
			cookieList = CookieManager.getCookies(username, password);
		}
	}
}
