package com.bill.control;

import java.sql.SQLException;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.bill.data.ItemOnBill;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class BarGraph extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		showChart(this);
		finish();
	}
	
	public void showChart(Context context) 
	{	
		// Bar 1
		//int[] y = { 124, 135, 443, 456, 234, 123, 342, 134, 123, 643, 234, 274 };
		CategorySeries series = new CategorySeries("Doanh thu từng món");
		
		try {
			Dao<Item, Integer> itemDao = getHelper().getItemDao();
			List<Item> items = itemDao.queryForAll();
			double price[] = new double[items.size()];
			int i = 0;
			
			for(Item item : items){
				Dao<ItemOnBill, Integer> itemOnBillDao = getHelper().getItemOnBillDao();
				List<ItemOnBill> list = itemOnBillDao.queryBuilder().where().eq("item_id", item).query();
				price[i] = list.size() * item.getPrice();
				i++;
			}
			
			for (int k = 0; k < price.length; k++) {
				series.add("Bar " + (k+1), price[k]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());

		// This is how the "Graph" itself will look like
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setChartTitle("Doanh thu từng món");
		mRenderer.setXTitle("X VALUES");
		mRenderer.setYTitle("Y VALUES");
		mRenderer.setAxesColor(Color.GREEN);
	    mRenderer.setLabelsColor(Color.RED);
	    // Customize bar 1
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.BLUE);
	    renderer.setDisplayChartValues(true);
	    renderer.setChartValuesSpacing((float) 0.5);
	    mRenderer.addSeriesRenderer(renderer);
	    
		Intent intent = ChartFactory.getBarChartIntent(context, dataset,mRenderer, Type.DEFAULT);
		startActivity(intent);
	}

}
