package com.pytech.hrm.adapters;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pytech.hrm.R;
import com.pytech.hrm.models.mission.Mission;
import com.pytech.hrm.util.TimeUtils;
import com.pytech.hrm.util.constants.Colors;
import com.pytech.hrm.util.constants.HRM;
import com.pytech.hrm.util.constants.SoftwareKey;
import com.pytech.hrm.util.constants.enums.MissionState;

public class MissionAdapter extends ArrayAdapter<Mission> {
	private int resource;
	private List<Mission> missionList;

	public MissionAdapter(Context context, int resource, List<Mission> missions) {
		super(context, resource, missions);
		this.resource = resource;
		this.missionList = missions;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout missionView;
		final Mission mission = this.get(position);

		if(convertView == null) {
			missionView = new LinearLayout(this.getContext());
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			inflater.inflate(this.resource, missionView, true);
		} else {
			missionView = (LinearLayout) convertView;
		}

		// 讀取任務資訊相關欄位
		CheckBox selected = (CheckBox) missionView.findViewById(R.id.selected_item);
		ImageView softwareIcon = (ImageView) missionView.findViewById(R.id.type_software);
		TextView missionTitle = (TextView) missionView.findViewById(R.id.title_text);
		TextView missionInfo = (TextView) missionView.findViewById(R.id.info_text);
		ProgressBar progress = (ProgressBar) missionView.findViewById(R.id.progress);

		// 設定算圖軟體圖示
		softwareIcon.setImageResource(SoftwareKey.convertIconImage(mission.getProduct()));

		// 設定任務名稱
		missionTitle.setText(mission.getName());

		// 設定是否已選擇
		selected.setChecked(mission.isSelected());

		// 設定執行進度與背景顏色
		MissionState state = mission.getState();
		long start = mission.getTimeStart();
		String durationStr = this.getContext().getString(R.string.label_duration_unknown);
		switch(state) {
			case RDY:
			case WD:
			case WDP:
			case WT:
			case WTM:
				missionView.setBackgroundColor(Colors.WAITING.parseColor());
				progress.setVisibility(View.GONE);
				break;
			case RUN:
			case GM:
			case CMP:
			case MOVGEN:
			case ASSEMBLE:
				missionView.setBackgroundColor(Colors.RUNNING.parseColor());
				progress.setVisibility(View.VISIBLE);
				progress.setProgress((int) (mission.getProgress() * HRM.PROGRESS_MAX));
				long duration = System.currentTimeMillis() - start;
				durationStr = TimeUtils.toDurationString(this.getContext(), duration);
				break;
			case DON:
				missionView.setBackgroundColor(Colors.DONE.parseColor());
				progress.setVisibility(View.VISIBLE);
				progress.setProgress(HRM.PROGRESS_MAX);
				duration = mission.getTimeDone() - start;
				durationStr = TimeUtils.toDurationString(this.getContext(), duration);
				break;
			case ERR:
			case ASSEMBLE_ERR:
			case MOVGEN_ERR:
				missionView.setBackgroundColor(Colors.ERROR.parseColor());
				progress.setVisibility(View.GONE);
				break;
			case PAUSE:
			case SYS_HOLDING:
			case SYS_PAUSE:
			case HOLDING:
				missionView.setBackgroundColor(Colors.PAUSED.parseColor());
				progress.setVisibility(View.VISIBLE);
				progress.setProgress(HRM.PROGRESS_MAX);
				break;
			case SKP:
			case CANCELING:
			case OFF:
			default:
				missionView.setBackgroundColor(Colors.CANCELLED.parseColor());
				progress.setVisibility(View.GONE);
				break;
		}

		// 設定已執行時間，狀態
		String formatedString = this.getContext().getString(R.string.label_mission_info, StringUtils.EMPTY);
		missionInfo.setText(String.format(formatedString, durationStr, state.toString()));

		// 為 checkbox 附加 listener
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mission.setSelected(isChecked);
			}
		};
		selected.setOnCheckedChangeListener(listener);
		
		return missionView;
	}

	public void set(int index, Mission mission) {
		if(index > 0 && index < this.missionList.size()) {
			this.missionList.set(index, mission);
			this.notifyDataSetChanged();
		}
	}

	public Mission get(int index) {
		return this.missionList.get(index);
	}
}
