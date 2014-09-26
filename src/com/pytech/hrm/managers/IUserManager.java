package com.pytech.hrm.managers;

import com.pytech.hrm.models.user.UserVO;

import android.content.Context;

public interface IUserManager {
	void login(String username, String password, Context context);
	
	void logout();
	
	boolean autoLogin(Context context);
	
	void keepLoginInfo(String username, String password, Context context);
	
	void cleanLoginInfo(Context context);
	
	UserVO getLoginInfo(Context context);
}
