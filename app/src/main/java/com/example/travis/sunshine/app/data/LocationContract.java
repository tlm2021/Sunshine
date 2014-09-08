package com.example.travis.sunshine.app.data;

import android.provider.BaseColumns;

public class LocationContract {
    /* Inner class that defines the table contents of the location table */
    public static final class LocationEntry implements BaseColumns {

        public static final String TABLE_NAME = "location";

        public static final String COLUMN_LOCATION_SETTING = "location_setting";

        // Column with the city name for the location
        public static final String COLUMN_LOC_NAME = "city_name";

        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
    }
}
