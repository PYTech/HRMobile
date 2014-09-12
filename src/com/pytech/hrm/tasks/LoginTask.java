package com.pytech.hrm.tasks;

import java.io.IOException;

import org.apache.http.auth.AuthenticationException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pytech.hrm.R;
import com.pytech.hrm.events.LoginSuccessEvent;
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
		try {
			RestManager.login(userVO.getId(), userVO.getPassword());
		} catch(AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return HRM.RESULT_OK;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		// Do nothing.
	}

	@Override
	protected void onPostExecute(String result) {
		this.mProgressDialog.dismiss();
		if(HRM.RESULT_OK.equals(result)) {
			Toast.makeText(this.mContext, this.mContext.getString(R.string.msg_login_ok), Toast.LENGTH_SHORT).show();
			EventBus.getDefault().post(new LoginSuccessEvent());
		}
	}

}
