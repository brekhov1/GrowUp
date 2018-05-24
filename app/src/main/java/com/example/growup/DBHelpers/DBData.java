package com.example.growup.DBHelpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBData extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dataDB.db";
    public static final String TABLE_NAME = "data";
    public static int DATABASE_VERSION = 1;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PARENTID = "parentId";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_WEIGHT = "weight";

    public static final int NUM_COLUMN_ID = 0;
    public static final int NUM_COLUMN_PARENTID = 1;
    public static final int NUM_COLUMN_TIME = 2;
    public static final int NUM_COLUMN_HEIGHT = 3;
    public static final int NUM_COLUMN_WEIGHT = 4;

    public DBData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PARENTID + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_HEIGHT + " TEXT, " +
                COLUMN_WEIGHT + " TEXT); ";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
