package com.bill.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moneymanager.R;

public class AddSellBillListViewAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] name;
	private final String[] number;
	private final String[] price;
 
	public AddSellBillListViewAdapter(Context context, String[] name, String[] number, String[] price) {
		super(context, R.layout.add_sell_bill, name);
		this.context = context;
		this.name = name;
		this.number = number;
		this.price = price;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.add_sell_bill, parent, false);
		TextView nameView = (TextView) rowView.findViewById(R.id.name);
		TextView numberView = (TextView) rowView.findViewById(R.id.number);
		TextView priceView = (TextView) rowView.findViewById(R.id.price);
		nameView.setText(name[position]);
		numberView.setText(number[position]);
		priceView.setText(price[position]);

		return rowView;
	}
}
