package com.bill.data;

import java.sql.SQLException;

import android.os.Bundle;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class ItemConnection extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		String name = getIntent().getStringExtra("name");
		String price = getIntent().getStringExtra("price");
		Item newItem = new Item(name, price);
		
		try{
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			itemDao.create(newItem);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		finish();
	}
}
