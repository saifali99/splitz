package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

    public AddGroupExpense() {
        user = new ArrayList<>(Arrays.asList("User1", "User2", "User3"));
        userAmount = new ArrayList<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_expense);

        amount = findViewById(R.id.etAmount);
        label = findViewById(R.id.etGroupExpLabel);
        confirm = findViewById(R.id.btnConfirmGroupExpense);
        cancel = findViewById(R.id.btnCloseGroupExpense);

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
