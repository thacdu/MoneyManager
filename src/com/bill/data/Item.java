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
	
	@DatabaseField
	private int number;
	
	@DatabaseField(canBeNull = true, foreign = true)
	Bill bill;
	
	public Item(){
		
	}
	
	public Item(String name, int price, int number, Bill bill){
		this.name = name;
		this.price = price;
		this.number = number;
		this.bill = bill;
	}
	
	public Item(String name, String price, String number, Bill bill){
		this.name = name;
		this.price = Integer.parseInt(price);
		this.number = Integer.parseInt(number);
		this.bill = bill;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPriceString(){
		return String.valueOf(price);
	}
	
	public String getNumberString(){
		return String.valueOf(number);
	}
	
	public void increaseNumber(){
		number++;
	}
	
	public void decreaseNumber(){
		number--;
	}
	
	public int getPriceInBill(){
		return price * number;
	}
	
	public void setBill(Bill bill){
		this.bill = bill;
	}
}
