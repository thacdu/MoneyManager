package com.bill.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Item {
	 
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField
	private String name;
	
	@DatabaseField
	private int price;
	
	public Item(){
		
	}
	
	public Item(String name, int price){
		this.name = name;
		this.price = price;
	}
	
	public Item(String name, String price){
		this.name = name;
		this.price = Integer.parseInt(price);
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getPriceString(){
		return String.valueOf(price);
	}
	
}
