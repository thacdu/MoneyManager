package com.bill.data;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Bill {
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private int tableNumber;
	
	@DatabaseField(dataType = DataType.DATE_STRING)
	private Date date;
	
	public Bill(){
		
	}
	
	public Bill(int tableNumber, Date date){
		this.tableNumber = tableNumber;
		this.date = date;
	}
	
	public Bill(String tableNumber, Date date){
		this.tableNumber = Integer.parseInt(tableNumber);
		this.date = date;
	}
	
	public String getTextTableNumber(){
		return String.valueOf(tableNumber);
	}
	
	public String getDate(){
		return date.toLocaleString();
	}
	
	public int getID(){
		return id;
	}
}
