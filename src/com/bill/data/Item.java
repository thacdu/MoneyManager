package com.bill.data;

public class Item {
	String name;
	double price;
	int number;
	Bill bill;
	
	public Item(String name, double price, int number, Bill bill){
		this.name = name;
		this.price = price;
		this.number = number;
		this.bill = bill;
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
