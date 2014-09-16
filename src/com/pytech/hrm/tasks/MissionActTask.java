package com.pytech.hrm.tasks;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.pytech.hrm.R;
import com.pytech.hrm.events.MissionActDoneEvent;
import com.pytech.hrm.events.MissionActFailEvent;
import com.pytech.hrm.exception.AuthException;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.models.rest.ParamPair;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.REST;
import com.pytech.hrm.util.constants.enums.MissionAction;
import com.pytech.hrm.util.rest.RestManager;

import de.greenrobot.event.EventBus;

public class MissionActTask extends AsyncTask<Mission, Integer, String> {
	public Context mContext;
	private ProgressDialog mProgressDialog;
	private MissionAction action;
	
	public MissionActTask(MissionAction action) {
		this.action = action;
	}

	public void setContext(Context context) {
		this.mContext = context;
	}

	@Override
	protected void onPreExecute() {
		this.mProgressDialog = new ProgressDialog(this.mContext);
		this.mProgressDialog.setMessage(this.mContext.getString(R.string.msg_mission_act_progress));
		this.mProgressDialog.setIndeterminate(true);
		this.mProgressDialog.setCancelable(true);
		this.mProgressDialog.setCanceledOnTouchOutside(false);
		this.mProgressDialog.show();
	}

	@Override
	protected String doInBackground(Mission... missions) {
		String result = HRM.TASK_RESULT_OK;
		Mission tempMission = null;
		try {
			for(Mission mission : missions) {
				tempMission = mission;
				List<ParamPair> params = new LinkedList<ParamPair>();
				params.add(new ParamPair(REST.PARAM_MISSION_ID, mission.getOid()));
				params.add(new ParamPair(REST.PARAM_ACTION, this.action.name()));
				RestManager.sendHttpPutRequest(REST.ACTION_PATH, REST.TYPE_APP_JSON, params);
			}
		} catch(IOException e) {
			Log.e(LoginTask.class.getName(), String.format("Perform action[%s] on mission[%s] failed, reason:[%s]", action.name(), tempMission.getName(), e.getMessage()));
			result = HRM.TASK_RESULT_CONN_FAIL;
		} catch(AuthException e) {
			Log.e(LoginTask.class.getName(), String.format("Do mission actions task authentication failed, reason:[%s]", e.getMessage()));
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
		if(HRM.TASK_RESULT_OK.equals(result)) {
			Toast.makeText(this.mContext, this.mContext.getString(R.string.msg_mission_act_done), Toast.LENGTH_SHORT).show();
			EventBus.getDefault().post(new MissionActDoneEvent());
		} else {
			EventBus.getDefault().post(new MissionActFailEvent(result));
		}
	}
}
