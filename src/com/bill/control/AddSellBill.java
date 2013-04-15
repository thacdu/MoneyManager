package com.bill.control;
     
import java.sql.SQLException;
import java.util.Date;

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
import com.example.moneymanager.R;
import com.example.moneymanager.R.id;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class AddSellBill extends OrmLiteBaseActivity<DatabaseHelper>{
	
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
        actionBar.addAction(saveAction);
        
        /* List view */
		listview = (ListView) findViewById(id.listview);
		adapter = new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE);
		listview.setAdapter(adapter);
		
		/* List view listener */
		listview.setOnItemClickListener(new OnItemClickListener()
        {
     	   public void onItemClick(AdapterView<?> parent, View view, int position, long id)
     	   {
     		   int n = Integer.parseInt(NUMBER[position]);
     		   NUMBER[position] = String.valueOf(n+1);
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
		try{
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			
			EditText tableNumber = (EditText) findViewById(id.tableNumber);

			Bill bill = new Bill(tableNumber.getText().toString().toString(), new Date(System.currentTimeMillis()));
			billDao.create(bill);
			
			for(int i = 0; i < n; i++){
				View currentView = listview.getChildAt(i);
				TextView number = (TextView) currentView.findViewById(id.number);
				if(number.getText() != "0"){
					TextView price = (TextView) currentView.findViewById(id.price);
					TextView name = (TextView) currentView.findViewById(id.name);
					String nameString = name.getText().toString().toString();
					String priceString = price.getText().toString().toString();
					String numberString = number.getText().toString().toString();
					numberString = numberString.substring(11, numberString.length());
					Item item = new Item(nameString, priceString, numberString, bill);
					itemDao.create(item);
				}
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		onBackPressed();
		startActivity(new Intent(this, ViewSellBillActivity.class));
	}
}

