package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.splitzapp.activity.DatabaseHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private Button cancel;
    private TextView dateTimeDisplay;
    private Button addExpense;
    private EditText value;
    private EditText label;
    private EditText description;
    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        spinner = (Spinner)findViewById(R.id.spndropdown);
        spinner.setOnItemSelectedListener(this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        dateTimeDisplay = (TextView)findViewById(R.id.tvdate);
        dateTimeDisplay.setText(currentDate);

        cancel = (Button)findViewById(R.id.btnClose);
        addExpense = (Button)findViewById(R.id.btnConfirm);

        value = (EditText)findViewById(R.id.etValue);
        label = (EditText)findViewById(R.id.etLabel);
        description = (EditText)findViewById(R.id.etDescription);

        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();

    }

    //  Dropdown Menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnConfirm(View view) {
        db.execSQL("INSERT INTO EXPENSES(uid, amount, label, description, category) values (?, ?, ?, ?, ?)",
                new String[]{ getIntent().getStringExtra("userId"), value.getText().toString(), label.getText().toString(), description.getText().toString(), "none"});
        Intent i = new Intent();
        i.putExtra("value", value.getText().toString());
        i.putExtra("label", label.getText().toString());
        i.putExtra("description", description.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }

    public void btnClose(View view) {
        finish();
    }
}
