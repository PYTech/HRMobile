package com.pytech.hrm.util.constants.enums;


//@JsonSerialize(using = MissionStateSerializer.class)
public enum MissionState {//
	RDY("Ready"), RUN("Running"), WD("Waiting Dependencies"), DON("Done"), ERR("Error"), SKP("Skipped"),
	PAUSE("Pause"),CANCELING("Canceling"),HOLDING("Holding"),WT("Waiting Time"),OFF("Offline"),WDP("Waiting Depedencies"),WTM("Waiting Time"),GM("Generating Move"),CMP("Compressing"),SYS_HOLDING("System Holding"),SYS_PAUSE("System Pause"),MOVGEN("Move Generating"),MOVGEN_ERR("Move Generation Fail");

	private String state;

	private MissionState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}
	
	@Override
	public String toString(){
		return this.state;
	}

}
