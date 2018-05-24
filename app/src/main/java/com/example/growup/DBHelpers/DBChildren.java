package com.example.growup.DBHelpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBChildren extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "childrenDB.db";
    public static final String TABLE_NAME = "children";
    public static int DATABASE_VERSION = 1;

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_LASTHEIGHT = "lastHeight";
    public static final String COLUMN_LASTWEIGHT = "lastWeight";

    public static final int NUM_COLUMN_ID = 0;
    public static final int NUM_COLUMN_NAME = 1;
    public static final int NUM_COLUMN_SURNAME = 2;
    public static final int NUM_COLUMN_SEX = 3;
    public static final int NUM_COLUMN_BIRTHDAY = 4;
    public static final int NUM_COLUMN_LASTHEIGHT = 5;
    public static final int NUM_COLUMN_LASTWEIGHT = 6;

    public DBChildren(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SURNAME + " TEXT, " +
                COLUMN_SEX + " TEXT, " +
                COLUMN_BIRTHDAY + " TEXT, " +
                COLUMN_LASTHEIGHT + " TEXT, " +
                COLUMN_LASTWEIGHT + " TEXT); ";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
