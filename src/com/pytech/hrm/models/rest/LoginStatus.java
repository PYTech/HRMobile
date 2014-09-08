package com.pytech.hrm.models.rest;

import java.io.Serializable;

public class LoginStatus implements Serializable {

	private static final long serialVersionUID = -3858239099870194974L;
	
	private boolean success;
	private boolean loggedIn;
	private String username;
	private String errorMessage;
	private int loginFailNum;

	public int getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(int loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
