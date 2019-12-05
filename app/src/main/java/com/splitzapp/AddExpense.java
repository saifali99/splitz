package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.splitzapp.activity.DatabaseHelper;

import java.text.DateFormat;
import java.util.Calendar;

public class AddExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;

    private EditText value;
    private EditText label;
    private EditText description;
    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        spinner = findViewById(R.id.spndropdown);
        spinner.setOnItemSelectedListener(this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView dateTimeDisplay = findViewById(R.id.tvdate);
        dateTimeDisplay.setText(currentDate);

        value = findViewById(R.id.etValue);
        label = findViewById(R.id.etLabel);
        description = findViewById(R.id.etDescription);

        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
    }

    //  Dropdown Menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnConfirm(View view) {
        db.execSQL("INSERT INTO EXPENSES(uid, amount, label, description, category) values (?, ?, ?, ?, ?)",
                new String[]{ getIntent().getStringExtra("userId"),
                        value.getText().toString(),
                        label.getText().toString(),
                        description.getText().toString(),
                        spinner.getSelectedItem().toString()});

        Intent i = new Intent();
        i.putExtra("value", value.getText().toString());
        i.putExtra("label", label.getText().toString());
        i.putExtra("description", description.getText().toString());
        i.putExtra("category", spinner.getSelectedItem().toString());
        setResult(RESULT_OK, i);
        finish();
    }

    public void btnClose(View view) {
        finish();
    }
}
