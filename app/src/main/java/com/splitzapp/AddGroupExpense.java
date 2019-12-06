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
        Cursor res = db.rawQuery("SELECT * FROM USERS WHERE username = ?", new String[]{user.getSelectedItem().toString()});
        if(!res.isAfterLast() ) {
            this.involvedUser.add(user.getSelectedItem().toString());
            this.involvedUserAmount.add(userAmount.getText().toString());
        }
    }

    public void btnConfirmGroupExpense(View view) {
        float totalAmount = Float.parseFloat(amount.getText().toString())/(float)involvedUser.size();
        db.execSQL("INSERT INTO groupExpense(gid, expenseLabel) values (?, ?)",
                new String[]{groupId, label.getText().toString()} );
        Cursor res0 = db.rawQuery("SELECT * FROM groupExpense", new String[]{});
        res0.moveToLast();
        for (int i = 0; i < involvedUser.size(); i++) {
            float tamount = Float.parseFloat(involvedUserAmount.get(i))-totalAmount;
            Cursor res1 = db.rawQuery("Select * from users where username = ?", new String[]{involvedUser.get(i)});
            res1.moveToFirst();
            db.execSQL("UPDATE groupUsers SET balance = balance + ?", new String[]{String.valueOf(tamount)});
            db.execSQL("INSERT INTO groupUserExpense(eid, gid, uid, amount) values (?, ?, ?, ?)", new String[]{res0.getString(0), groupId, res1.getString(0), involvedUserAmount.get(i)});
        }
        setResult(RESULT_OK, getIntent());
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
