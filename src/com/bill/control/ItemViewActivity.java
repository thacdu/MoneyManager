package com	.bill.control;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;

import com.bill.control.adapter.ItemViewListAdapter;
import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.example.moneymanager.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class ItemViewActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_all_item);
		
		try{
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			List<Item> itemList = itemDao.queryForAll();
			
			if(itemList.size() > 0){
				String[] itemNameList = new String[itemList.size()];
				String[] itemPriceList = new String[itemList.size()];
				
				for(int i = 0; i < itemList.size(); i++){
					itemNameList[i] = itemList.get(i).getName();
					itemPriceList[i] = itemList.get(i).getPriceString();
				}
				
				ListView listView = (ListView) findViewById(R.id.listview);
				listView.setAdapter(new ItemViewListAdapter(this, itemNameList, itemPriceList));
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
