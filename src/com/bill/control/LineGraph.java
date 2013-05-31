package com.bill.control;

import java.sql.SQLException;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bill.data.Bill;
import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.bill.data.ItemOnBill;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class LineGraph extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		showChart(this);
		finish();
	}
	
	public void showChart(Context context) {
		
		List<Bill> bills;
		TimeSeries series = new TimeSeries("Doanh thu từng ngày");
		
		try {
			Dao<Bill, Integer> billDao = getHelper().getBillDao();
			bills = billDao.queryForAll();
			
			int n = bills.size();
			 
			int[] x = new int[n]; // x values!
			int[] y =  new int[n]; // y values!
			
			for(int i = 0; i < n; i++) x[i] = i+1;
			
			int k = 0;
			for(Bill bill : bills){
				y[k] = getTotalPrice(bill);
			}
			
			for( int i = 0; i < n; i++)
			{
				series.add(x[i], y[i]);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Draw graph
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		mRenderer.addSeriesRenderer(renderer);
		
		// Customization time for line 1!
		renderer.setColor(Color.RED);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line Graph Title");
		startActivity(intent);	
	}
	
	private int getTotalPrice(Bill bill){
		int price = 0;
		try{
			Dao<ItemOnBill, Integer> itemOnBillDao = getHelper().getItemOnBillDao();
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			List<ItemOnBill> list = itemOnBillDao.queryBuilder().where().eq("bill_id", bill).query();
			
			for(int i = 0; i < list.size(); i++){
				ItemOnBill item = list.get(i);
				int id = item.getItem().getId();
				Item temp = itemDao.queryForId(id);
				price += temp.getPrice() * item.getNumber();
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return price;
	}

}
