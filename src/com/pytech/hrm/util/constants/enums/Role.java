package com.pytech.hrm.util.constants.enums;

/*custom hasRole("OO") for spring security*/

public enum Role {
  
	ADMIN("ADMIN"), // OP
	VIEWER("VIEWER"), // OP 
	USER("USER"); // // UP

	private String name;

	private Role(String name) {
		this.name = name;
	}

	public String getAuthority() {
		return this.name;
	}

}
