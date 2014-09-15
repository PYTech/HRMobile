package com.pytech.hrm.tasks;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.pytech.hrm.R;
import com.pytech.hrm.events.LoginFailEvent;
import com.pytech.hrm.events.LoginSuccessEvent;
import com.pytech.hrm.exception.AuthException;
import com.pytech.hrm.models.user.UserVO;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.rest.RestManager;

import de.greenrobot.event.EventBus;

public class LoginTask extends AsyncTask<UserVO, Integer, String> {
	public Context mContext;
	private ProgressDialog mProgressDialog;

	public void setContext(Context context) {
		this.mContext = context;
	}

	@Override
	protected void onPreExecute() {
		this.mProgressDialog = new ProgressDialog(this.mContext);
		this.mProgressDialog.setMessage(this.mContext.getString(R.string.msg_login_progress));
		this.mProgressDialog.setIndeterminate(true);
		this.mProgressDialog.setCancelable(true);
		this.mProgressDialog.setCanceledOnTouchOutside(false);
		this.mProgressDialog.show();
	}

	@Override
	protected String doInBackground(UserVO... params) {
		UserVO userVO = params[0];
		String result = HRM.TASK_RESULT_OK;
		try {
			RestManager.login(userVO.getId(), userVO.getPassword());
		} catch(AuthException e) {
			Log.e(LoginTask.class.getName(), String.format("Login task auth failed for username[%s] failed, reason:[%s]", userVO.getId(), e.getMessage()));
			result = HRM.TASK_RESULT_AUTH_FAIL;
		} catch(IOException e) {
			Log.e(LoginTask.class.getName(), String.format("Login task for username[%s] failed, reason:[%s]", userVO.getId(), e.getMessage()));
			result = HRM.TASK_RESULT_CONN_FAIL;
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
			Toast.makeText(this.mContext, this.mContext.getString(R.string.msg_login_ok), Toast.LENGTH_SHORT).show();
			EventBus.getDefault().post(new LoginSuccessEvent());
		} else {
			EventBus.getDefault().post(new LoginFailEvent(result));
		}
	}
}
