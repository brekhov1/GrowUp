package com.example.growup.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import static com.example.growup.DBHelpers.DBChildren.COLUMN_BIRTHDAY;
import static com.example.growup.DBHelpers.DBChildren.COLUMN_ID;
import static com.example.growup.DBHelpers.DBChildren.TABLE_NAME;

public class ChildrenHelper {

    SQLiteDatabase database;

    public ChildrenHelper(Context context) {
        DBChildren childrenHelper = new DBChildren(context);
        database = childrenHelper.getWritableDatabase();
    }

    public long insert(String name, String surname, String sex, long birthday, double lastHeight, double lastWeight) {
        ContentValues contentValues1 = new ContentValues();

        contentValues1.put(DBChildren.COLUMN_NAME, name);
        contentValues1.put(DBChildren.COLUMN_SURNAME, surname);
        contentValues1.put(DBChildren.COLUMN_SEX, sex);
        contentValues1.put(DBChildren.COLUMN_BIRTHDAY, birthday);
        contentValues1.put(DBChildren.COLUMN_LASTHEIGHT, lastHeight);
        contentValues1.put(DBChildren.COLUMN_LASTWEIGHT, lastWeight);

        return database.insert(DBChildren.TABLE_NAME, null, contentValues1);
    }

    public ArrayList<Child> getAll() {

        Cursor c = database.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<Child> arr = new ArrayList<>();

        c.moveToFirst();
        if (!c.isAfterLast()) {
            do {
                int id = c.getInt(DBChildren.NUM_COLUMN_ID);
                String name = c.getString(DBChildren.NUM_COLUMN_NAME);
                String surname = c.getString(DBChildren.NUM_COLUMN_SURNAME);
                String sex = c.getString(DBChildren.NUM_COLUMN_SEX);
                long birthday = Long.parseLong(c.getString(DBChildren.NUM_COLUMN_BIRTHDAY));
//                Log.e("AAA",birthday+"");
                double lastHeight = Double.parseDouble(c.getString(DBChildren.NUM_COLUMN_LASTHEIGHT));
                double lastWeight = Double.parseDouble(c.getString(DBChildren.NUM_COLUMN_LASTWEIGHT));

                arr.add(new Child(id, name, surname, sex, birthday, lastHeight, lastWeight));

            } while (c.moveToNext());
        }
        database.close();
        return arr;
    }

    public long getChildBirthday(int childId){
        Cursor cb = database.query(TABLE_NAME, new String[] {COLUMN_ID,COLUMN_BIRTHDAY}, null, null, null, null, null);

        long birthday = 0;

        if(cb.moveToFirst() && cb.getCount() >= 1){
            do{
                if (cb.getInt(0)==childId) {
                    birthday = Long.parseLong(cb.getString(1));
                    break;
                }
            } while(cb.moveToNext());
        }
        database.close();
        return birthday;
    }

    public Child getChild(int id){
        Cursor c = database.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToPosition(id);
        Child child = new Child(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), Long.parseLong(c.getString(4)), Double.parseDouble(c.getString(5)), Double.parseDouble(c.getString(6)));
        database.close();
        return child;
    }

    public void updateInfo(Child child){
        ContentValues cv = new ContentValues();

        cv.put(DBChildren.COLUMN_NAME, child.getName());
        cv.put(DBChildren.COLUMN_SURNAME, child.getSurname());
        cv.put(DBChildren.COLUMN_SEX, child.getSex());
        cv.put(DBChildren.COLUMN_BIRTHDAY, child.getBirthday()+"");

        Log.e("update",child.toString());

        database.update(DBChildren.TABLE_NAME,cv,"_id=?",new String[]{String.valueOf(child.getId())});
        database.close();
    }

    public void updateLast(int id, double lastHeight, double lastWeight){
        ContentValues cv = new ContentValues();

        cv.put(DBChildren.COLUMN_LASTHEIGHT, lastHeight);
        cv.put(DBChildren.COLUMN_LASTWEIGHT, lastWeight);

        Log.e("update","ok");

        database.update(DBChildren.TABLE_NAME,cv,"_id=?",new String[]{id+""});
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
            database.delete(DBChildren.TABLE_NAME,"_id=" + arr.get(i),null);
        }
        database.close();
    }

    public void delete(long id){
        database.delete(DBChildren.TABLE_NAME,"_id=" + id,null);
        database.close();
    }

    String TAG = "DbHelper";
    // functions omitted


    /**
     * Helper function that parses a given table into a string
     * and returns it for easy printing. The string consists of
     * the table name and then each row is iterated through with
     * column_name: value pairs printed out.
     *
     * @param db the database to get the table from
     * @param tableName the the name of the table to parse
     * @return the table tableName as a string
     */
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