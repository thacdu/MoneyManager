package com.bill.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.TextView;

import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.example.moneymanager.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class ViewSellBillActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_sell_bill);
		
		try{
			Dao<Bill, Integer> dao = getHelper().getBillDao();
			List<Bill> l = new ArrayList<Bill>();
			l.addAll(dao.queryForAll());
			
			TextView t1 = (TextView) findViewById(R.id.id1);
			
			t1.setText(String.valueOf(l.size()));
		}catch(SQLException e){
			throw new RuntimeException();
		}
	}
}
