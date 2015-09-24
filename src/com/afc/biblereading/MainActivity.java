package com.afc.biblereading;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.joda.time.DateTime;

import com.afc.biblereading.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.graphics.Typeface;
import android.widget.TextView;


public class MainActivity extends Activity {
	public static final String PREFS_NAME = "MyPrefsFile";
	public static DateTime startDate;
	public static DateTime endDate;
	public static PendingIntent pendingIntent;
	public static AlarmManager alarmManager;
	LocalDataManage DOP;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        DOP = ((ApplicationSingleton)getApplication()).getDataBase();
        
		ArrayList<HashMap<String, Object>> result = DOP.getPlanInfo(DOP);
		Log.d("database plan return", String.valueOf(result.size()));
		Log.d("database plan return", result.toString());
		
//        if (value.equals("Yes")) {
        if (result.size()>0) {
        	Intent intent = new Intent(this, CalenderActivity.class);
        	
        	startActivity(intent);
        }
        else {
        	setContentView(R.layout.activity_main);
            
            Typeface face0 = Typeface.createFromAsset(getAssets(),"fonts/fonts1.TTF");
            TextView PickDateTitle = (TextView) findViewById(R.id.PickDateTitle);
            PickDateTitle.setTypeface(face0);
            
            Typeface face1 = Typeface.createFromAsset(getAssets(),"fonts/fonts2.TTF");
            TextView StartDateText = (TextView) findViewById(R.id.StartDateText);
            StartDateText.setTypeface(face1);
            TextView EndDateText = (TextView) findViewById(R.id.EndDateText);
            EndDateText.setTypeface(face1);
        }
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
    
    public void startReading(View view){
    	Intent intent = new Intent(this, Tabs.class);

		// TODO: add space for plan name, default 'reading plan'+id, cant be empty"
		String planName = "reading plan 1";
    	DatePicker StartDateValue = (DatePicker) findViewById(R.id.StartDateValue);
        DatePicker EndDateValue = (DatePicker) findViewById(R.id.EndDateValue);        

        startDate = new DateTime(StartDateValue.getYear(), StartDateValue.getMonth()+1, StartDateValue.getDayOfMonth(),0,0,0,0);
        endDate = new DateTime(EndDateValue.getYear(), EndDateValue.getMonth()+1, EndDateValue.getDayOfMonth(),0,0,0,0);
        
        CreateReadingPlan.CreatePlan(DOP, planName, startDate, endDate, this);
        
        
        
        
        Calendar calendar = Calendar.getInstance();
    
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	
		alarmManager = (AlarmManager) getApplicationContext().getSystemService(getBaseContext().ALARM_SERVICE);
		
		int id = (int) System.currentTimeMillis();
	
	 	Intent intent0 = new Intent(this, TimeAlarm.class);	 
	
	 	pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), id, intent0, PendingIntent.FLAG_UPDATE_CURRENT);

	 	Log.v("first","notify");

	 	alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 24*60*60*1000, pendingIntent);
        
        
        
        
    	startActivity(intent);
    }
    
}
