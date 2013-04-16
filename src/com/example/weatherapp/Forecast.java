package com.example.weatherapp;

public class Forecast {
    
    public int time;
    public String summary;
    public String icon;
    public double temperatureMin;
    public double tempreatureMax;
    public double windSpeed;
    public double humidity;
    
    public Forecast(int time, String summary, String icon, double temperatureMin, double temperatureMax, double windSpeed, double humidity) {
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.temperatureMin = temperatureMin;
        this.tempreatureMax = temperatureMax;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
    }
    
}
