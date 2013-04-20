package com.bill.control;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.bill.data.DatabaseHelper;
import com.example.moneymanager.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

public class SettingActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		ActivitySwipeDetector activitySwipeDetector = new ActivitySwipeDetector(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
		layout.setOnTouchListener(activitySwipeDetector);
	}
}
