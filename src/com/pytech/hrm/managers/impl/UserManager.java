package com.pytech.hrm.managers.impl;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.pytech.hrm.managers.IUserManager;
import com.pytech.hrm.models.user.UserVO;
import com.pytech.hrm.tasks.LoginTask;
import com.pytech.hrm.util.PreferenceUtils;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.PrefKey;
import com.pytech.hrm.util.rest.RestManager;

public class UserManager implements IUserManager {
	public void login(String username, String password, Context context) {
		// Execute login async task.
		UserVO userVO = new UserVO();
		userVO.setId(username);
		userVO.setPassword(password);
		
		LoginTask loginTask = new LoginTask();
		loginTask.setContext(context);
		loginTask.execute(userVO);
	}

	public void logout() {
		RestManager.cleanCookies();
	}
	
	public boolean autoLogin(Context context) {
		SharedPreferences pref = PreferenceUtils.getSharedPref(context);
		boolean auto = pref.getBoolean(PrefKey.AUTO_LOGIN, false);
		if(auto) {
			String username = pref.getString(PrefKey.USERNAME, HRM.ANNONYMOUS);
			String password = pref.getString(PrefKey.PASSWORD, StringUtils.EMPTY);
			this.login(username, password, context);
		}
		return auto;
	}
	
	public void keepLoginInfo(String username, String password, Context context) {
		Editor editor = PreferenceUtils.getEditor(context);
		editor.putString(PrefKey.USERNAME, username);
		editor.putString(PrefKey.PASSWORD, password);
		editor.putBoolean(PrefKey.AUTO_LOGIN, true);
		editor.commit();
	}
	
	public void cleanLoginInfo(Context context) {
		Editor editor = PreferenceUtils.getEditor(context);
		editor.remove(PrefKey.USERNAME);
		editor.remove(PrefKey.PASSWORD);
		editor.putBoolean(PrefKey.AUTO_LOGIN, false);
		editor.commit();
	}
	
	public UserVO getLoginInfo(Context context) {
		UserVO localUser = new UserVO();
		SharedPreferences pref = PreferenceUtils.getSharedPref(context);
		localUser.setId(pref.getString(PrefKey.USERNAME, null));
		localUser.setPassword(pref.getString(PrefKey.PASSWORD, null));
		localUser.setAutoLogin(pref.getBoolean(PrefKey.AUTO_LOGIN, false));
		return localUser;
	}
}
