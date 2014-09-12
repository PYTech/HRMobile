package com.pytech.hrm.models.rest;

import java.io.Serializable;

public class RestTask implements Serializable {
	/**
	 * Auto-generated serial version UID
	 */
	private static final long serialVersionUID = 5649189052783655255L;
	private Boolean success;
	private String error;
	private Object target;

	/**
	 * Used for HiRender Application only!
	 */
	public RestTask() {
	}

	/**
	 * 'success'的task
	 * 
	 * @param target
	 */
	public RestTask(Object target) {

		super();
		this.success = true;
		this.target = target;
	}

	/**
	 * 
	 * 'failure'的task<BR/>
	 * 建議使用 RestTask(Object target, Exception e)
	 * 
	 * @param target
	 * @param errorKey
	 */
	public RestTask(Object target, String error) {
		super();
		this.success = false;
		this.target = target;
		this.error = error;
	}

	/**
	 * 'failure'的task<BR/>
	 * 只要給excpetion會自動抓e.getMessage塞到error
	 * 
	 * @param target
	 * @param e
	 *            Excpetion
	 */
	public RestTask(Object target, Exception e) {
		super();
		this.success = false;
		this.target = target;
		this.error = e.toString();
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

}
