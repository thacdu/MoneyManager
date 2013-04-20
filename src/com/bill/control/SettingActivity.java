package com.bill.control;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import com.example.moneymanager.R;

public class SettingActivity extends PreferenceActivity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.setting);
		setIcon();
	}
	
	private void setIcon(){
		Preference itemAdd = (Preference) findPreference("item_add");
		itemAdd.setIcon(R.drawable.btn_add);
	}
}
