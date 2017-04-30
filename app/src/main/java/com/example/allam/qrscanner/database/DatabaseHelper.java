package com.example.allam.qrscanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.allam.qrscanner.database.DatabaseStructure.DATABASE_NAME;
import static com.example.allam.qrscanner.database.DatabaseStructure.DATABASE_VERSION;
import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.Columns.DESCRIPTION;
import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.Columns.ID;
import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.Columns.NAME;
import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.TABLE_NAME;

/**
 * Created by Allam on 4/30/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private String query = "create table " + TABLE_NAME + " ( " +
            ID + " integer autoIncrement, " +
            NAME + " , " +
            DESCRIPTION + " );";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME + "if exists");
        db.execSQL(query);
    }
}
