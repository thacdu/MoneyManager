package com.bill.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moneymanager.R;
import com.example.moneymanager.R.id;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.IntentAction;

public class GraphAChartEngineActivity extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        final ActionBar actionBar = (ActionBar) findViewById(id.actionbar);
		actionBar.setHomeAction(new IntentAction(this, ActionBarControl.createIntent(this), R.drawable.ic_title_home_default));
    }
    
    public void lineGraphHandler (View view)
    {
    	Intent intent = new Intent(getBaseContext(), LineGraph.class);
        startActivity(intent);
    }
    
    public void barGraphHandler (View view)
    {
    	Intent intent = new Intent(getBaseContext(), BarGraph.class);
        startActivity(intent);
    }
    
    public void pieGraphHandler (View view)
    {
    	Intent intent = new Intent(getBaseContext(), PieGraph.class);
        startActivity(intent);
    }
    
}
