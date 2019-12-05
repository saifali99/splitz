package com.splitzapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.GroupExpenseListView;

import java.util.ArrayList;
import java.util.List;

public class AddGroupExpense extends AppCompatActivity {

//    List<UserGroupExpenseStatus> userGroupExpenseStatuses;
    private List<String> involvedUser;
    private List<String> involvedUserAmount;
    private EditText amount;
    private EditText label;
    private Spinner user;
    private EditText userAmount;

    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;

    private String groupId;

    public AddGroupExpense() {
        involvedUser = new ArrayList<>();
        involvedUserAmount = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_expense);

        amount = findViewById(R.id.etAmount);
        label = findViewById(R.id.etGroupExpLabel);
        user = findViewById(R.id.spnInvolvedUser);
        userAmount = findViewById(R.id.etInvolvedUserAmount);

        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();

        groupId = getIntent().getStringExtra("groupId");
        Cursor cursor = db.rawQuery("SELECT u.username FROM groupUsers gu JOIN users u ON gu.uid = u.uid WHERE gu.gid = ?", new String[]{groupId});
        fillGroupUserSpinner(cursor);

        ListView listView = findViewById(R.id.lvlistview4);
        GroupExpenseListView groupExpenseListView = new GroupExpenseListView(this, involvedUser, involvedUserAmount);
        listView.setAdapter(groupExpenseListView);
    }

    public void btnAddInvolvedUser(View view) {
        this.involvedUser.add(user.getSelectedItem().toString());
        this.involvedUserAmount.add(userAmount.getText().toString());
    }

    public void btnConfirmGroupExpense(View view) {
        // Query is not working
        // (Caused by: android.database.sqlite.SQLiteException: no such table: groupExpense)
        // Need to look at it
        // Need to add label in group expense
        // No table for saving expenses paid by each user
        db.execSQL("INSERT INTO groupExpense(gid, expense) values (?, ?)",
                new String[]{groupId, amount.getText().toString()});

        /*Intent i = new Intent();
        i.putExtra("label", label.getText().toString());
        i.putExtra("amount", amount.getText().toString());
        setResult(RESULT_OK, i);*/
        finish();
    }

    public void btnCloseGroupExpense(View view) {
        finish();
    }

    private void fillGroupUserSpinner(Cursor cursor) {
        List<String> groupUsers = new ArrayList<>();
        while (cursor.moveToNext()) {
            groupUsers.add(cursor.getString(0));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.support_simple_spinner_dropdown_item, groupUsers);
        user.setAdapter(adapter);
    }
}
