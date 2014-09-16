package com.pytech.hrm.util.constants.enums;

public enum MissionAction {
    RERUN("RERUN"),
    CANCEL("CANCEL"),
    HOLD("HOLD"),
    RESUME("RESUME"),
    RETRY("RETRY"),
    SYS_HOLD("SYS_HOLD");

    
    private String action;
    private MissionAction(String action) {
        this.action = action;
    }
    
    public String getAction() {
        return this.action;
    }	 

}
