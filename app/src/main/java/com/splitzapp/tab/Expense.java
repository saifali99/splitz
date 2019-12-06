package com.splitzapp.tab;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.splitzapp.AddExpense;
import com.splitzapp.R;
import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.ExpenseListView;

import java.util.ArrayList;
import java.util.List;

public class Expense extends Fragment {

    private static final int REQUEST_CODE_GET_EXPENSE = 1;
    private List<String> idList;
    private List<String> valueList;
    private List<String> labelList;
    private List<String> categoryList;
    private ExpenseListView expenseListView;

    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;

    private String mode = "ADD";

    public Expense() {
        idList = new ArrayList<>();
        valueList = new ArrayList<>();
        labelList = new ArrayList<>();
        categoryList = new ArrayList<>();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
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
                intent.putExtra("mode", "ADD");
                mode = "ADD";
                startActivityForResult(intent, REQUEST_CODE_GET_EXPENSE);
            }
        });

        labelList.clear();
        valueList.clear();
        categoryList.clear();

        Cursor cursor = db.rawQuery("SELECT e.eid, e.amount, e.label, e.category FROM expenses e WHERE e.uid = ?", new String[]{ getArguments().getString("userId")});
        while (cursor.moveToNext()) {
            this.idList.add(cursor.getString(0));
            this.valueList.add(cursor.getString(1));
            this.labelList.add(cursor.getString(2));
            this.categoryList.add(cursor.getString(3));
        }

        ListView listView = view.findViewById(R.id.lvlistview1);
        expenseListView = new ExpenseListView(getActivity(), valueList, labelList, categoryList);
        listView.setAdapter(expenseListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), AddExpense.class);
                intent.putExtra("mode", "EDIT");
                intent.putExtra("id", idList.get(position));
                intent.putExtra("value", valueList.get(position));
                intent.putExtra("label", labelList.get(position));
                intent.putExtra("category", categoryList.get(position));
                intent.putExtra("position", String.valueOf(position));
                mode = "EDIT";
                startActivityForResult(intent, REQUEST_CODE_GET_EXPENSE);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE_GET_EXPENSE == requestCode && data != null) {
            String value, label, description, category, id;

            id = data.getStringExtra("id");
            value = data.getStringExtra("value");
            label = data.getStringExtra("label");
            category = data.getStringExtra("category");

            if(mode.equals("ADD")) {
                this.idList.add(id);
                this.valueList.add(value);
                this.labelList.add(label);
                this.categoryList.add(category);
            }
            else if(mode.equals("EDIT")) {
                int position = Integer.valueOf(data.getStringExtra("position"));
                idList.set(position, id);
                valueList.set(position, value);
                labelList.set(position, label);
                categoryList.set(position, category);
            }

            expenseListView.notifyDataSetChanged();
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
