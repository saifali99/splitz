package com.splitzapp;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    private String value;
    private String label;
    private String description;

//    ListView lst;
//    List<String> val = Arrays.asList("10", "20", "30");
//    List<String> lab = Arrays.asList("A", "B", "C");
//    List<String> desc = Arrays.asList("X", "Y", "Z");

    public Expense() {
        // Required empty public constructor
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
                startActivity(intent);
            }
        });

        if (getArguments() != null) {
            value = getArguments().getString("value");
            label = getArguments().getString("label");
            description = getArguments().getString("description");

//            val.add(value);
//            lab.add(label);
//            desc.add(description);
        }

//        lst = (ListView)view.findViewById(R.id.lvlistview);
//        CustomListView customListView = new CustomListView(getActivity(), val, lab, desc);
//        lst.setAdapter(customListView);

        return view;
    }

}
