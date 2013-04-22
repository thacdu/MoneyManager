package com.bill.control.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moneymanager.R;

public class ItemViewListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] name;
	private final String[] price;
 
	public ItemViewListAdapter(Context context, String[] name, String[] price) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.list_item ,name);
		this.context = context;
		this.name = name;
		this.price = price;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.list_item, parent, false);
		TextView nameView = (TextView) rowView.findViewById(R.id.name);
		TextView priceView = (TextView) rowView.findViewById(R.id.price);
		nameView.setText(name[position]);
		priceView.setText(price[position]);
		
		return rowView;
	}
}
