package com.splitzapp.tab;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splitzapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Expense2 extends Fragment {


    public Expense2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense2, container, false);
    }

}
