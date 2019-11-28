package com.splitzapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.splitzapp.tab.Group;

import java.sql.BatchUpdateException;

public class GroupInfo extends AppCompatActivity {

    private String groupName;
    private TextView tvGroupName;
    private Button addExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        tvGroupName = findViewById(R.id.tvTitle);

        groupName = getIntent().getStringExtra("groupName");
        tvGroupName.setText(groupName);

        addExpense = findViewById(R.id.btnaddgroupexpense);
    }

    public void btnAddGroupExpense(View view) {
        Intent i = new Intent(getApplicationContext(), AddGroupExpense.class);
        i.putExtra("groupName", groupName);
        startActivity(i);
    }

}
