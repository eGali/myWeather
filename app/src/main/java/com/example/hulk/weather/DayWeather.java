package com.example.hulk.weather;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hulk on 6/4/15.
 */
public class DayWeather {

    private String id, city, main, day, country, icon, dt, description;

    public DayWeather(String dt, String city, String main, String country, String icon, String day, String description){
        this.dt = dt;
        this.city = city;
        this.main = main;
        this.day = day;
        this.country = country;
        this.icon = icon;
    }

    public static ArrayList<DayWeather> makeWeatherList(String weatherData) throws JSONException {
        ArrayList<DayWeather> dayWeather = new ArrayList<>();


        JSONObject data = new JSONObject(weatherData);

        Constants.CITY = data.getJSONObject("city").getString("name");
        Constants.COUNTRY = data.getJSONObject("city").getString("country");

//        TextView text = (TextView)findViewById(R.id.textView);

        JSONArray list = data.getJSONArray("list");
//        JSONArray weather = list.getJSONArray("weather");
//        JSONArray
        System.out.println("*********************This is the length of the list array: " + list.length());
//        System.out.println("*********************This is the length of the weather array: " + weather.length());

        for (int i = 0; i < list.length(); i++) {

            String dt = list.getJSONObject(i).getString("dt");
            String day = list.getJSONObject(i).getJSONObject("temp").getString("day");
            JSONObject m =list.getJSONObject(i).getJSONArray("weather").getJSONObject(0);
            String main = m.getString("main");
            String description = m.getString("description");
            String icon = m.getString("icon");
            DayWeather weather = new DayWeather(dt, Constants.CITY, main, Constants.COUNTRY, icon, day, description);
            dayWeather.add(weather);
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ dt: " + dt + " day: " + day + " main: " + main);
        }

        return dayWeather;
    }

    public void setCity(String city){this.city = city;}
    public void setMain(String main) {this.main = main;}
    public void setDay(String day){
        this.day = day;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }
    public void setId(String id){
        this.dt = id;
    }
//    public void setDescription(String description){this.description = description};

    public String getId(){return this.dt;}
    public String getCity(){
        return this.city;
    }
    public String getMain(){
        return this.main;
    }
    public String getDay(){
        return this.day;
    }
    public String getCountry(){
        return this.country;
    }
    public String getIcon(){return this.icon;}
//    public String getDescription(){return description};

    public static String getURL(String icon, boolean big){

//        http://openweathermap.org/img/w/10d.png

//        String photoURI = "http://openweathermap.org/img/w/" + icon + ".png";
        String photoURI = "http://openweathermap.org/img/w/10d.png";
        Log.i(Constants.TAG, "Photo url: " + photoURI);
        return photoURI;
    }


}
