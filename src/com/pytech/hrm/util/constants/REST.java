package com.pytech.hrm.util.constants;

public class REST {

	// Path variables
	public static final String SERVER_URL = "https://210.61.119.24/HIRENDER";

	/* User */
	public static final String FORGOT_USER_ID_PATH = SERVER_URL + "/recoverId";
	public static final String FORGOT_USER_PASS = SERVER_URL + "/recoverAccount";
	public static final String REGISTER_ACCOUNT = SERVER_URL + "/registerAccount";
	public static final String LOGIN_PATH = "/j_spring_security_check";
	public static final String USER_PATH = "/rest/session/user";
	public static final String USER_SIGNOUT_PATH = "/rest/session";

	/* Queue, Job */
	public static final String QUEUE_PATH = "/rest/queue";
	public static final String QUEUE_SUBMIT_PATH = QUEUE_PATH + "/submission";
	public static final String MISSION_PATH = QUEUE_PATH + "/missions";
	public static final String FRAME_PATH = QUEUE_PATH + "/frames";
	public static final String ACTION_PATH = QUEUE_PATH + "/action";
	public static final String RENDER_RESULT_ZIP_PATH = QUEUE_PATH + "/zipPath";
	public static final String LOG_PATH = QUEUE_PATH + "/framelogString/";
	public static final String PREVIEW_PATH = QUEUE_PATH + "/previewFrameStream/";

	/* Project */
	public static final String PROJECT_PATH = "/rest/projects";

	/* Version */
	public static final String VERSION_PATH = "/rest/version";
	public static final String LAST_APP_PATH = VERSION_PATH + "/getLastApp";
	public static final String ALL_APP_PATH = VERSION_PATH + "/apps";
	public static final String LAST_PLUGIN_PATH = VERSION_PATH + "/lastPlugin";
	public static final String ALL_PLUGIN_PATH = VERSION_PATH + "/lastPlugins";
	public static final String OS_WINDOWS = "OS_WINDOWS";
	public static final String OS_LINUX = "OS_LINUX";

	/* Billing */
	public static final String BILLING_PATH = "/rest/billing/balance";
	public static final String PARAM_USER_ID = "userId";
	public static final String BILLING_LINK = "/rest/billing/link";
	public static final String PARAM_PROJECT_ID = "projectId";

	// Key variables
	public static final String KEY_FORM_USERNAME = "j_username";
	public static final String KEY_FORM_MIMA = "j_password";

	// Param keys
	public static final String PARAM_FILTER_KEY = "filter";
	public static final String PARAM_MISSION_ID = "missionOid";
	public static final String PARAM_OS_TYPE = "osType";
	public static final String PARAM_TIME_INTERVAL_BEGIN = "start";
	public static final String PARAM_TIME_INTERVAL_END = "end";

	// Param filter variables
	public static final String PARAM_FILTER_VALUE_SELF = "me";
	public static final String PARAM_FILTER_MISSION_LIST_RUNNING = "running";
	public static final String PARAM_FILTER_MISSION_LIST_HISTORY = "historical";
	public static final String PARAM_FILTER_MISSION_LIST_DONE = "done";
	public static final String PARAM_FILTER_FRAME_LIST_DONE = "done";

	// Param content variables
	public static final String PARAM_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
}
