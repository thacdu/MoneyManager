package com.example.moneymanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context context;

	public ImageAdapter(Context context) {
		this.context = context;
	}
	
	public int getCount() {
		return thumbIds.length;
	}
	
	public Object getItem(int position) {
		return position;
	}
	
	public long getItemId(int position) {
		return position;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view;
		ImageButton imageButton;
		if (convertView == null) { // if it’s not recycled, initialize some attributes
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			view = layoutInflater.inflate(R.layout.grid_item, null);
			
			TextView textView = (TextView)view.findViewById(R.id.icon_text);
			textView.setText(textsIds[position]);
			
			imageButton = (ImageButton)view.findViewById(R.id.icon_image);
			imageButton.setPadding(8, 8, 8, 8);
			imageButton.setImageResource(thumbIds[position]);
			imageButton.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(context, classlist[position]).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
			
		} else {
			view = (View) convertView;
		}
		
		return view;
	}

	// references to our images
	private Integer[] thumbIds = {
			R.drawable.plus, R.drawable.expand,
			R.drawable.plus, R.drawable.expand,
			R.drawable.plus, R.drawable.expand
	};
	
	// references to our texts
	private String[] textsIds = {
			"plus", "expand",
			"plus", "expand",
			"plus", "expand"
	};
	
	private Class[] classlist = {
			ListFruitActivity.class, ListFruitActivity.class,
			ListFruitActivity.class, ListFruitActivity.class,
			ListFruitActivity.class, ListFruitActivity.class
	};
}