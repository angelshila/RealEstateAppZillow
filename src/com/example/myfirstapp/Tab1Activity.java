package com.example.myfirstapp;

import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;



public class Tab1Activity extends Activity
{
	private UiLifecycleHelper uiHelper;
	String linkText="";
	String header="";
	String url1="";
	String fblink="";
	private Session.StatusCallback callback = new Session.StatusCallback() {


		@Override
		public void call(Session session, SessionState state,
		Exception exception) {
	
		}
};
    private void openFacebookSession(){
        
        Session.openActiveSession(Tab1Activity.this, true, Arrays.asList("email", "user_birthday", "user_hometown", "user_location"), new Session.StatusCallback()
        {

        @Override
        public void call(Session session, SessionState state,
        Exception exception) {
        FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(Tab1Activity.this)
        .setLink("https://developers.facebook.com/android")
        .setCaption("Property Information from Zillow.com")
        .setPicture(url1)
        .setLink(fblink)
        .setName(header)
        .build();
      uiHelper.trackPendingDialogCall(shareDialog.present());
   
}
});
 }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    Session.getActiveSession()
   .onActivityResult(this, requestCode, resultCode, data);
    uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
   @Override
   public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data){
	   
   }
   @Override
   public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {

//	   String postId = FacebookDialog.getNativeDialogPostId(data);
	   Toast.makeText(getApplicationContext(), "Posted Story Successfully", Toast.LENGTH_LONG).show();
   }
});
    }
    
	
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
        	uiHelper = new UiLifecycleHelper(this, callback);
        	uiHelper.onCreate(savedInstanceState);
        	super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tab1);
            
            Intent intent = getIntent();
            String text = intent.getStringExtra("RESULT");

            try {
				JSONObject totaldetails = new JSONObject(text);
				System.out.println("SAUMYAAAAAAA I LOVE YOUUUUUU");
				JSONObject data = new JSONObject(text);
            	JSONObject resultsData = data.getJSONObject("chart");
            	JSONObject url1Obj = resultsData.getJSONObject("1year");
			    url1 = url1Obj.getString("url");


	            
	            JSONObject res = totaldetails.getJSONObject("result");
	        	
	            String proptyp = res.getString("useCode");
	    		System.out.println(proptyp);
	    		if(proptyp.equals("")){
	    		TextView tv = (TextView)findViewById(R.id.proptyp1);
	    		tv.setText("N/A", TextView.BufferType.EDITABLE);
	    		}
	    		else{
	    			TextView tv = (TextView)findViewById(R.id.proptyp1);
		    		tv.setText(proptyp, TextView.BufferType.EDITABLE);
	    		}
	    		
	        	
	    		String yrbt = res.getString("yearBuilt");
	    		System.out.println(yrbt);
	    		if(yrbt.equals("")){
	    			TextView tv1 = (TextView)findViewById(R.id.yrbt1);
		    		tv1.setText("N/A", TextView.BufferType.EDITABLE);	
	    		}
	    		else{
	    			TextView tv1 = (TextView)findViewById(R.id.yrbt1);
		    		tv1.setText(yrbt, TextView.BufferType.EDITABLE);	
	    			
	    		}
	    		
	    		
	        	String ls = res.getString("lotSizeSqFt");
	        	if(ls.equals("0")){
	        		TextView tv2 = (TextView)findViewById(R.id.ls1);
		    		tv2.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	
	    		else{
	    			TextView tv2 = (TextView)findViewById(R.id.ls1);
		    		tv2.setText(ls+" sq. ft.", TextView.BufferType.EDITABLE);
	    		}
	    		String fa = res.getString("finishedSqFt");
	    		if(fa.equals("0")){
	    		TextView tv3 = (TextView)findViewById(R.id.fa1);
	    		tv3.setText("N/A", TextView.BufferType.EDITABLE);
	    		}
	    		else{
	    			TextView tv3 = (TextView)findViewById(R.id.fa1);
		    		tv3.setText(fa+" sq. ft.", TextView.BufferType.EDITABLE);
	    		}

	        	String ba = res.getString("bathrooms");
	        	if(ba.equals("")){
	        		TextView tv4 = (TextView)findViewById(R.id.ba1);
		    		tv4.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else{
	        		TextView tv4 = (TextView)findViewById(R.id.ba1);
		    		tv4.setText(ba, TextView.BufferType.EDITABLE);
	        	}
	        	
	        	String be = res.getString("bedrooms");
	        	if(be.equals("")){
	        		TextView tv5 = (TextView)findViewById(R.id.be1);
		    		tv5.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else{
	        		TextView tv5 = (TextView)findViewById(R.id.be1);
		    		tv5.setText(be, TextView.BufferType.EDITABLE);
	        	}
	        	
	        	String taxyr = res.getString("taxAssessmentYear");
	        	if(taxyr.equals("")){
	        		TextView tv6 = (TextView)findViewById(R.id.taxyr1);
		    		tv6.setText("N/A", TextView.BufferType.EDITABLE);
	        	
	        	}
	        	else{
	        		TextView tv6 = (TextView)findViewById(R.id.taxyr1);
		    		tv6.setText(taxyr, TextView.BufferType.EDITABLE);
	        	}
	    		
	        	String taxass = res.getString("taxAssessment");
	        	if(taxass.equals("0.00")){
	        		TextView tv7 = (TextView)findViewById(R.id.taxass1);
		    		tv7.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else{
	        		TextView tv7 = (TextView)findViewById(R.id.taxass1);
		    		tv7.setText("$"+taxass, TextView.BufferType.EDITABLE);
	        	}
	        	

	        	String lastsoldpr = res.getString("lastSoldPrice");
	        	if(lastsoldpr.equals("0.00") || lastsoldpr.equals("")){
	        		TextView tv8 = (TextView)findViewById(R.id.lastsoldpr1);
		    		tv8.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else{
	        		TextView tv8 = (TextView)findViewById(R.id.lastsoldpr1);
		    		tv8.setText("$"+lastsoldpr, TextView.BufferType.EDITABLE);
	        	}
	        	

	        	String lastsolddt = res.getString("lastSoldDate");
	        	if(lastsolddt.equals("") || lastsolddt.equals("01-Jan-1970")){
	        		TextView tv9 = (TextView)findViewById(R.id.lastsolddt1);
		    		tv9.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else{
	        		TextView tv9 = (TextView)findViewById(R.id.lastsolddt1);
		    		tv9.setText(lastsolddt, TextView.BufferType.EDITABLE);
	        	}
	        	
	    		
	        	String zestpropval = res.getString("estimateAmount");
	        	if(zestpropval.equals("0.00")){
	        		TextView tv10 = (TextView)findViewById(R.id.zestpropval1);
		    		tv10.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else{
	        		TextView tv10 = (TextView)findViewById(R.id.zestpropval1);
		    		tv10.setText("$"+zestpropval, TextView.BufferType.EDITABLE);
	        	}
	        	
	        	String zestpropdate= res.getString("estimateLastUpdate");
	        	TextView tv11 = (TextView)findViewById(R.id.zestprop);
	    		tv11.setText("Zestimate \u00AE Property Estimate \nas of "+zestpropdate+":", TextView.BufferType.EDITABLE);
	    		
	        	String thirtydaysoverallchange= res.getString("estimateValueChange");
	        	TextView tv12 = (TextView)findViewById(R.id.thirtydaysoverallchange1);
	    		tv12.setText("$"+thirtydaysoverallchange, TextView.BufferType.EDITABLE);
	    		
	    		String estimateValueChangeSign = res.getString("estimateValueChangeSign");
	    		if(estimateValueChangeSign.equals("-")){
	    			ImageView oc1= (ImageView)findViewById(R.id.oc1);
	    			oc1.setImageResource(R.drawable.down_r);
	    			
	    		}
	    		else if(estimateValueChangeSign.equals("+")){
	    			ImageView oc1= (ImageView)findViewById(R.id.oc1);
		    		oc1.setImageResource(R.drawable.up_g);
		    		
	    		}

	    		
	        	String alltimeproplow = res.getString("estimateValuationRangeLow");
	        	String alltimeprophigh = res.getString("estimateValuationRangeHigh");
	        	if(alltimeproplow.equals("") && alltimeprophigh.equals("")){
	        		TextView tv13 = (TextView)findViewById(R.id.alltimeproplowhigh);
		    		tv13.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	
	    		else{
	    			TextView tv13 = (TextView)findViewById(R.id.alltimeproplowhigh);
		    		tv13.setText("$"+alltimeproplow+" - $"+alltimeprophigh, TextView.BufferType.EDITABLE);
	    		}
	        
	    		String restpropval= res.getString("restimateAmount");
	        	TextView tv14 = (TextView)findViewById(R.id.restpropval1);
	    		tv14.setText("$"+restpropval, TextView.BufferType.EDITABLE);
	    		
	        	String restpropdate= res.getString("restimateLastUpdate");
	        	TextView tv15 = (TextView)findViewById(R.id.restprop);
	    		tv15.setText("Rent Zestimate \u00AE Rent Valuation \nas of "+restpropdate, TextView.BufferType.EDITABLE);
	    		
	        	String thirtydaysrentchange =res.getString("restimateValueChange");
	        	TextView tv16 = (TextView)findViewById(R.id.thirtydaysrentchange1);
	    		tv16.setText("$"+thirtydaysrentchange, TextView.BufferType.EDITABLE);
	    		
	    		String restimateValueChangeSign = res.getString("restimateValueChangeSign");
	    		if(restimateValueChangeSign.equals("-")){
	    			ImageView rent_pic= (ImageView)findViewById(R.id.rent_pic);
	    			rent_pic.setImageResource(R.drawable.down_r);
//	    			rent_pic.getLayoutParams().height = 30;
//		    		rent_pic.getLayoutParams().width = 30;
	    		}
	    		else if(restimateValueChangeSign.equals("+")){
	    			ImageView rent_pic= (ImageView)findViewById(R.id.rent_pic);
		    		rent_pic.setImageResource(R.drawable.up_g);
//		    		rent_pic.getLayoutParams().height = 30;
//		    		rent_pic.getLayoutParams().width = 30;

	    		}
	    		
	        	String alltimerentlow = res.getString("restimateValuationRangeLow");
	        	String alltimerenthigh= res.getString("restimateValuationRangeHigh");
	        	if(alltimerentlow.equals("") && alltimerenthigh.equals(""))
	        	{
	        		TextView tv17 = (TextView)findViewById(R.id.alltimerentlowhigh);
		    		tv17.setText("N/A", TextView.BufferType.EDITABLE);
	        	}
	        	else
	        	{
	        		TextView tv17 = (TextView)findViewById(R.id.alltimerentlowhigh);
		    		tv17.setText("$"+alltimerentlow+" - $"+alltimerenthigh, TextView.BufferType.EDITABLE);
	        	}
	    		
	        
	    		String homedetails = res.getString("homedetails");
	    		String street = res.getString("street");
	    		String city = res.getString("city");
	    		String state = res.getString("state");
	    		String zip = res.getString("zipcode");

	            header=street+", "+city+", "+state+"-"+zip;
	            fblink=homedetails;
	    		TextView link = (TextView) findViewById(R.id.row2);
	    		linkText = "<a href='"+homedetails+"'>"+header+"</a>";
	    		link.setText(Html.fromHtml(linkText));
	    		link.setClickable(true);
	    		link.setMovementMethod (LinkMovementMethod.getInstance());
	    		TextView link1 = (TextView) findViewById(R.id.zillow_tnc2);
	    		String linkText1 = "Use is subject to <a href=http://www.zillow.com/wikipages/Privacy-and-Terms-of-Use/>Terms of Use</a>";
	    		link1.setText(Html.fromHtml(linkText1));
	    		link1.setClickable(true);
	    		link1.setMovementMethod (LinkMovementMethod.getInstance());
	    		TextView link2 = (TextView) findViewById(R.id.zillow_tnc3);
	    		String linkText2 = "<a href=http://www.zillow.com/zestimate/>What's a Zestimate?</a>";
	    		link2.setText(Html.fromHtml(linkText2));
	    		link2.setClickable(true);
	    		link2.setMovementMethod (LinkMovementMethod.getInstance());

	        	ImageButton imgb = (ImageButton)findViewById(R.id.fbbutton);
	        	imgb.setOnClickListener(new View.OnClickListener() {
	        	@Override
	        	public void onClick(View view) {
	        		AlertDialog.Builder builder = new AlertDialog.Builder(Tab1Activity.this);
	        		builder.setPositiveButton(R.string.displaypropertydetails, new DialogInterface.OnClickListener() {
	        		@Override
	        		public void onClick(DialogInterface dialog, int id) {
	        		openFacebookSession();
	        		}
	        		});
	        		builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
	        		@Override
	        		public void onClick(DialogInterface dialog, int id) {
	        		Toast.makeText(getApplicationContext(), "Post Cancelled", Toast.LENGTH_LONG).show();
	        		}
	        		});

	        		AlertDialog dialog = builder.create();
	        		dialog.setTitle("Post to Facebook");
	        		
	        		dialog.show();
	        		}
	        	});
	    		
	    		

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
    	
        }
        
 
        
      

@Override
protected void onResume() {
super.onResume();
uiHelper.onResume();
}

	@Override
	protected void onPause() {
   super.onPause();
  uiHelper.onPause();
	}


@Override
 protected void onDestroy() {
 super.onDestroy();
 uiHelper.onDestroy();
}

	
        
        
}