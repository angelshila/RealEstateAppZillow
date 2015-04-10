package com.example.myfirstapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.TabHost.TabSpec;

public class ResultActivity extends TabActivity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
            
            Intent intent = getIntent();
            String text = intent.getStringExtra("DATA_JSON");
            try {
				JSONObject data = new JSONObject(text);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // create the TabHost that will contain the Tabs
            TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


            TabSpec tab1 = tabHost.newTabSpec("First Tab");
            TabSpec tab2 = tabHost.newTabSpec("Second Tab");

           // Set the Tab name and Activity
           // that will be opened when particular Tab will be selected
            tab1.setIndicator("BASIC INFO");
    	    Intent intentbasicinfo = new Intent(ResultActivity.this, Tab1Activity.class);
    	    intentbasicinfo.putExtra("RESULT", text); 
            tab1.setContent(intentbasicinfo);
            
            tab2.setIndicator("HISTORICAL ZESTIMATES");
            Intent intentcharts = new Intent(ResultActivity.this, Tab2Activity.class);
            intentcharts.putExtra("CHART", text); 
           
            tab2.setContent(intentcharts);

           
            
            /** Add the tabs  to the TabHost to display. */
            tabHost.addTab(tab1);
//            tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#000000"));
            tabHost.addTab(tab2);
//            tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#000000"));
//            TabHost tabhost = getTabHost();
//            for(int i=0;i<tabhost.getTabWidget().getChildCount();i++) 
//            {
//                TextView tv = (TextView) tabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
//                tv.setTextColor(Color.parseColor("#FFFFFF"));
//            } 


    }
} 
