package com.bill.control;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.bill.data.ItemOnBill;
import com.example.moneymanager.R;
import com.example.moneymanager.R.id;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class ViewSellBillActivity extends OrmLiteBaseActivity<DatabaseHelper>{
	private static Bill bill;
	private static ArrayList<ItemOnBill> item;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_sell_bill);
		
		/* Action bar */
		final ActionBar actionBar = (ActionBar) findViewById(id.actionbar);
		actionBar.setHomeAction(new IntentAction(this, ActionBarControl.createIntent(this), R.drawable.ic_title_home_demo));
        
		int n = item.size();
		int billPrice = 0;
		String[] name = new String[n];
		String[] number = new String[n];
		String[] price = new String[n];
		
		for(int i = 0; i < n; i++){
			name[i] = item.get(i).getItem().getName();
			number[i] = item.get(i).getNumberString();
			price[i] = item.get(i).getItem().getPriceString();
			billPrice += item.get(i).getPriceInBill();
		}
		
		TextView textView = (TextView) findViewById(R.id.table);
		textView.setText("Bàn số " + bill.getTextTableNumber());
		TextView totalPrice = (TextView) findViewById(R.id.total_price);
		totalPrice.setText("Tổng số tiền: " + billPrice);
		
        /* List view */
		ListView listview = (ListView) findViewById(id.listview);
		AddSellBillListViewAdapter adapter = new AddSellBillListViewAdapter(this, name, number, price);
		listview.setAdapter(adapter);
	}
	
	public static void setObject(Bill newBill, ArrayList<ItemOnBill> newItem){
		bill = newBill;
		item = newItem;
	}
}
