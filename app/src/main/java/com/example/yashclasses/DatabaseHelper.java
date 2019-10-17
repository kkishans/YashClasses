package com.example.yashclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "Student.db";
    public static final String TABLE_NAME = "Student";
    public static final String COL_1 = "Id";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Std";
    public static final String COL_4 = "Medium";
    public static final String COL_5 = "ContactNo";
    public static final String COL_6 = "AdmissionDate";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table  " + TABLE_NAME + "(Sid  INTEGER PRIMARY KEY  AUTOINCREMENT, Name TEXT,Std text,Medium text,ContactNo text,AdmissionDate DATE )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
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
}
