package com.pytech.hrm.util.constants.enums;

public enum FrameState {
	/*
	 * Read RDY - Task can be executed.
	 * Running RUN - Task is running.
	 * Done DON - Task is done.
	 * Error ERR - Task finished with error or failed to start.
	 * Skipped SKP - Task skipped.
	 * Warning WRN - Warning from parser.
	 * Parser Error PER - Error from parser.
	 * Parser Bad Result PBR - Bad result from parser.
	 */
	RDY("Ready"), RUN("Running"), DON("Done"), ERR("Error"), SKP("Skipped"), WRN("Warning"), PER("Parser Error"), PBR("Parser Bad Result");

	private String state;

	private FrameState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	@Override
	public String toString() {
		return this.state;
	}

}
