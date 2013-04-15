package com.bill.data;

public class Bill {
	int tableNumber;
	String date;
	
	public Bill(int tableNumber, String date){
		this.tableNumber = tableNumber;
		this.date = date;
	}
	
	public void addItem(Item item){
		item.setBill(this);
	}
	
	public void removeItem(Item item){
		
	}
}
