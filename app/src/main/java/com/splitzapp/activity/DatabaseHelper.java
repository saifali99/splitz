package com.splitzapp.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SplitzDB";
    private static final String TB_NAME_USERS = "users";
    private static final String ID ="uid";
    private static final String USERNAME="username";
    private static final String PASSWORD="password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_NAME_USERS + " (" + ID + " INTEGER PRIMARY KEY autoincrement," + USERNAME + " VARCHAR UNIQUE," + PASSWORD + " VARCHAR )");
        db.execSQL("CREATE TABLE IF NOT EXISTS expenses(eid INTEGER PRIMARY KEY AUTOINCREMENT, uid INTEGER, amount INTEGER, label varchar(30), description varchar(128), category varchar(30), FOREIGN KEY(uid) REFERENCES users(uid))");
        db.execSQL("CREATE TABLE groups(gid INTEGER PRIMARY KEY AUTOINCREMENT,GROUPNAME VARCHAR(30))");
        db.execSQL("CREATE TABLE IF NOT EXISTS groupUsers(gid INTEGER, uid INTEGER,  Primary Key (uid, gid), FOREIGN KEY(gid) REFERENCES groups(gid), FOREIGN KEY(uid) REFERENCES users(uid))");
        db.execSQL("CREATE TABLE IF NOT EXISTS groupExpense(eid INTEGER PRIMARY KEY AUTOINCREMENT, gid INTEGER, expenseLabel varchar, FOREIGN KEY(gid) references groups(gid))");
        db.execSQL("CREATE TABLE IF NOT EXISTS groupUserExpense(eid INTEGER, gid INTEGER, uid INTEGER, amount REAL, FOREIGN KEY(gid) references groups(gid), FOREIGN KEY(uid) REFERENCES users(uid), FOREIGN KEY(eid) references groupExpenses(eid))");
        db.execSQL("INSERT INTO " + TB_NAME_USERS + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "('1','admin','admin888')");
        db.execSQL("INSERT INTO " + TB_NAME_USERS + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "('2','root','root123')");
        db.execSQL("INSERT INTO " + TB_NAME_USERS + "(" + ID + "," + USERNAME + "," + PASSWORD + ") VALUES" + "('3','wanqing','wanqing')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
