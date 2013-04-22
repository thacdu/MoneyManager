package com.bill.control;
     
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
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
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class AddSellBill extends OrmLiteBaseActivity<DatabaseHelper> {
	
	private String[] name;
	private String[] number;
	private String[] price;
	
	AddSellBillListViewAdapter adapter;
	ListView listview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_sell_bill);
		
		/* Action bar */
		final ActionBar actionBar = (ActionBar) findViewById(id.actionbar);
		actionBar.setHomeAction(new IntentAction(this, ActionBarControl.createIntent(this), R.drawable.ic_title_home_default));
		final Action saveAction = new SaveAction();
		actionBar.setTitle(R.string.add_sell_bill);
        actionBar.addAction(saveAction);
        
        readData();
        
        /* List view */
		listview = (ListView) findViewById(id.listview);
		adapter = new AddSellBillListViewAdapter(this, name, number, price);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				int n = Integer.parseInt(number[position]);
				number[position] = String.valueOf(n+1);
				adapter.notifyDataSetChanged();
			}
			
		});
	}
	
	/* Save Action Class for Save Action on Action Bar */
	private class SaveAction extends AbstractAction{
		public SaveAction(){
			super(R.drawable.accept);
		}
		
		@Override
		public void performAction(View view) {
			// TODO Auto-generated method stub
			saveAction();
		}	
	}
	
	public void saveAction(){
		int n = listview.getChildCount();
		Bill bill;
		ArrayList<ItemOnBill> itemList = new ArrayList<ItemOnBill>();
		
		try{
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			Dao<ItemOnBill, Integer> itemOnBillDao = getHelper().getItemOnBillDao();
			
			EditText tableNumber = (EditText) findViewById(id.tableNumber);

			bill = new Bill(tableNumber.getText().toString().toString(), new Date(System.currentTimeMillis()));
			billDao.create(bill);
			
			for(int i = 0; i < n; i++){
				View currentView = listview.getChildAt(i);
				TextView number = (TextView) currentView.findViewById(id.number);
				if(! number.getText().equals("Số lượng : 0")){
					TextView price = (TextView) currentView.findViewById(id.price);
					TextView name = (TextView) currentView.findViewById(id.name);
					String nameString = name.getText().toString().toString();
					String priceString = price.getText().toString().toString();
					String numberString = number.getText().toString().toString();
					numberString = numberString.substring(11, numberString.length());
					
					Item item = itemDao.queryBuilder().where()
							.eq("name", nameString)
							.and()
							.eq("price", Integer.parseInt(priceString)).queryForFirst();
					
					ItemOnBill currentItem = new ItemOnBill(item, numberString, bill);
					itemOnBillDao.create(currentItem);
					itemList.add(currentItem);
				}
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		onBackPressed();
		ViewSellBillActivity.setObject(bill, itemList);
		startActivity(new Intent(this, ViewSellBillActivity.class));
	}
	
	private void readData(){
		try{
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			List<Item> itemList = itemDao.queryForAll();
			
			if(itemList.size() > 0){
				name = new String[itemList.size()];
				price = new String[itemList.size()];
				number = new String[itemList.size()];
				
				for(int i = 0; i < itemList.size(); i++){
					name[i] = itemList.get(i).getName();
					price[i] = itemList.get(i).getPriceString();
					number[i] = "0";
				}
			}
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

