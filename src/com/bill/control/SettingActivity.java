package com.bill.control;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.bill.data.ItemConnection;
import com.example.moneymanager.R;

public class SettingActivity extends PreferenceActivity{
	
	Preference itemAdd;
	Preference itemList;
	Preference dataRestore;
	Preference dataBackup;
	Preference dataDelete;
	Preference otherAlert;
	Preference otherLanguage;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.setting);
		getPreference();
		setIcon();
		
		itemAdd.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				
				final View view = getLayoutInflater().inflate(R.layout.add_item_alert, null);
				Button ok = (Button)view.findViewById(R.id.ok);
				Button cancel = (Button)view.findViewById(R.id.cancel);
				
				final AlertDialog dialog = new AlertDialog.Builder(SettingActivity.this)
					.setTitle(R.string.item_info)
					.setView(view)
					.create();;
				
				ok.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						EditText nameText = (EditText) view.findViewById(R.id.name);
						EditText priceText = (EditText) view.findViewById(R.id.price);
						String name = nameText.getText().toString().toString();
						String price = priceText.getText().toString().toString();
						Intent intent = new Intent(getBaseContext(), ItemConnection.class);
						intent.putExtra("name", name);
						intent.putExtra("price", price);
						dialog.dismiss();
						startActivity(intent);
					}
				});
				
				cancel.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}	
				});
				
				dialog.show();
				return true;
			}
		});
		
		itemList.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getBaseContext(), ItemViewActivity.class));
				return true;
			}
		});
		
		
	}
	
	private void getPreference(){
		itemAdd = (Preference) findPreference("item_add");		
		itemList = (Preference) findPreference("item_view");
		dataRestore = (Preference) findPreference("data_restore");
		dataBackup = (Preference) findPreference("data_backup");
		dataDelete = (Preference) findPreference("data_delete");
		otherAlert = (Preference) findPreference("other_alert");
		otherLanguage = (Preference) findPreference("other_language");
	}
	
	private void setIcon(){
		itemAdd.setIcon(R.drawable.btn_add);
		itemList.setIcon(R.drawable.btn_list);
		dataRestore.setIcon(R.drawable.btn_restore);
		dataBackup.setIcon(R.drawable.btn_backup);
		dataDelete.setIcon(R.drawable.btn_remove);
		otherAlert.setIcon(R.drawable.btn_alert);
		otherLanguage.setIcon(R.drawable.btn_language);
	}
}
