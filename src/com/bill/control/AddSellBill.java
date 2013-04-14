package com.bill.control;
     
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class AddSellBill extends ListActivity {
	
	static final String[] NAME = 
            new String[] { "Android", "iOS", "WindowsMobile", "Blackberry"};
	static final String[] NUMBER = 
			new String[] {"1", "2", "3", "4"};
	static final String[] PRICE =
			new String[] {"1.23", "3.14", "5.76", "8.93"};
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
  
		// no more this
		// setContentView(R.layout.list_fruit);
		 
		setListAdapter(new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE));
		/*
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});*/
 
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
 
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
 
	}
	
	private void loadMenu(){
		
	}
 
}
