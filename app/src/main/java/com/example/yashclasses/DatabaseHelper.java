package com.example.yashclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.UserManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "Student.db";
    public static final String TABLE_NAME = "Student";
    public static String STD_TABLE_NAME = "std_table";
    public static final String COL_1 = "Sid";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Std";
    public static final String COL_4 = "Medium";
    public static final String COL_5 = "ContactNo";
    public static final String COL_6 = "AdmissionDate";
    public static final String COL_7 = "location";
    public static final String COL_8 = "to_pay";
    public static final String STD_COL_0 = "std_id";
    public static final String STD_COL_1 = "std";
    public static final String STD_COL_2 = "medium";
    public static final String STD_COL_3 = "fees";
    public static final String PAYMENT_TABLE = "payment";
    public static final String PAY_COL_1 = "pid";
    public static final String PAY_COL_2 = "sid";
    public static final String PAY_COL_3 = "credit";
    public static final String PAY_COL_4 = "timestamp";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + STD_TABLE_NAME + "(" + STD_COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STD_COL_1 + " TEXT, " + STD_COL_2 + " TEXT, " + STD_COL_3 + " Double)");
        db.execSQL("create table  " + TABLE_NAME + "(Sid  INTEGER PRIMARY KEY  AUTOINCREMENT, Name TEXT,Std text,Medium text,ContactNo text,AdmissionDate DATE,location TEXT,to_pay float )");
        db.execSQL("create table " + PAYMENT_TABLE + " ( " + PAY_COL_1 + " INTEGER PRIMARY KEY ," + PAY_COL_2 + " INTEGER REFERENCES " + TABLE_NAME + "(" + COL_1 + ") ," + PAY_COL_3 + " TEXT ," + PAY_COL_4 + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE " + STD_TABLE_NAME);
        db.execSQL("DROP TABLE " + PAYMENT_TABLE);
        onCreate(db);
    }

    public boolean insertData(String name, String std, String medium, String Contact, String Date, String location, float fees) {
        SQLiteDatabase db = this.getReadableDatabase();

        if (fees == 0) {
            fees = GetFees(std,medium);

        }


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, std);
        contentValues.put(COL_4, medium);
        contentValues.put(COL_5, Contact);
        //contentValues.put(COL_6 , Date);
        contentValues.put(COL_7, location);
        contentValues.put(COL_8, fees);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else return true;
    }

    public boolean InsertStd(String std, String medium, double fees) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STD_COL_1, std);
        contentValues.put(STD_COL_2, medium);
        contentValues.put(STD_COL_3, fees);
        long result = db.insert(STD_TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else return true;
    }

    public Cursor getAllStd() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(STD_TABLE_NAME, null, null, null, null, null, STD_COL_3);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getAllStudent(String sortBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        String sb = "3";
        cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_3);
        if(sortBy.equals("Name"))
            cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_2);
        else if(sortBy.equals("Medium"))
            cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_4);
        else if(sortBy.equals("Class"))
            cursor = db.query(TABLE_NAME, null, null, null, null, null, COL_3);

        cursor.moveToFirst();
        return cursor;
    }



    public Cursor getOneStd(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + STD_TABLE_NAME + " where " + STD_COL_0 + " = " + id, null);
        return cursor;
    }

    public String[] getAllStdNames() {
            String[] stdNamesList;
            int i = 0;
            SQLiteDatabase db = this.getReadableDatabase();
            String[] cols = {STD_COL_1};

            Cursor c = db.query(true, STD_TABLE_NAME, cols, null, null, null, null, STD_COL_3,null);

            stdNamesList = new String[c.getCount()];

        while(c.moveToNext()){
               stdNamesList[i] = c.getString(c.getColumnIndex(STD_COL_1));
               i++;
        }
        return stdNamesList;
    }

//    public float GetFees(String std, String medium){
//        Cursor fees;
//        Float fee;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//         fees = db.rawQuery("select fees from " + STD_TABLE_NAME + " where " +STD_COL_1 + " = " + std + " and " + STD_COL_2 + " = '" + medium + "'" ,null);
//
//        float[] stdNamesList;
//        while (c.moveToNext()) {
//                stdNamesList[i] = c.getString(c.getColumnIndex(STD_COL_1));
//                i++;
//            }
//            return stdNamesList;
//    }
    public String[] getAllMediumNames(String Class) {
        String[] stdMediumList;
        int i = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT "+STD_COL_2 +" FROM "+ STD_TABLE_NAME+" WHERE "+STD_COL_1+" = '"+Class+"'",null);


        stdMediumList = new String[c.getCount()];
        while (c.moveToNext()) {
            stdMediumList[i] = c.getString(c.getColumnIndex(STD_COL_2));
            i++;
        }
        return stdMediumList;
    }




    public boolean insertPayment(float amount , String sid){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PAY_COL_2,sid);
        contentValues.put(PAY_COL_3 , amount);

        long result = db.insert(PAYMENT_TABLE,null,contentValues);

        if (result == -1)
            return  false;
        else    return true;
    }

        public float GetFees (String std, String medium){
            Cursor fees;
            Float fee;
            SQLiteDatabase db = this.getReadableDatabase();

            fees = db.rawQuery("select fees from " + STD_TABLE_NAME + " where " + STD_COL_1 + " = '" + std + "' and " + STD_COL_2 + " = '" + medium + "'", null);

            if (fees.getCount() == 1) {
                fees.moveToFirst();
                fee = fees.getFloat(fees.getColumnIndex(STD_COL_3));
                return fee;
            } else return 0;


        }

        public boolean updatePay(float amount ,String sid){
                SQLiteDatabase db =  this.getReadableDatabase();
                float fee;
                Cursor c = db.rawQuery("select " + COL_8 + " from " + TABLE_NAME + " where " + COL_1 + " = " + sid , null );

                c.moveToFirst();
                fee = c.getFloat(c.getColumnIndex(COL_8));
                fee = fee - amount;

                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_8 , fee);
               // long res = db.rawQuery("Update " + TABLE_NAME + " set " + COL_8 + " = " + COL_8 + " - " + amount + " where "+ COL_1 + " = " + sid ,null);
                long result = db.update(TABLE_NAME , contentValues , COL_1 + " = "+ sid,null);

            if (result == -1)
                return false;
            else return true;
        }
 }


