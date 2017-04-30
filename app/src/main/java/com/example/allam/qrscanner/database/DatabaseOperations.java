package com.example.allam.qrscanner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.allam.qrscanner.model.StatueModel;

import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.Columns.DESCRIPTION;
import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.Columns.NAME;
import static com.example.allam.qrscanner.database.DatabaseStructure.StatueTable.TABLE_NAME;

/**
 * Created by Allam on 4/30/2017.
 */

public class DatabaseOperations {
    private SQLiteDatabase mDatabase;

    public DatabaseOperations(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context.getApplicationContext());
        mDatabase = databaseHelper.getWritableDatabase();
    }

    private ContentValues getContentValues(StatueModel statue) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, statue.getName());
        contentValues.put(DESCRIPTION, statue.getDscription());
        return contentValues;
    }

    public void insertIntoStatues(StatueModel statue) {
        ContentValues contentValues = getContentValues(statue);
        mDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public String getStatueDescription(String statueName) {
        String statueDescription = null;
        Cursor cursor = mDatabase.query(TABLE_NAME, new String[]{DESCRIPTION}, NAME + " = ? ", new String[]{statueName}, null, null, null);
        if (cursor.moveToFirst()){
            statueDescription = cursor.getString(cursor.getColumnIndex(DESCRIPTION));
        }
        cursor.close();
        return statueDescription;
    }
}
