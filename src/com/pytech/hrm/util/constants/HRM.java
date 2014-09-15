package com.pytech.hrm.util.constants;

public class HRM {
	// Task result strings.
	public static final String TASK_RESULT_OK = "OK";
	public static final String TASK_RESULT_FAIL = "FAIL";
	public static final String TASK_RESULT_CONN_FAIL = "CONN_FAIL";
	public static final String TASK_RESULT_AUTH_FAIL = "AUTH_FAIL";
	
	// Activity result codes.
	public static final int ACTIVITY_RESULT_GOBACK = 100;
	
	// Rest manager settings.
	public static final int REST_CONNECT_TIMEOUT = 15000;
	public static final int REST_SOCKET_TIMEOUT = 60000;
	
	// Intent request codes.
	public static final int REQ_CODE_LOGIN = 1;
	public static final int REQ_CODE_MISSION = 2;
	
	// Preference keys.
	public static final String KEY_AUTO_LOGIN_ACCOUNT = "AUTO_USERNAME";
	public static final String KEY_AUTO_LOGIN_PASSWORD = "AUTO_MIMA";
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String ANNONYMOUS = "annonymous";
	public static final String TIME_NOT_STARTED = "-";
	public static final int PROGRESS_MAX = 100;
	
	public static final String KEY_MISSION_INFO_DURATION = "DURATION";
	public static final String KEY_MISSION_INFO_STATE = "STATE";
}
