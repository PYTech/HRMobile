package com.pytech.hrm.managers;

import java.util.List;

import com.pytech.hrm.models.mission.Frame;

import android.content.Context;

public interface IFrameManager {
	void getAllFrames(Context context, boolean showLoading, String missionOid);
	
	void showFramePreview(Context context, Frame frame);
	
	void showFrameLog(Context context, Frame frame);
	
	void downloadResultPics(Context context, List<Frame> frameList);
}
