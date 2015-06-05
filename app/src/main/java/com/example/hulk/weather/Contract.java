package com.example.hulk.weather;

import android.provider.BaseColumns;

/**
 * Created by hulk on 6/4/15.
 */
public class Contract {
    public static final String DATABASE_NAME = "weather.db";
    WeatherEntry dw = new WeatherEntry();

    public static final class WeatherEntry implements BaseColumns{
        public int test = 2;

        public static final String TABLE_NAME = "weather_entry";
        public static final String _ID = "dt";
        public static final String CITY = "name";
        public static final String COUNTRY = "country";
        public static final String DAY = "day";
        public static final String ICON = "icon";
        public static final String MAIN = "main";
    }
}
