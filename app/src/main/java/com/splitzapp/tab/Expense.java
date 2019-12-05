package com.splitzapp.tab;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.splitzapp.AddExpense;
import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.ExpenseListView;
import com.splitzapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expense extends Fragment {

    private static final int REQUEST_CODE_GET_EXPENSE = 1;
    private List<String> valueList;
    private List<String> labelList;
    private List<String> categoryList;
    private ExpenseListView expenseListView;

    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    public Expense() {
        valueList = new ArrayList<>();
        labelList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbhelper = new DatabaseHelper(getContext());
        db = dbhelper.getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        Button addExpense = view.findViewById(R.id.btnadd);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddExpense.class);
                intent.putExtra("userId", getArguments().getString("userId", "null"));
                startActivityForResult(intent, REQUEST_CODE_GET_EXPENSE);
            }
        });

        labelList.clear();
        valueList.clear();
        categoryList.clear();

        Cursor cursor = db.rawQuery("SELECT e.amount, e.label, e.category FROM expenses e WHERE e.uid = ?", new String[]{ getArguments().getString("userId")});
        while (cursor.moveToNext()) {
            this.valueList.add(cursor.getString(0));
            this.labelList.add(cursor.getString(1));
            this.categoryList.add(cursor.getString(2));
        }

        ListView listView = view.findViewById(R.id.lvlistview1);
        expenseListView = new ExpenseListView(getActivity(), valueList, labelList, categoryList);
        listView.setAdapter(expenseListView);

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE_GET_EXPENSE == requestCode && data != null) {
            String value, label, description, category;

            value = data.getStringExtra("value");
            label = data.getStringExtra("label");
            category = data.getStringExtra("category");
//            description = data.getStringExtra("description");

            this.valueList.add(value);
            this.labelList.add(label);
            this.categoryList.add(category);

            expenseListView.notifyDataSetChanged();
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
