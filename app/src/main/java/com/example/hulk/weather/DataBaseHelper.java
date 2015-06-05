package com.example.hulk.weather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by hulk on 6/4/15.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;

    private String[] projection = {
            Contract.WeatherEntry._ID,
            Contract.WeatherEntry.CITY,
            Contract.WeatherEntry.COUNTRY,
            Contract.WeatherEntry.DAY,
            Contract.WeatherEntry.MAIN,
            Contract.WeatherEntry.ICON,};

    private static final String DATABASE_CREATE =
            "CREATE TABLE " +
                    Contract.WeatherEntry.TABLE_NAME + "(" +
                    Contract.WeatherEntry._ID + " INTEGER PRIMARY KEY, " +
                    Contract.WeatherEntry.CITY + " TEXT NOT NULL, " +
                    Contract.WeatherEntry.COUNTRY + " TEXT NOT NULL, " +
                    Contract.WeatherEntry.DAY + " TEXT NOT NULL, " +
                    Contract.WeatherEntry.MAIN + " TEXT NOT NULL, " +
                    Contract.WeatherEntry.ICON + " TEXT NOT NULL)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.WeatherEntry.TABLE_NAME;

    public DataBaseHelper(Context context) { super(context, Contract.DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Constants.TAG, " Create table command: " + DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public void insertWeatherEntry(DayWeather weather) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contract.WeatherEntry._ID, weather.getId());
        cv.put(Contract.WeatherEntry.CITY, weather.getCity());
        cv.put(Contract.WeatherEntry.COUNTRY, weather.getCountry());
        cv.put(Contract.WeatherEntry.DAY, weather.getDay());
        cv.put(Contract.WeatherEntry.MAIN, weather.getMain());
        cv.put(Contract.WeatherEntry.ICON, weather.getIcon());

        db.insert(Contract.WeatherEntry.TABLE_NAME, null, cv);
    }

    public Cursor getAllRows() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Contract.WeatherEntry.TABLE_NAME, projection, null, null, null, null, null);

//        Here's the method with arguments:
//        public Cursor query (String table, String[] columns, String selection, String[]
//        selectionArgs, String groupBy, String orderBy, String limit)

    }

    public Cursor getRowByID(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] ids = {String.valueOf(id)};
        return db.query(Contract.WeatherEntry.TABLE_NAME, projection, Contract.WeatherEntry._ID + "==?", ids, null, null, null);
    }

    public void deleteRow(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] ids = {String.valueOf(id)};
        db.delete(Contract.WeatherEntry.TABLE_NAME, Contract.WeatherEntry._ID + "==?", ids);
    }

    public void addRows(List<DayWeather> weather) {
        for (DayWeather w : weather) {
            insertWeatherEntry(w);
        }
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + Contract.WeatherEntry.TABLE_NAME);
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
    }
}
