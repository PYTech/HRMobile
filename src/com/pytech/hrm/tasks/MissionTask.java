package com.pytech.hrm.tasks;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pytech.hrm.R;
import com.pytech.hrm.events.GetMissionDoneEvent;
import com.pytech.hrm.events.GetMissionFailEvent;
import com.pytech.hrm.models.mission.Mission;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		// Do nothing.
	}

	@Override
	protected void onPostExecute(Integer result) {
		this.mProgressDialog.dismiss();
		if(result != null && result >= 0) {
			String resultMsg = this.mContext.getString(R.string.msg_mission_get_done, result);
			Toast.makeText(this.mContext, resultMsg, Toast.LENGTH_SHORT).show();
			EventBus.getDefault().post(new GetMissionDoneEvent(this.missionList));
		} else {
			EventBus.getDefault().post(new GetMissionFailEvent());
		}
	}
}
