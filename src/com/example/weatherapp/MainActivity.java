package com.example.weatherapp;

import com.example.myfirstapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnFindLocation;
	
	// Holds an instance of the GPSTracker class
	GPSTracker gps;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get an instance of the button
        btnFindLocation = (Button) findViewById(R.id.btnFindLocation);
        
        // Find location button onClick event
        btnFindLocation.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);
                
                // Check if GPS is enabled
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    
                    Toast.makeText(getApplicationContext(), "Latitude: " + latitude + " // Longitude: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to get location. :(", Toast.LENGTH_LONG).show();
                }
            }
        });
        
        GPSTracker gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
                
        return true;
    }
    
}
