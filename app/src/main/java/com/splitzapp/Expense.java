package com.splitzapp;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Expense extends Fragment {
    private static final int REQUEST_CODE_GET_EXPENSE = 1;
    private String value;
    private String label;
    private String description;

    ListView lst;
    List<String> val;
    List<String> lab;
    List<String> desc;

    public Expense() {
        // Required empty public constructor
        val = new ArrayList<>();
        lab = new ArrayList<>();
        desc = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_expense, container, false);


        Button addExpense = (Button) view.findViewById(R.id.btnadd);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddExpense.class);
                startActivityForResult(intent, REQUEST_CODE_GET_EXPENSE);
            }
        });

        if (getArguments() != null) {
            value = getArguments().getString("value");
            label = getArguments().getString("label");
            description = getArguments().getString("description");

        }

//        lst = (ListView)view.findViewById(R.id.lvlistview);
//        CustomListView customListView = new CustomListView(getActivity(), val, lab, desc);
//        lst.setAdapter(customListView);

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // If the activity result was received from the "Get Car" request
        if (REQUEST_CODE_GET_EXPENSE == requestCode) {
            value = data.getStringExtra("value");
            label = data.getStringExtra("label");
            description = data.getStringExtra("description");
            val.add(value);
            lab.add(label);
            desc.add(description);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}
