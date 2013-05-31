package com.bill.control;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.bill.data.ItemOnBill;
import com.example.moneymanager.R;
import com.example.moneymanager.R.id;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class ViewSellBillDetailActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_sell_bill);
		
		/* Action bar */
		final ActionBar actionBar = (ActionBar) findViewById(id.actionbar);
		actionBar.setHomeAction(new IntentAction(this, ActionBarControl.createIntent(this), R.drawable.ic_title_home_default));
       
		int id = getIntent().getExtras().getInt("id");
		

		try{
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			Bill bill = billDao.queryForId(id);
			
			Dao<ItemOnBill, Integer> itemOnBillDao = getHelper().getItemOnBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			List<ItemOnBill> list = itemOnBillDao.queryBuilder().where().eq("bill_id", bill).query();
			
			int n = list.size();
			int billPrice = 0;
			String[] name = new String[n];
			String[] number = new String[n];
			String[] price = new String[n];
			
			for(int i = 0; i < n; i++){
				int idTemp = list.get(i).getItem().getId();
				Item iTemp = itemDao.queryForId(idTemp);
				name[i] = iTemp.getName();
				number[i] = list.get(i).getNumberString();
				price[i] = iTemp.getPriceString();
				billPrice += list.get(i).getNumber() * iTemp.getPrice();
			}
			
			TextView textView = (TextView) findViewById(R.id.table);
			textView.setText("Bàn số " + bill.getTextTableNumber());
			TextView totalPrice = (TextView) findViewById(R.id.total_price);
			totalPrice.setText("Tổng số tiền: " + billPrice);
			
	        
			ListView listview = (ListView) findViewById(R.id.listview);
			AddSellBillListViewAdapter adapter = new AddSellBillListViewAdapter(this, name, number, price);
			listview.setAdapter(adapter);
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
