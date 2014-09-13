package com.pytech.hrm.models.rest;

public class ParamPair {

	private String key;

	private String value;

	public ParamPair() {

	}

	public ParamPair(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ParamPair [key=" + key + ", value=" + value + "]";
	}

}
