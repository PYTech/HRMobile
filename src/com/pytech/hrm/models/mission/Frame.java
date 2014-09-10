package com.pytech.hrm.models.mission;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import android.media.Image;

import com.pytech.hrm.util.constants.enums.FrameState;
import com.pytech.hrm.util.constants.enums.RenderMode;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Frame implements Serializable{

	private static final long serialVersionUID = 4386197122993210256L;
	private String oid;
	private long id; //global unique
	private int frameSeq; // unique in one block (ex frange 20-40, frameSeq should be 20,21,..and so on)
	private FrameState state;
	private long startTime;
	private long endTime;
	private long duration;
	
	/*usage from Uframe*/
	private int startCount; //
	private int totTimeUsed; //sum uframe.timeUsed
	private float totCost; //sum uframe.cost
	private String poolOid;
	private RenderMode renderMode;
	
	// for frame preview
	private List<Image> images;
	
	// for mission reference
	private String missionOid;
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFrameSeq() {
		return frameSeq;
	}
	public void setFrameSeq(int frameSeq) {
		this.frameSeq = frameSeq;
	}
	public FrameState getState() {
		return state;
	}
	public void setState(FrameState state) {
		this.state = state;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public int getStartCount() {
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}
	public int getTotTimeUsed() {
		return totTimeUsed;
	}
	public void setTotTimeUsed(int totTimeUsed) {
		this.totTimeUsed = totTimeUsed;
	}	
	
	public float getTotCost() {
		return totCost;
	}
	public void setTotCost(float totCost) {
		this.totCost = totCost;
	}
	
	public String getPoolOid() {
		return poolOid;
	}
	public void setPoolOid(String poolOid) {
		this.poolOid = poolOid;
	}
	public RenderMode getRenderMode() {
		return renderMode;
	}
	public void setRenderMode(RenderMode renderMode) {
		this.renderMode = renderMode;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public String getMissionOid() {
		return missionOid;
	}
	public void setMissionOid(String missionOid) {
		this.missionOid = missionOid;
	}
	@Override
	public String toString() {
		return "Frame [oid=" + oid + ", id=" + id + ", frameSeq=" + frameSeq + ", state=" + state + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration + ", startCount=" + startCount + ", totTimeUsed=" + totTimeUsed + ", totCost=" + totCost + ", poolOid=" + poolOid
				+ ", renderMode=" + renderMode + ", images=" + images + ", missionOid=" + missionOid + "]";
	}
	
	
}
