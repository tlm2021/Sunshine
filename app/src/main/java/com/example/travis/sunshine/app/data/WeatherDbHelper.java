package com.example.travis.sunshine.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.travis.sunshine.app.data.LocationContract.LocationEntry;
import com.example.travis.sunshine.app.data.WeatherContract.WeatherEntry;

/**
 * Created by travis on 9/8/14.
 */
public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weather.db";
    private static final int DATABASE_VERSION = 1;

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold locations.

        final String SQL_CREATE_LOCATION_TABLE =
                "CREATE TABLE " + LocationEntry.TABLE_NAME + "(" +
                        LocationEntry._ID + " INTEGER PRIMARY KEY," +

                        LocationEntry.COLUMN_LOCATION_SETTING + " TEXT UNIQUE NOT NULL, " +
                        LocationEntry.COLUMN_LOC_NAME + " TEXT NOT NULL, " +

                        LocationEntry.COLUMN_LATITUDE + " REAL NOT NULL, " +
                        LocationEntry.COLUMN_LONGITUDE + " REAL NOT NULL " +
                        "UNIQUE (" + LocationEntry.COLUMN_LOCATION_SETTING + ") ON CONFLICT IGNORE" +
                        " );";

        final String SQL_CREATE_WEATHER_TABLE =
                "CREATE TABLE " + WeatherEntry.TABLE_NAME + " (" +
                        WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +

                        WeatherEntry.COLUMN_LOC_KEY + " INTEGER NOT NULL," +
                        WeatherEntry.COLUMN_DATETEXT + " TEXT NOT NULL, " +
                        WeatherEntry.COLUMN_SHORT_DESC + " TEXT NOT NULL, " +
                        WeatherEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL, " +

                        WeatherEntry.COLUMN_MIN_TEMP + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_MAX_TEMP + " REAL NOT NULL, " +

                        WeatherEntry.COLUMN_HUMIDITY + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_PRESSURE + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                        WeatherEntry.COLUMN_DEGREES + " REAL NOT NULL, " +

                        " FOREIGN KEY (" + WeatherEntry.COLUMN_LOC_KEY + ") REFERENCES " +
                        LocationEntry.TABLE_NAME + " (" + LocationEntry._ID + "), " +

                        " UNIQUE (" + WeatherEntry.COLUMN_DATETEXT + ", " +
                        WeatherEntry.COLUMN_LOC_KEY + ") ON CONFLICT REPLACE);";

        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
