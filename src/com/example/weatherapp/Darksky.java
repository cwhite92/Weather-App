package com.example.weatherapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Darksky {
    
    // https://api.forecast.io/forecast/c009c5573e8ced3863c90a4113d03288/28.854167,128.041667?units=uk&exclude=hourly%2Calerts%2Cflags
    
    public static final String BASE_URL = "https://api.forecast.io/forecast/";
    public static final String API_KEY = "c009c5573e8ced3863c90a4113d03288";
    
    // Location data
    protected double latitude;
    protected double longitude;
    
    // Holds weather information for the entire week
    ArrayList<Forecast> weeklyForecast = new ArrayList<Forecast>();

    private AsyncHttpClient client;
    
    public Darksky(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        client = new AsyncHttpClient();
    }

    public ArrayList<Forecast> getWeeklyForecast() {
        // Clear before inserting new data
        weeklyForecast.clear();
        
        client.get(BASE_URL + API_KEY + "/" + latitude + "," + longitude + "?units=uk&exclude=hourly%2Calerts%2Cflags", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject root = new JSONObject(response);
                    JSONObject daily = root.getJSONObject("daily");
                    JSONArray forecasts = daily.getJSONArray("data");
                    
                    // Loop through every day
                    for (int i = 0; i < forecasts.length(); i++) {
                        JSONObject dayObject = forecasts.getJSONObject(i);
                        
                        weeklyForecast.add(new Forecast(
                            dayObject.getInt("time"),
                            dayObject.getString("summary"),
                            dayObject.getString("icon"),
                            dayObject.getDouble("temperatureMin"),
                            dayObject.getDouble("temperatureMax"),
                            dayObject.getDouble("windSpeed"),
                            dayObject.getDouble("humidity")
                        ));
                    }
                } catch(Exception e) {
                    // TODO: handle
                }
                
                System.out.println(weeklyForecast.toString());
            }
        });
        
        return weeklyForecast;
    }

}