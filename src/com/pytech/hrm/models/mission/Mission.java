package com.pytech.hrm.models.mission;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.pytech.hrm.util.constants.enums.MissionState;
import com.pytech.hrm.util.constants.enums.OutputType;
import com.pytech.hrm.util.constants.enums.ProductConstants;
import com.pytech.hrm.util.constants.enums.ProjectPriority;
import com.pytech.hrm.util.constants.enums.RenderMode;



@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Mission implements Serializable {

	private static final long serialVersionUID = -6636092398074084048L;

	// private long id;
	private String oid;
	// private RenderEngine service; // enum RenderEngine, ex.MAYA_VRAY
	private String renderingProduct; // product oid
	private String product;//product name
	
	private String name;
	private int frameFirst;
	private int frameLast;
	private int framesPerTask; // @assign by server
	private int framesInc;
	private String workingDirectory; // @assign by server
	private String files; // output image

	// private String engine; // ** render engine ex.v-ray
	private String project; // ** project oid
	private String projectName;// ** project name
	private long projectLastCleanTime; //** project lastCleanTime
	
	private String sceneFile; // ** asset file
	private int corePerFrame; // **

	private OutputType outputType; // frame; animation
	private boolean isConvertVideo;
	private boolean isFileCheck;
	private RenderMode renderMode;
	private String email;

	private int num;
	private String userName; // job owner id (user_id)
	private long timeCreation; // job submission time
	private long timeWait; // subscribe job start
	private long timeDone; // job finish
	private long timeStart;
	private ProjectPriority priority;
	private String dependencyName; // submission name seperate by comma

	private MissionState state; // using af state abbr, ex MissionState.valueOf("DON")

	private int numQueueing;
	private int numRunning;
	private int numDone;
	private int numFail;
	private int numTotal;

	private float progress;

	private String dependency; // mission oid split by ','

	private String compressedFileName;

	private List<MissionCharacteristic> characteristics;
	
	//for usage
	private float averageFrameTime;
	private float coreHours;
	private float cost;
	
	private boolean selected;

	// private Submission submission;

	public Mission() {
	}

	public Mission(String oid, String name, int frameFirst, int frameLast, String project, String sceneFile, MissionState state, int numQueueing,
			int numRunning, int numDone, int numFail, int numTotal) {
		super();
		// this.id = id;
		this.oid = oid;
		this.name = name;
		this.frameFirst = frameFirst;
		this.frameLast = frameLast;
		this.project = project;
		this.sceneFile = sceneFile;
		this.state = state;
		this.numQueueing = numQueueing;
		this.numRunning = numRunning;
		this.numDone = numDone;
		this.numFail = numFail;
		this.numTotal = numTotal;
	}

	public Mission(String oid, String name, int frameFirst, int frameLast, String project, String sceneFile, MissionState state, int numRunning, int numDone,
			int numFail, int numTotal) {

		this.oid = oid;
		this.name = name;
		this.frameFirst = frameFirst;
		this.frameLast = frameLast;
		this.project = project;
		this.sceneFile = sceneFile;
		this.state = state;
		this.numRunning = numRunning;
		this.numDone = numDone;
		this.numFail = numFail;
		this.numTotal = numTotal;

	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	/*
	 * public long getId() {
	 * return id;
	 * }
	 * 
	 * public void setId(long id) {
	 * this.id = id;
	 * }
	 */

	// enum RenderEngine
	/*
	 * public RenderEngine getService() {
	 * return service;
	 * }
	 * 
	 * public void setService(RenderEngine service) {
	 * this.service = service;
	 * }
	 */

	public String getRenderingProduct() {
		return renderingProduct;
	}

	public void setRenderingProduct(String renderingProduct) {
		this.renderingProduct = renderingProduct;
	}

	public String getProduct() {
		if(!StringUtils.isEmpty(getRenderingProduct())){
			return ProductConstants.convertToEnumType(getRenderingProduct()).getName();
		}
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFrameFirst() {
		return frameFirst;
	}

	public void setFrameFirst(int frameFirst) {
		this.frameFirst = frameFirst;
	}

	public int getFrameLast() {
		return frameLast;
	}

	public void setFrameLast(int frameLast) {
		this.frameLast = frameLast;
	}

	public int getFramesPerTask() {
		return framesPerTask;
	}

	public void setFramesPerTask(int framesPerTask) {
		this.framesPerTask = framesPerTask;
	}

	public int getFramesInc() {
		return framesInc;
	}

	public void setFramesInc(int framesInc) {
		this.framesInc = framesInc;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public long getProjectLastCleanTime() {
		return projectLastCleanTime;
	}

	public void setProjectLastCleanTime(long projectLastCleanTime) {
		this.projectLastCleanTime = projectLastCleanTime;
	}

	public String getSceneFile() {
		return sceneFile;
	}

	public void setSceneFile(String sceneFile) {
		this.sceneFile = sceneFile;
	}

	public int getCorePerFrame() {
		return corePerFrame;
	}

	public void setCorePerFrame(int corePerFrame) {
		this.corePerFrame = corePerFrame;
	}

	public OutputType getOutputType() {
		return outputType;
	}

	public void setOutputType(OutputType outputType) {
		this.outputType = outputType;
	}

	public boolean isConvertVideo() {
		return isConvertVideo;
	}

	public void setConvertVideo(boolean isConvertVideo) {
		this.isConvertVideo = isConvertVideo;
	}

	public boolean isFileCheck() {
		return isFileCheck;
	}

	public void setFileCheck(boolean isFileCheck) {
		this.isFileCheck = isFileCheck;
	}

	public RenderMode getRenderMode() {
		return renderMode;
	}

	public void setRenderMode(RenderMode renderMode) {
		this.renderMode = renderMode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * public String getState() {
	 * return state;
	 * }
	 * 
	 * public void setState(String state) {
	 * this.state = state;
	 * }
	 */

	public MissionState getState() {
		return state;
	}

	public void setState(MissionState state) {
		this.state = state;
	}

	public int getNumQueueing() {

		int numInStatus = getNumRunning() + getNumDone() + getNumFail();
		// System.out.println("numInStatus=" + numInStatus);

		numQueueing = ((getNumTotal() - numInStatus) <= 0) ? 0 : (getNumTotal() - numInStatus);
		// System.out.println("numQueueing=" + numQueueing);

		return numQueueing;
	}

	public void setNumQueueing(int numQueueing) {
		this.numQueueing = numQueueing;
	}

	public int getNumRunning() {
		return numRunning;
	}

	public void setNumRunning(int numRunning) {
		this.numRunning = numRunning;
	}

	public int getNumDone() {
		return numDone;
	}

	public void setNumDone(int numDone) {
		this.numDone = numDone;
	}

	public int getNumFail() {
		return numFail;
	}

	public void setNumFail(int numFail) {
		this.numFail = numFail;
	}

	public int getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}

	public float getProgress() {
		if(getNumTotal() != 0) {
			progress = (float) getNumDone() / (float) getNumTotal();
		} else {
			progress = 0;
		}
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	public String getDependency() {
		if(dependency != null) {
			dependency = dependency.replaceAll(",", "|");
		}
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	public String getCompressedFileName() {
		return compressedFileName;
	}

	public void setCompressedFileName(String compressedFileName) {
		this.compressedFileName = compressedFileName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getTimeCreation() {
		return timeCreation;
	}

	public void setTimeCreation(long timeCreation) {
		this.timeCreation = timeCreation;
	}

	public long getTimeWait() {
		return timeWait;
	}

	public void setTimeWait(long timeWait) {
		this.timeWait = timeWait;
	}

	public long getTimeDone() {
		return timeDone;
	}

	public void setTimeDone(long timeDone) {
		this.timeDone = timeDone;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public ProjectPriority getPriority() {
		return priority;
	}

	public void setPriority(ProjectPriority priority) {
		this.priority = priority;
	}

	public String getDependencyName() {
		return dependencyName;
	}

	public void setDependencyName(String dependencyName) {
		this.dependencyName = dependencyName;
	}

	public List<MissionCharacteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<MissionCharacteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public float getAverageFrameTime() {
		return averageFrameTime;
	}

	public void setAverageFrameTime(float averageFrameTime) {
		this.averageFrameTime = averageFrameTime;
	}

	public float getCoreHours() {
		return coreHours;
	}

	public void setCoreHours(float coreHours) {
		this.coreHours = coreHours;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "Mission [oid=" + oid + ", renderingProduct=" + renderingProduct + ", product=" + product + ", name=" + name + ", frameFirst=" + frameFirst
				+ ", frameLast=" + frameLast + ", framesPerTask=" + framesPerTask + ", framesInc=" + framesInc + ", workingDirectory=" + workingDirectory
				+ ", files=" + files + ", project=" + project + ", projectName=" + projectName + ", projectLastCleanTime=" + projectLastCleanTime
				+ ", sceneFile=" + sceneFile + ", corePerFrame=" + corePerFrame + ", outputType=" + outputType + ", isConvertVideo=" + isConvertVideo
				+ ", isFileCheck=" + isFileCheck + ", renderMode=" + renderMode + ", email=" + email + ", num=" + num + ", userName=" + userName
				+ ", timeCreation=" + timeCreation + ", timeWait=" + timeWait + ", timeDone=" + timeDone + ", timeStart=" + timeStart + ", priority="
				+ priority + ", dependencyName=" + dependencyName + ", state=" + state + ", numQueueing=" + numQueueing + ", numRunning=" + numRunning
				+ ", numDone=" + numDone + ", numFail=" + numFail + ", numTotal=" + numTotal + ", progress=" + progress + ", dependency=" + dependency
				+ ", compressedFileName=" + compressedFileName + ", characteristics=" + characteristics + ", averageFrameTime=" + averageFrameTime
				+ ", coreHours=" + coreHours + ", cost=" + cost + "]";
	}

	

}
