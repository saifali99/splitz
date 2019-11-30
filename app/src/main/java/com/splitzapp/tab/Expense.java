package com.splitzapp.tab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.splitzapp.AddExpense;
import com.splitzapp.listview.ExpenseListView;
import com.splitzapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expense extends Fragment {

    private static final int REQUEST_CODE_GET_EXPENSE = 1;
    private List<String> value;
    private List<String> label;
    private ExpenseListView expenseListView;

    public Expense() {
        value = new ArrayList<>(Arrays.asList("10","20","30"));
        label = new ArrayList<>(Arrays.asList("Label1","Label2","Label3"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense, container, false);

        Button addExpense = view.findViewById(R.id.btnadd);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddExpense.class);
                startActivityForResult(intent, REQUEST_CODE_GET_EXPENSE);
            }
        });

        ListView listView = view.findViewById(R.id.lvlistview1);
        expenseListView = new ExpenseListView(getActivity(), value, label);
        listView.setAdapter(expenseListView);

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE_GET_EXPENSE == requestCode && data != null) {
            String value, label, description;

            value = data.getStringExtra("value");
            label = data.getStringExtra("label");
            description = data.getStringExtra("description");
            this.value.add(value);
            this.label.add(label);

            expenseListView.notifyDataSetChanged();
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
