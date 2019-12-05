package com.splitzapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.splitzapp.activity.DatabaseHelper;

import java.text.DateFormat;
import java.util.Calendar;

public class AddExpense extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String eId;
    private Spinner spinner;
    private EditText value;
    private EditText label;
    private EditText description;
    private TextView header;
    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mode = getIntent().getStringExtra("mode");

        header = findViewById(R.id.tvAddExpenseHeader);

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

        if(mode.equals("ADD")) {
            header.setText("Add Expense");
        }
        else if(mode.equals("EDIT")) {
            header.setText("Edit Expense");
            value.setText(getIntent().getStringExtra("value"));
            label.setText(getIntent().getStringExtra("label"));
            spinner.setSelection(((ArrayAdapter<String>)spinner.getAdapter()).getPosition(getIntent().getStringExtra("category")));
        }
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
        Intent i = new Intent();
        if(mode.equals("ADD")) {
            db.execSQL("INSERT INTO EXPENSES(uid, amount, label, description, category) values (?, ?, ?, ?, ?)",
                    new String[]{getIntent().getStringExtra("userId"),
                            value.getText().toString(),
                            label.getText().toString(),
                            description.getText().toString(),
                            spinner.getSelectedItem().toString()});
            eId = db.rawQuery("Select eid from expenses", new String[]{}).getString(0);
        }
        else if(mode.equals("EDIT")) {
            i.putExtra("position", getIntent().getStringExtra("position"));
            eId = getIntent().getStringExtra("id");
            db.execSQL("UPDATE expenses SET amount = ?, label = ?, category = ?, description = ? where eid = ?",
                    new String[]{value.getText().toString(), label.getText().toString(), spinner.getSelectedItem().toString(), description.getText().toString(), eId});
        }
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
