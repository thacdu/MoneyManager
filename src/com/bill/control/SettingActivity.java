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
		
		Preference itemList = (Preference) findPreference("item_view");
		itemList.setIcon(R.drawable.btn_list);
		
		Preference dataRestore = (Preference) findPreference("data_restore");
		dataRestore.setIcon(R.drawable.btn_restore);
		
		Preference dataBackup = (Preference) findPreference("data_backup");
		dataBackup.setIcon(R.drawable.btn_backup);
		
		Preference dataDelete = (Preference) findPreference("data_delete");
		dataDelete.setIcon(R.drawable.btn_remove);
		
		Preference otherAlert = (Preference) findPreference("other_alert");
		otherAlert.setIcon(R.drawable.btn_alert);
		
		Preference otherLanguage = (Preference) findPreference("other_language");
		otherLanguage.setIcon(R.drawable.btn_language);
	}
}
