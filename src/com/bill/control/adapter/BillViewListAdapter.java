package com.bill.control.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moneymanager.R;

public class BillViewListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] date;
	private final String[] table;
	private final String[] price;
 
	public BillViewListAdapter(Context context, String[] date, String[] table, String[] price) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.list_all_bill, date);
		this.context = context;
		this.date = date;
		this.table = table;
		this.price = price;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.list_all_bill, parent, false);
		TextView dateView = (TextView) rowView.findViewById(R.id.date);
		TextView tableView = (TextView) rowView.findViewById(R.id.table_number);
		TextView priceView = (TextView) rowView.findViewById(R.id.price);
		dateView.setText(date[position]);
		tableView.setText(table[position]);
		priceView.setText(price[position]);
		
		return rowView;
	}
}