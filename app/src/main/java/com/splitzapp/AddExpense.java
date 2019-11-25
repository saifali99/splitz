package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private TextView textView;
    private TextView dateTimeDisplay;
    private Button addExpense;
    private EditText value;
    private EditText label;
    private EditText description;


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

        textView = (TextView)findViewById(R.id.tvclose);
        addExpense = (Button)findViewById(R.id.btnConfirm);

        value = (EditText)findViewById(R.id.etValue);
        label = (EditText)findViewById(R.id.etLabel);
        description = (EditText)findViewById(R.id.etDescription);
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
        Bundle bundle = new Bundle();
        bundle.putString("value", value.getText().toString());
        bundle.putString("label", label.getText().toString());
        bundle.putString("description", description.getText().toString());
        Expense expense = new Expense();
        expense.setArguments(bundle);

        Intent i = new Intent();

        i.putExtra("value", value.getText().toString());
        i.putExtra("label", label.getText().toString());
        i.putExtra("description", description.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }

    public void tvClose(View view) {
        finish();
    }
}
