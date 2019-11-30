package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.GroupExpenseListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddGroupExpense extends AppCompatActivity {

    private EditText amount;
    private EditText label;
    private Button confirm;
    private Button cancel;

    List<String> user;
    List<String> userAmount;
    GroupExpenseListView groupExpenseListView;

    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    public AddGroupExpense() {
        user = new ArrayList<>(Arrays.asList("user1test"));
        userAmount = new ArrayList<>();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();

        setContentView(R.layout.activity_add_group_expense);

        amount = findViewById(R.id.etAmount);
        label = findViewById(R.id.etGroupExpLabel);
        confirm = findViewById(R.id.btnConfirmGroupExpense);
        cancel = findViewById(R.id.btnCloseGroupExpense);

        Intent i = getIntent();
        //I need the username here in user list
        Cursor res0 = db.rawQuery("SELECT * from groupUsers WHERE gid = ?", new String[]{i.getStringExtra("groupId")});
        for(res0.moveToFirst(); !res0.isAfterLast(); res0.moveToNext()) {
            Cursor res1 = db.rawQuery("SELECT username FROM users WHERE uid = ?", new String[]{res0.getString(1)});
            res1.moveToFirst();
            user.add(res1.getString(0));
        }


        ListView listView = findViewById(R.id.lvlistview4);
        groupExpenseListView = new GroupExpenseListView(this, user, userAmount);
        listView.setAdapter(groupExpenseListView);


    }
    public void btnConfirmGroupExpense(View view) {
        finish();
    }

    public void btnCloseGroupExpense(View view) {
        finish();
    }

}
