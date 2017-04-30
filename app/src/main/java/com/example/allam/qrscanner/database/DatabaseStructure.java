package com.example.allam.qrscanner.database;

import android.provider.BaseColumns;

/**
 * Created by Allam on 4/30/2017.
 */

public class DatabaseStructure implements BaseColumns {
    public static final String DATABASE_NAME = "museum.db";
    public static final int DATABASE_VERSION = 1;

    public static final class StatueTable{
        public static final String TABLE_NAME = "statue";
        public static final class Columns{
            public static final String ID = _ID;
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
        }
    }
}
