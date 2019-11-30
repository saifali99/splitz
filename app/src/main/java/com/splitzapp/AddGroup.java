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
import com.splitzapp.listview.UserListView;
import com.splitzapp.tab.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddGroup extends AppCompatActivity {

    private Button btnConfirm;
    private Button btnCancel;
    private EditText etGroupName;
    private Button btnAddusers;
    private EditText etPersonname;

    private List<String> personName;
    private UserListView userListView;
    private String groupId;

    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    public AddGroup() {
        personName = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        etGroupName = findViewById(R.id.etGroupName);
        btnAddusers = findViewById(R.id.btnAddUsers);
        btnConfirm = findViewById(R.id.btnConfirmGroup);
        btnCancel = findViewById(R.id.btnCloseGroup);
        etPersonname = findViewById(R.id.etpersonname);

        ListView listView = findViewById(R.id.lvlistview3);
        userListView = new UserListView(this, personName);
        listView.setAdapter(userListView);

        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
    }

    public void btnAddUsers(View view) {
        String personName = etPersonname.getText().toString();
        //save the personNames that user add in data with there groupname as etGroupname
        Cursor cursor;
        cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{personName});
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            this.personName.add(personName);
            userListView.notifyDataSetChanged();
        }

        etPersonname.setText("");
    }

    public void btnConfirmGroup(View view) {

        db.execSQL("INSERT INTO groups(groupname) VALUES (?)", new String[]{etGroupName.getText().toString()});
        Cursor res0 = db.rawQuery("SELECT * FROM groups", new String[]{});
        res0.moveToLast();
        for (int i = 0; i < personName.size(); i++) {
            Cursor res = db.rawQuery("SELECT * FROM USERS WHERE username = ?", new String[]{personName.get(i)});
            if(!res.isAfterLast() ) {
                res.moveToFirst();
                db.execSQL("INSERT INTO groupUsers VALUES (?, ?)", new String[]{res0.getString(0), res.getString(0)});
            }
        }
        Intent intent = new Intent();
        intent.putExtra("groupName", etGroupName.getText().toString());
        intent.putExtra("groupId", String.valueOf(res0.getString(0)));
        setResult(RESULT_OK, intent);
        finish();
    }

    public void btnCloseGroup(View view) {
        finish();
    }

}
