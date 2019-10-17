package com.example.yashclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "Student.db";
    public static final String TABLE_NAME = "Student";
    public static String STD_TABLE_NAME = "std_table";
    public static final String COL_1 = "Id";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Std";
    public static final String COL_4 = "Medium";
    public static final String COL_5 = "ContactNo";
    public static final String COL_6 = "AdmissionDate";
    public static String STD_COL_0 = "std_id";
    public static String STD_COL_1 = "std";
    public static String STD_COL_2 = "medium";
    public static String STD_COL_3 = "fees";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+STD_TABLE_NAME+ "("+STD_COL_0+" INTEGER PRIMARY KEY AUTOINCREMENT, "+STD_COL_1+" TEXT, "+STD_COL_2+" TEXT, "+STD_COL_3+" Double)");
        db.execSQL("create table  " + TABLE_NAME + "(Sid  INTEGER PRIMARY KEY  AUTOINCREMENT, Name TEXT,Std text,Medium text,ContactNo text,AdmissionDate DATE )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE "+STD_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String std, String medium, String Contact, String Date){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , name);
        contentValues.put(COL_3 , std);
        contentValues.put(COL_4 , medium);
        contentValues.put(COL_5, Contact);
        //contentValues.put(COL_6 , Date);

        long  result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return  false;
        else    return true;
    }

    public boolean InsertStd(String std,String medium,double fees){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STD_COL_1,std);
        contentValues.put(STD_COL_2,medium);
        contentValues.put(STD_COL_3,fees);
        long result =  db.insert(STD_TABLE_NAME,null,contentValues);
        if (result == -1)
            return  false;
        else    return true;
    }
    public Cursor getAllStd(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(STD_TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        return  cursor;
    }
}
