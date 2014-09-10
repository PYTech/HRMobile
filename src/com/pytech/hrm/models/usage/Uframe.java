package com.pytech.hrm.models.usage;

import java.util.Date;

import com.pytech.hrm.models.mission.Frame;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.util.constants.enums.RenderLevel;
import com.pytech.hrm.util.constants.enums.RenderMode;

public class Uframe {
	private String oid;
	private Date timeRecord;
	private Frame frame = new Frame();	
	private String renderAfName;
	private int retryCount = 0;
	private int timeUsed = 0;
	private float cost = 0;
	private RenderLevel level;
	private RenderMode renderMode;
	private int capacity;
	private String poolOid;
	
	// Billing required variables.
	private String projOwnerId;
	private String projOwnerOid;
	
	private float balance;
	
	private Mission mission = new Mission();
	
	
	public Uframe(){
		super();
	}
	
	public Uframe(int retryCount, int timeUsed, int cost) {
		super();
		this.retryCount = retryCount;
		this.timeUsed = timeUsed;
		this.cost = cost;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getTimeRecord() {
		return timeRecord;
	}

	public void setTimeRecord(Date timeRecord) {
		this.timeRecord = timeRecord;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public String getRenderAfName() {
		return renderAfName;
	}

	public void setRenderAfName(String renderAfName) {
		this.renderAfName = renderAfName;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(int timeUsed) {
		this.timeUsed = timeUsed;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public RenderLevel getLevel() {
		return level;
	}

	public void setLevel(RenderLevel level) {
		this.level = level;
	}

	public String getProjOwnerOid() {
		return projOwnerOid;
	}

	public void setProjOwnerOid(String projOwnerOid) {
		this.projOwnerOid = projOwnerOid;
	}

	public String getProjOwnerId() {
		return projOwnerId;
	}

	public void setProjOwnerId(String projOwnerId) {
		this.projOwnerId = projOwnerId;
	}

	public RenderMode getRenderMode() {
		return renderMode;
	}

	public void setRenderMode(RenderMode renderMode) {
		this.renderMode = renderMode;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getPoolOid() {
		return poolOid;
	}

	public void setPoolOid(String poolOid) {
		this.poolOid = poolOid;
	}
	

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	

	

	
}
