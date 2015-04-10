package com.example.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.*;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.CursorJoiner.Result;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;



public class MainActivity extends ActionBarActivity {
	
	 private class construct_call extends AsyncTask<String,String, String> {
	    	protected String doInBackground(String... params) {

	    	String output = null;

	    	output = getOutputFromUrl(params[0]);

	    	

	    	return output;

	    }

	    	

	    	private String getOutputFromUrl(String url) {
	    		
	    		String output = null;

	    	try {

	    			DefaultHttpClient httpClient = new DefaultHttpClient();

	    			HttpGet httpGet = new HttpGet(url);



	    	HttpResponse httpResponse = httpClient.execute(httpGet);

	    	HttpEntity httpEntity = httpResponse.getEntity();

	        output = EntityUtils.toString(httpEntity);

	    	} catch (UnsupportedEncodingException e) {

	        e.printStackTrace();

	    	} catch (ClientProtocolException e) {

	    	e.printStackTrace();

	    	} catch (IOException e) {

	    	e.printStackTrace();

	    	}

	    	return output;

	 }



	    @Override

	    protected void onPostExecute(String result) {

	    super.onPostExecute(result);

	    try{

	    Intent intent = new Intent(MainActivity.this, ResultActivity.class);

	    intent.putExtra("DATA_JSON", result); 
	    System.out.println(result);
		JSONObject jsonObj = new JSONObject(result);
		System.out.println("Done");
		
		JSONObject res = jsonObj.getJSONObject("result");
		String msgcode = res.getString("code");
		
		System.out.println(msgcode);
		
		if(msgcode.equals("0")){
			EditText editText = (EditText)findViewById(R.id.notfound);
    		editText.setText("", TextView.BufferType.EDITABLE);
		    startActivity(intent);

		}
		else{
			
			EditText editText = (EditText)findViewById(R.id.notfound);
    		editText.setText("No exact match found-- Verify that the given address is correct.", TextView.BufferType.EDITABLE);

		}

	   }

	   catch(Exception e){

	   e.printStackTrace();
	   }
	}


	}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//        PackageInfo info = getPackageManager().getPackageInfo("com.example.myfirstapp", 
//        	PackageManager.GET_SIGNATURES);
//        	for (Signature signature : info.signatures) {
//        	MessageDigest md = MessageDigest.getInstance("SHA");
//        	md.update(signature.toByteArray());
//        	String x = Base64.encodeToString(md.digest(), Base64.DEFAULT);
//        	Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//        	}
//        	} catch (NameNotFoundException e) {
//        	
//        	} catch (NoSuchAlgorithmException e) {
//        	
//        	}

    	ImageView zillow = (ImageView) findViewById(R.id.zillowlogo);
    	zillow.setOnClickListener(new View.OnClickListener() {

    	@Override
    	public void onClick(View v) {
    	// TODO Auto-generated method stub
    	Uri uri = Uri.parse( "http://www.zillow.com/" );
    	startActivity( new Intent( Intent.ACTION_VIEW, uri));
    	}
    	});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /** Called when the user touches the button */
    public void sendMessage(View view) {
        // Do something in response to button click
    	EditText addressEditText = (EditText) findViewById(R.id.edit_message);
    	String saddress = addressEditText.getText().toString();
    	EditText cityEditText = (EditText) findViewById(R.id.edit_message1);
    	String city = cityEditText.getText().toString();
    	Spinner statespinner = (Spinner)findViewById(R.id.states_array);
    	String state = statespinner.getSelectedItem().toString();
    	if (saddress.matches("")) {
//    	    Toast.makeText(this, "This field is required ", Toast.LENGTH_SHORT).show();
//    	    return;
    		
    		EditText editText = (EditText)findViewById(R.id.emptycontent);
    		editText.setText("This field is required.", TextView.BufferType.EDITABLE);
    		
    	}
    	else
    	{
    		EditText editText2 = (EditText)findViewById(R.id.emptycontent);
    		editText2.setText("", TextView.BufferType.EDITABLE);
    	}
    	
    	if (city.matches("")) {
//    	    Toast.makeText(this, "This field is required ", Toast.LENGTH_SHORT).show();
//    	    return;
    		
    		EditText editText3 = (EditText)findViewById(R.id.emptycontent1);
    		editText3.setText("This field is required.", TextView.BufferType.EDITABLE);
    		
    	}
    	else
    	{
    		EditText editText4 = (EditText)findViewById(R.id.emptycontent1);
    		editText4.setText("", TextView.BufferType.EDITABLE);
    	}
    	
    	if (state.matches("Choose State")) {
//    	    Toast.makeText(this, "This field is required ", Toast.LENGTH_SHORT).show();
//    	    return;
    		
    		EditText editText5 = (EditText)findViewById(R.id.emptycontent2);
    		editText5.setText("This field is required.", TextView.BufferType.EDITABLE);
    		
    	}
    	else
    	{
    		EditText editText6 = (EditText)findViewById(R.id.emptycontent2);
    		editText6.setText("", TextView.BufferType.EDITABLE);
    	}
    	
    
    	
    	
    	String url="";
    	if(!saddress.equals("") && !city.equals("") && !state.equals(""))
    	{
    		url = Uri.parse("http://anushiladeyhw8-env.elasticbeanstalk.com/").buildUpon()
    			    .appendQueryParameter("SA", saddress)
    			    .appendQueryParameter("CityInput",city)
    			    .appendQueryParameter("state",state)
    			    .build().toString();
    		System.out.println(url);

        	System.out.println("The URL to be sent to servlet is " + url);
        	System.out.println("Calling execute function of AsyncTaskRunner....");
        	new construct_call().execute(url);
        	System.out.println("This statement is also executed");
    	}
    	
    	
    	
    	
    }
    
   

    	
}
    
    
    
    
    




