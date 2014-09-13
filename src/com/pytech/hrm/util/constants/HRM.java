package com.pytech.hrm.util.constants;

public class HRM {
	// General string.
	public static final String RESULT_OK = "OK";
	public static final String RESULT_CONN_FAIL = "CONN_FAIL";
	public static final String RESULT_LOGIN_FAIL_AUTH = "LOGIN_AUTH_FAIL";
	
	// Rest manager settings.
	public static final int REST_CONNECT_TIMEOUT = 15000;
	public static final int REST_SOCKET_TIMEOUT = 60000;
	
	// Intent request codes.
	public static final int REQ_CODE_LOGIN = 1;
	
	// Preference keys.
	public static final String KEY_AUTO_LOGIN_ACCOUNT = "AUTO_USERNAME";
	public static final String KEY_AUTO_LOGIN_PASSWORD = "AUTO_MIMA";
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String ANNONYMOUS = "annonymous";
}
