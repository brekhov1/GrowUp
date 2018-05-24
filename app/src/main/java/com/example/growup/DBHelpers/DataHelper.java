package com.example.growup.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.example.growup.DBHelpers.DBData.TABLE_NAME;

public class DataHelper {

    SQLiteDatabase database;

    public DataHelper(Context context) {
        DBData dataHelper = new DBData(context);
        database = dataHelper.getWritableDatabase();
    }

    public long insert(int parentId, long time, double height, double weight) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBData.COLUMN_PARENTID, parentId);
        contentValues.put(DBData.COLUMN_TIME, time);
        contentValues.put(DBData.COLUMN_HEIGHT, height);
        contentValues.put(DBData.COLUMN_WEIGHT, weight);

        return database.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<DataRecord> getAllByChild(int parentId1) {

        Cursor mCursor = database.query(TABLE_NAME, null, "parentId = ?", new String[] { parentId1+"" }, null, null, null);
        ArrayList<DataRecord> arr = new ArrayList<>();

        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                int id = mCursor.getInt(DBData.NUM_COLUMN_ID);
                int parentId = mCursor.getInt(DBData.NUM_COLUMN_PARENTID);
                long time = mCursor.getLong(DBData.NUM_COLUMN_TIME);
                double height = mCursor.getDouble(DBData.NUM_COLUMN_HEIGHT);
                double weight = mCursor.getDouble(DBData.NUM_COLUMN_WEIGHT);

                arr.add(new DataRecord(id, parentId, time, height, weight));

            } while (mCursor.moveToNext());
        }
        database.close();
        return arr;
    }

    public DataRecord getDataRecord(int id){
        Cursor c = database.query(TABLE_NAME, null, null, null, null, null,
                null);
        c.moveToPosition(id);
        DataRecord dataRecord = new DataRecord(c.getInt(0), c.getInt(1), c.getLong(2),c.getDouble(3), c.getDouble(4));
        database.close();
        return dataRecord;
    }

    public void delete(int id){
        database.delete(DBData.TABLE_NAME,"_id=" + id,null);
        database.close();
    }

    public void deleteAll(){
        Cursor c = database.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Integer> arr = new ArrayList<>();

        c.moveToFirst();
        if (!c.isAfterLast()) {
            do {
                int id = c.getInt(DBChildren.NUM_COLUMN_ID);
                arr.add(id);
            } while (c.moveToNext());
        }

        for (int i = 0; i<arr.size();i++) {
            database.delete(DBData.TABLE_NAME,"_id=" + arr.get(i),null);
        }
        database.close();
    }

    String TAG = "DbHelper";

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        Log.d(TAG, "getTableAsString called");
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst() ){
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }

        return tableString;
    }

}
