package com.bill.control;
     
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class AddSellBill extends ListActivity{
	
	private String[] NAME = 
            new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
	private String[] NUMBER = 
			new String[] {"1", "2", "3", "4"};
	private String[] PRICE =
			new String[] {"1.23", "3.14", "5.76", "8.93"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		int n = Integer.parseInt(NUMBER[position]);
		NUMBER[position] = String.valueOf(n+1);
		setListAdapter(new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE));
	}
}
