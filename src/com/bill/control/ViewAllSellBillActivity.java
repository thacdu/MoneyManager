package com.bill.control;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.example.moneymanager.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class ViewAllSellBillActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_all_sell_bill);
		try{
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			
			List<Bill> billList = billDao.queryBuilder().orderBy("date", false).query();
			
			if(billList.size() > 0){
				String[] billNameList = new String[billList.size()];
				for(int i = 0; i < billList.size(); i++){
					billNameList[i] = String.valueOf(billList.get(i).getID());
				}
				
				ListView listView = (ListView) findViewById(R.id.listview);
				listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_all_bill, billNameList));
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
