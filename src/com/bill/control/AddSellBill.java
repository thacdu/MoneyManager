package com.bill.control;
     
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.moneymanager.R;
import com.example.moneymanager.R.id;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.AbstractAction;
import com.markupartist.android.widget.ActionBar.Action;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class AddSellBill extends Activity{
	
	private String[] NAME = 
            new String[] { "Bánh mỳ Bate", "Kem", "Cocacola", "Trà đá","Bánh mỳ Bate", "Kem", "Cocacola", "Trà đá","Bánh mỳ Bate", "Kem", "Cocacola", "Trà đá"};
	private String[] NUMBER = 
			new String[] {"0", "0", "0", "0","0", "0", "0", "0","0", "0", "0", "0"};
	private String[] PRICE =
			new String[] {"1.23", "3.14", "5.76", "8.93","1.23", "3.14", "5.76", "8.93","1.23", "3.14", "5.76", "8.93"};
	
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
		final ListView listview = (ListView) findViewById(id.listview);
		listview.setAdapter(new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE));
		
		/* List view listener */
		listview.setOnItemClickListener(new OnItemClickListener()
        {
     	   public void onItemClick(AdapterView<?> parent, View view, int position, long id)
     	   {
     		   int n = Integer.parseInt(NUMBER[position]);
     		   NUMBER[position] = String.valueOf(n+1);
     		   mSetAdapter(listview);
     	   }
        });
	}
	
	/* Set list view adapter */
	protected void mSetAdapter(ListView listView) {
		listView.setAdapter(new AddSellBillListViewAdapter(this, NAME, NUMBER, PRICE));
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
		onBackPressed();
		
	}
}

