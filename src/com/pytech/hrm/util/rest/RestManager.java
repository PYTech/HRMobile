package com.pytech.hrm.util.rest;

import java.io.IOException;
import java.util.List;

import org.apache.http.auth.AuthenticationException;
import org.apache.http.cookie.Cookie;

public class RestManager {
	private static List<Cookie> cookieList;

	public static void login(String username, String password) throws AuthenticationException, IOException {
		prepareCookies(username, password);
	}
	
	protected synchronized static void prepareCookies(String username, String password) throws AuthenticationException, IOException {
		if(cookieList == null) {
			cookieList = CookieManager.getCookies(username, password);
		}
	}
}
