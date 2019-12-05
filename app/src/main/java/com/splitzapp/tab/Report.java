package com.splitzapp.tab;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.splitzapp.R;
import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.ReportLstView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Report extends Fragment {

    private List<String> valList;
    private List<String> catList;
    private ReportLstView reportLstView;

    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    public Report() {
        valList = new ArrayList<>();
        catList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbhelper = new DatabaseHelper(getContext());
        db = dbhelper.getWritableDatabase();

        View view = inflater.inflate(R.layout.fragment_report, container, false);

        catList.clear();
        valList.clear();

        Cursor cursor = db.rawQuery("SELECT e.category, SUM(e.amount) FROM expenses e WHERE e.uid = ? GROUP BY e.category", new String[]{ getArguments().getString("userId")});
        while (cursor.moveToNext()) {
            this.catList.add(cursor.getString(0));
            this.valList.add(cursor.getString(1));
        }

        ListView listView = view.findViewById(R.id.lvlistview5);
        reportLstView = new ReportLstView(getActivity(), valList, catList);
        listView.setAdapter(reportLstView);

        return view;
    }

}
