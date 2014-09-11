package com.pytech.hrm.managers;

import android.content.Context;

import com.pytech.hrm.models.user.UserVO;
import com.pytech.hrm.tasks.LoginTask;

public class UserManager {
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

	}
}
