package com.bill.control;

import android.app.Activity;
import android.os.Bundle;

public class SaveActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		super.onBackPressed();
		onBackPressed();
	}
}
