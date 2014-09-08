package com.pytech.hrm.exception;

public enum ErrorKey {
	INVALID_IP("This account is accessable only in a specific IP adress."),

	// General error keys.
	/** G001: JSON string conversion error. **/
	G001("JSON string conversion error."),
	/** G002: Plugin unrecognized request type. **/
	G002("Plugin unrecognized request type."),
	/** G003: Plugin request content error. **/
	G003("Plugin request content error."),

	/** G101: FXML content error. **/
	G101("FXML content error."),
	/** G102: Load object failed. **/
	G102("Load object failed."),
	/** G103: Get reloaded object failed. **/
	G103("Get reloaded object failed."),
	/** G104: FXML content reloaded error. **/
	G104("FXML content  reloaded error."),
	/** G105: Load file from given path error. **/
	G105("Load file from given path error."),
	/** G106: Write string to given filepath error. **/
	G106("Write string to given filepath error."),

	/** G201: Data converter error: field name not found. **/
	G201("Data converter error: field name not found."),
	/** G202: JSON string conversion error. **/
	G202("URL converter error: argument length out of range."),

	/** G301: Permission denied, could not create folder. **/
	G301("Permission denied, could not create folder."),
	
	/** G401: Proxy failed, could not connect to server. **/
	G401("Proxy failed, could not connect to server."),

	/** G311: Permission denied, could not open default browser. **/
	G311("Permission denied, could not open default browser."),
	
	/** G901: No Internet connection. **/
	G901("No Internet connection."),
	/** G902: Web server connection fail. **/
	G902("Web server connection fail."),
	/** G903: Web server communication timeout. **/
	G903("Web server communication timeout."),
	/** G904: Web server communication error. **/
	G904("Web server communication error."),
	
	/** G999: Unknown error. **/
	G999("Unknown error."),

	
	// User module (Code: U) error keys.
	/** U001: Email duplicated. **/
	U001("Email duplicated"),
	/** U002: ID duplicated. **/
	U002("ID duplicated"),
	/** U003: ID not exists. **/
	U003("ID not exists"),
	/** U004: Incorrect account or password **/
	U004("Incorrect account or password"),
	/** U005: Email authCode error **/
	U005("Email authCode error"),
	/** U006: Email not exists **/
	U006("Email not exists"),
	/** U007: ID already activated **/
	U007("ID already activated"),
	/** U008: Can't connect to mail server **/
	U008("Can't connect to mail server"),
	/** U009: incorrect ip address **/
	U009("incorrect ip address"),
	/** U010: captcha check code authentication failed **/
	U010("captcha check code authentication failed"),
	/** U011: account inactive **/
	U011("account inactive"),
	/** U012: Incorrect account or password **/
	U012("account suspend"),


	// Transfer module (Code: T) error keys.
	/** T001: FTP server connect failed, connection timeout. **/
	T001("FTP server connect failed, connection timeout."),
	/** T002: FTP server login failed, invalid user. **/
	T002("FTP server login failed, invalid user."),
	/** T003: FTP server communication error. **/
	T003("FTP server communication error."),

	/** T101: Access denied. **/
	T101("Access denied."),
	/** T102: Rmdir failed, directory not empty. **/
	T102("Rmdir failed, directory not empty."),
	/** T103: Path not allowed. **/
	T103("Path not allowed."),
	/** T111: File unavailable. **/
	T111("File unavailable."),
	/** T121: Storage allocation exceed. **/
	T121("Storage allocation exceed."),
	/** T131: File name not allowed. **/
	T131("File name not allowed."),

	
	// Job module (Code: J) error keys.
	/** J001: not project member ,admin or owner **/
	J001("not project member,admin or owner"),
	/** J002: not project admin or owner **/
	J002("not project admin or owner"),
	/** J003: not project member **/
	J003("not project member"),
	/** J004: not mission owner **/
	J004("not mission owner"),
	/** J005: current login user is not the same to target user **/
	J005("current login user is not the same to target user"),
	/** J006: not submission owner **/
	J006("not submission owner"),
	/** J007: unsupported mission operation **/
	J007("unsupported mission operation"),
	
	/** J101: get frame log fail **/
	J101("get frame log fail"),
	/** J102: unsupported product (no converter implemented) **/
	J102("unsupported product (no converter implemented)"),
	
	/** J201: thumbnail file does not exist **/
	J201("thumbnail file does not exist"),
	/** J202: fail tp generate thumbnail file **/
	J202("fail tp generate thumbnail file"),
	
	/** J301: no jobCache found **/
	J301("no jobCache found"),
	
	/** J401: receive job done message but some frame not done **/
	J401("receive job done message but some frame not done"),
	
	/** J901: job submission failed **/
	J901("job submission failed"),
	/** J902: get mission list failed **/
	J902("get mission list failed"),
	/** J903: get frame list failed **/
	J903("get frame list failed"),
	
	
	// Billing module.
	/** could not get billing link **/
	B001("could not get billing link"),
	/** billing link syntax error **/
	B002("billing link syntax error"),
	
	
	// Database persistent layer module (Code: D) error keys.
	/** D001: Target value cannot be null. **/
	D001("Target value cannot be null."),
	/** D002: Target value list cannot be empty. **/
	D002("Target value list cannot be empty."),
	/** D003: Database operation failed. **/
	D003("Database operation failed."),
	;
	
	private String annotation;

	private ErrorKey(String annotation) {
		this.annotation = annotation;
	}

	public String getAnnotation() {
		return this.annotation;
	}
}
