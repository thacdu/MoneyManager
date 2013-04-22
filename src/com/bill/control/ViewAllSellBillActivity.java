package com.bill.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bill.control.adapter.BillViewListAdapter;
import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.bill.data.ItemConnection;
import com.bill.data.ItemOnBill;
import com.example.moneymanager.R;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class ViewAllSellBillActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_all_sell_bill);
		try{
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			
			final List<Bill> billList = billDao.queryBuilder().orderBy("date", false).query();			
			int n = billList.size();
			
			if(n > 0){
				String[] billTableList = new String[n];
				String[] billDateList = new String[n];
				String[] billPriceList = new String[n];
				
				for(int i = 0; i < n; i++){
					billTableList[i] = billList.get(i).getTextTableNumber();
					billDateList[i] = billList.get(i).getDate();
					billPriceList[i] = getTotalPrice(billList.get(i));
				}
				
				ListView listView = (ListView) findViewById(R.id.listview);
				listView.setAdapter(new BillViewListAdapter(this, billDateList, billTableList, billPriceList));
				listView.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int position,
							long id) {
						// TODO Auto-generated method stub
						Bill bill = billList.get(position);
						
						Intent intent = new Intent(getBaseContext(), ViewSellBillDetailActivity.class);
						intent.putExtra("thacdu", bill.getID());
						startActivity(intent);
					}
					
				});
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	private String getTotalPrice(Bill bill){
		int price = 0;
		try{
			Dao<ItemOnBill, Integer> itemOnBillDao = getHelper().getItemOnBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			List<ItemOnBill> list = itemOnBillDao.queryBuilder().where().eq("bill_id", bill).query();
			
			for(int i = 0; i < list.size(); i++){
				ItemOnBill item = list.get(i);
				int id = item.getItem().getId();
				Item temp = itemDao.queryForId(id);
				price += temp.getPrice() * item.getNumber();
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return String.valueOf(price);
	}
}
