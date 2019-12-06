package com.splitzapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.GroupExpenseListView;

import java.util.ArrayList;
import java.util.List;

public class GroupInfo extends AppCompatActivity {

    private static final int REQUEST_CODE_GET_EXPENSE = 1;
    private List<String> label;
    private List<String> amount;
    private String groupName;
    private String groupId;
    private GroupExpenseListView groupExpenseListView;

    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;

    public GroupInfo() {
        label = new ArrayList<>();
        amount = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();

        TextView tvGroupName = findViewById(R.id.tvTitle);

        groupName = getIntent().getStringExtra("groupName");
        groupId = getIntent().getStringExtra("groupId");
        tvGroupName.setText(groupName);

        label.clear();
        amount.clear();

        Cursor res = db.rawQuery("SELECT * from groupExpense WHERE gid = ?", new String[]{groupId});
        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            label.add(res.getString(2));
            Cursor tmp = db.rawQuery("Select sum(amount) from groupUserExpense WHERE eid = ?", new String[]{res.getString(0)});
            tmp.moveToFirst();
            System.out.println("Here"+tmp.getString(0));
            amount.add(tmp.getString(0));
        }

        ListView listView = findViewById(R.id.lvGroupExpense);
        groupExpenseListView = new GroupExpenseListView(this, label, amount);
        listView.setAdapter(groupExpenseListView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE_GET_EXPENSE == requestCode && data != null) {
            label.clear();
            amount.clear();

            Cursor res = db.rawQuery("SELECT * from groupExpense WHERE gid = ?", new String[]{groupId});
            for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                label.add(res.getString(2));
                Cursor tmp = db.rawQuery("Select sum(amount) from groupUserExpense WHERE eid = ?", new String[]{res.getString(0)});
                tmp.moveToFirst();
                amount.add(tmp.getString(0));
            }

            ListView listView = findViewById(R.id.lvGroupExpense);
            groupExpenseListView = new GroupExpenseListView(this, label, amount);
            listView.setAdapter(groupExpenseListView);
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public void btnAddGroupExpense(View view) {
        Intent i = new Intent(getApplicationContext(), AddGroupExpense.class);
        i.putExtra("groupName", groupName);
        i.putExtra("groupId", groupId);
        startActivityForResult(i, REQUEST_CODE_GET_EXPENSE);
    }
//    public void BtnGenerateReport(View view) {
//        Intent i = new Intent(getApplicationContext(), GroupReport.class);
//        i.putExtra("groupName", groupName);
//        i.putExtra("groupId", groupId);
//        startActivity(i);
//    }
}
