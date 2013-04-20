package com.bill.control;
     
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.example.moneymanager.R;
import com.example.moneymanager.R.id;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class AddSellBill extends OrmLiteBaseActivity<DatabaseHelper> implements SwipeInterface{
	
	private String[] NAME = 
            new String[] { "Bánh mỳ Bate", "Kem", "Cocacola", "Trà đá"};
	private String[] NUMBER = 
			new String[] {"0", "0", "0", "0"};
	private String[] PRICE =
			new String[] {"10000", "8000", "8000", "3000"};
	
	AddSellBillListViewAdapter adapter;
	ListView listview;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_sell_bill);
		
		/* Action bar */
		final ActionBar actionBar = (ActionBar) findViewById(id.actionbar);
		actionBar.setHomeAction(new IntentAction(this, ActionBarControl.createIntent(this), R.drawable.ic_title_home_demo));
		final Action saveAction = new SaveAction();
		actionBar.setTitle(R.string.list_sell_bill);
        actionBar.addAction(saveAction);
        
        /* List view */
		listview = (ListView) findViewById(id.listview);
		adapter = new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE);
		listview.setAdapter(adapter);
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
		ArrayList<Item> item = new ArrayList<Item>();
		
		try{
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			
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
					Item currentItem = new Item(nameString, priceString, numberString, bill);
					itemDao.create(currentItem);
					item.add(currentItem);
				}
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		onBackPressed();
		ViewSellBillActivity.setObject(bill, item);
		startActivity(new Intent(this, ViewSellBillActivity.class));
	}

	@Override
	public void bottom2top(View view, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void left2right(View view, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right2left(View view, int position) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(NUMBER[position]);
		NUMBER[position] = String.valueOf(n-1);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void top2bottom(View view, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touch(View view, int position) {
		// TODO Auto-generated method stub
		int n = Integer.parseInt(NUMBER[position]);
		NUMBER[position] = String.valueOf(n+1);
		adapter.notifyDataSetChanged();
	}
}

