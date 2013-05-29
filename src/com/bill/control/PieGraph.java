package com.bill.control;

import java.sql.SQLException;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.bill.data.DatabaseHelper;
import com.bill.data.Item;
import com.bill.data.ItemOnBill;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class PieGraph extends OrmLiteBaseActivity<DatabaseHelper>{
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		showChart(this);
		finish();
	}
	
	public void showChart(Context context) {

		List<Item> items;
		
		CategorySeries series = new CategorySeries("Pie Graph");
		DefaultRenderer renderer = new DefaultRenderer();
		
		try {
			
			Dao<Item, Integer> itemDao = getHelper().getItemDao();

			items = itemDao.queryForAll();
			double price[] = new double[items.size()];
			int i = 0;
			
			for(Item item : items){
				Dao<ItemOnBill, Integer> itemOnBillDao = getHelper().getItemOnBillDao();
				List<ItemOnBill> list = itemOnBillDao.queryBuilder().where().eq("item_id", item).query();
				price[i] = list.size() * item.getPrice();
				i++;
			}
			
			for (int k = 0; k < items.size(); k++) {
				series.add(items.get(k).getName(), k);
			}
			
			int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN, Color.BLACK, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.TRANSPARENT, Color.RED };
		
			for (int k = 0; k < items.size(); k++) {
				SimpleSeriesRenderer r = new SimpleSeriesRenderer();
				r.setColor(colors[k]);
				renderer.addSeriesRenderer(r);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		renderer.setChartTitle("Pie Chart Demo");
		renderer.setChartTitleTextSize(7);
		renderer.setZoomButtonsVisible(true);
		renderer.setLabelsColor(Color.BLACK);
		renderer.setLabelsTextSize(15);

		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");
		startActivity(intent);
	}
}
