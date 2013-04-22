package com.bill.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ItemOnBill {
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private int number;
	
	@DatabaseField(canBeNull = true, foreign = true)
	private Item item;
	
	@DatabaseField(canBeNull = true, foreign = true)
	private Bill bill;
	
	public ItemOnBill(){
		
	}
	
	public ItemOnBill(Item item, int number, Bill bill){
		this.item = item;
		this.number = number;
		this.bill = bill;
	}
	
	public ItemOnBill(Item item, String number, Bill bill){
		this(item, Integer.parseInt(number), bill);
	}
	
	public String getNumberString(){
		return String.valueOf(number);
	}
	
	public int getId(){
		return id;
	}
	
	public void increaseNumber(){
		number++;
	}
	
	public void decreaseNumber(){
		number--;
	}
	
	public int getPriceInBill(){
		return item.getPrice() * number;
	}
	
	public void setBill(Bill bill){
		this.bill = bill;
	}
	
	public Bill getBill(){
		return bill;
	}
	
	public Item getItem(){
		return item;
	}

}
