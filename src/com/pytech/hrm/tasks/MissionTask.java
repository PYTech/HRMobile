package com.pytech.hrm.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pytech.hrm.R;
import com.pytech.hrm.events.GetMissionDoneEvent;
import com.pytech.hrm.events.GetMissionFailEvent;
import com.pytech.hrm.exception.AuthException;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.models.rest.ParamPair;
import com.pytech.hrm.models.rest.RestTask;
import com.pytech.hrm.util.constants.REST;
import com.pytech.hrm.util.rest.JsonConverter;
import com.pytech.hrm.util.rest.RestManager;

import de.greenrobot.event.EventBus;

public class MissionTask extends AsyncTask<Void, Integer, Integer> {
	private Context mContext;
	private ProgressDialog mProgressDialog;
	private List<Mission> missionList = new ArrayList<Mission>();

	public void setContext(Context context) {
		this.mContext = context;
	}

	@Override
	protected void onPreExecute() {
		this.mProgressDialog = new ProgressDialog(this.mContext);
		this.mProgressDialog.setMessage(this.mContext.getString(R.string.msg_mission_get_progress));
		this.mProgressDialog.setIndeterminate(true);
		this.mProgressDialog.setCancelable(true);
		this.mProgressDialog.setCanceledOnTouchOutside(false);
		this.mProgressDialog.show();
	}

	@Override
	protected Integer doInBackground(Void... params) {
		try {
			List<ParamPair> paramPairs = new LinkedList<ParamPair>();
			paramPairs.add(new ParamPair(REST.PARAM_USER_ID, RestManager.getUserId()));
			paramPairs.add(new ParamPair(REST.PARAM_FILTER_KEY, REST.PARAM_FILTER_MISSION_LIST_RUNNING));
			RestTask restTask = RestManager.sendHttpGetRequest(REST.MISSION_PATH, REST.TYPE_APP_JSON, paramPairs);
			List<Mission> missions = JsonConverter.convertFromObjects(Mission.class, restTask);
		} catch(IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(AuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(this.mProgressDialog.isShowing()) {
				this.mProgressDialog.dismiss();
			}
		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		// Do nothing.
	}

	@Override
	protected void onPostExecute(Integer result) {
		if(result != null && result >= 0) {
			String resultMsg = this.mContext.getString(R.string.msg_mission_get_done, result);
			Toast.makeText(this.mContext, resultMsg, Toast.LENGTH_SHORT).show();
			EventBus.getDefault().post(new GetMissionDoneEvent(this.missionList));
		} else {
			EventBus.getDefault().post(new GetMissionFailEvent());
		}
	}
}
