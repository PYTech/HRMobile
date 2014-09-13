package com.pytech.hrm.exception;

public class AuthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3958771506230884479L;
	
	public AuthException() {
		super();
	}
	
	public AuthException(String errMsg) {
		super(errMsg);
	}

}
