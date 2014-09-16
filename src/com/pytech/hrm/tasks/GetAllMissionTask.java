package com.pytech.hrm.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.pytech.hrm.R;
import com.pytech.hrm.events.GetMissionDoneEvent;
import com.pytech.hrm.events.GetMissionFailEvent;
import com.pytech.hrm.exception.AuthException;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.models.rest.ParamPair;
import com.pytech.hrm.models.rest.RestTask;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.REST;
import com.pytech.hrm.util.rest.JsonConverter;
import com.pytech.hrm.util.rest.RestManager;

import de.greenrobot.event.EventBus;

public class GetAllMissionTask extends AsyncTask<Void, Integer, String> {
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private List<Mission> missionList = new ArrayList<Mission>();
	private boolean showLoading = true;
	
	public GetAllMissionTask(boolean showLoading) {
		this.showLoading = showLoading;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	@Override
	protected void onPreExecute() {
		this.mProgressDialog = new ProgressDialog(this.mContext);
		if(this.showLoading) {
			this.mProgressDialog.setMessage(this.mContext.getString(R.string.msg_mission_get_progress));
			this.mProgressDialog.setIndeterminate(true);
			this.mProgressDialog.setCancelable(true);
			this.mProgressDialog.setCanceledOnTouchOutside(false);
			this.mProgressDialog.show();
		}
	}

	@Override
	protected String doInBackground(Void... params) {
		String result = HRM.TASK_RESULT_OK;
		try {
			// 取得執行中的任務列表
			List<ParamPair> paramPairs = new LinkedList<ParamPair>();
			paramPairs.add(new ParamPair(REST.PARAM_USER_ID, RestManager.getUserId()));
			paramPairs.add(new ParamPair(REST.PARAM_FILTER_KEY, REST.PARAM_FILTER_MISSION_LIST_RUNNING));
			RestTask restTask = RestManager.sendHttpGetRequest(REST.MISSION_PATH, REST.TYPE_APP_JSON, paramPairs);
			List<Mission> missions = JsonConverter.convertFromObjects(Mission.class, restTask.getTarget());
			this.missionList.addAll(missions);
			
			// 取得歷史任務列表
			paramPairs = new LinkedList<ParamPair>();
			paramPairs.add(new ParamPair(REST.PARAM_USER_ID, RestManager.getUserId()));
			paramPairs.add(new ParamPair(REST.PARAM_FILTER_KEY, REST.PARAM_FILTER_MISSION_LIST_HISTORY));
			restTask = RestManager.sendHttpGetRequest(REST.MISSION_PATH, REST.TYPE_APP_JSON, paramPairs);
			missions = JsonConverter.convertFromObjects(Mission.class, restTask.getTarget());
			this.missionList.addAll(missions);
		} catch(IOException e) {
			Log.e(LoginTask.class.getName(), String.format("Get missions task failed, reason:[%s]", e.getMessage()));
			result = HRM.TASK_RESULT_CONN_FAIL;
		} catch(AuthException e) {
			Log.e(LoginTask.class.getName(), String.format("Get missions task authentication failed, reason:[%s]", e.getMessage()));
			result = HRM.TASK_RESULT_AUTH_FAIL;
		} finally {
			if(this.mProgressDialog.isShowing()) {
				this.mProgressDialog.dismiss();
			}
		}
		return result;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		// Do nothing.
	}

	@Override
	protected void onPostExecute(String result) {
		if(StringUtils.equals(HRM.TASK_RESULT_OK, result)) {
			if(this.showLoading) {
				String resultMsg = this.mContext.getString(R.string.msg_mission_get_done, this.missionList.size());
				Toast.makeText(this.mContext, resultMsg, Toast.LENGTH_SHORT).show();
			}
			EventBus.getDefault().post(new GetMissionDoneEvent(this.missionList));
		} else {
			EventBus.getDefault().post(new GetMissionFailEvent(result));
		}
	}
}
