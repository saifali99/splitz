package com.splitzapp.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SplitzDB";
    public static final String TB_NAME = "users";
    public static final String ID="_id";
    public static final String USERNAME="username";
    public static final String PASSWORD="password";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME + " (" + ID + " INTEGER PRIMARY KEY," + USERNAME + " VARCHAR," + PASSWORD + " VARCHAR )");
        db.execSQL("INSERT INTO " + TB_NAME + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "('1','admin','admin888')");
        db.execSQL("INSERT INTO " + TB_NAME + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "('2','root','root123')");
        db.execSQL("INSERT INTO " + TB_NAME + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "('3','wanqing','wanqing')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
