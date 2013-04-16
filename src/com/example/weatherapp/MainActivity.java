package com.example.weatherapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.b3weather.R;

public class MainActivity extends Activity {	
	// Holds an instance of the GPSTracker class
	GPSTracker gps;
	
	// Holds an instance of the Darksky API
	Darksky darksky;
	
	// Holds weather data for the next 7 days
	ArrayList<Forecast> weeklyForecast;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        weeklyForecast = new ArrayList<Forecast>();
        
        // Instantiate GPS object
        gps = new GPSTracker(MainActivity.this);
        
        // Check if GPS is enabled
        if (gps.canGetLocation()) {            
            Toast.makeText(getApplicationContext(), "Latitude: " + gps.getLatitude() + " // Longitude: " + gps.getLongitude(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unable to get location. :(", Toast.LENGTH_LONG).show();
        }
        
        // Set up API requester with location data
        darksky = new Darksky(gps.getLatitude(), gps.getLongitude());
        
        darksky.getWeeklyForecast();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
                
        return true;
    }
    
}
