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
	private double price;
	
	@DatabaseField
	private int number;
	
	@DatabaseField(canBeNull = true, foreign = true)
	Bill bill;
	
	public Item(){
		
	}
	
	public Item(String name, double price, int number, Bill bill){
		this.name = name;
		this.price = price;
		this.number = number;
		this.bill = bill;
	}
	
	public Item(String name, String price, String number, Bill bill){
		this.name = name;
		this.price = Double.parseDouble(price);
		this.number = Integer.parseInt(number);
		this.bill = bill;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void increaseNumber(){
		number++;
	}
	
	public void decreaseNumber(){
		number--;
	}
	
	public double getPriceInBill(){
		return price * number;
	}
	
	public void setBill(Bill bill){
		this.bill = bill;
	}
}
