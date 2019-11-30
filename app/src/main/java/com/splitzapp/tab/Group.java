package com.splitzapp.tab;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.splitzapp.AddGroup;
import com.splitzapp.GroupInfo;
import com.splitzapp.activity.DatabaseHelper;
import com.splitzapp.listview.GroupListView;
import com.splitzapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group extends Fragment {

    private static final int REQUEST_CODE_GET_EXPENSE = 1;
    private List<String> groupName;
    private List<String> groupId;
    private GroupListView groupListView;


    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    public Group() {
        groupName = new ArrayList<>();
        groupId = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_group, container, false);

        Button addGroup = (Button)view.findViewById(R.id.btnaddgroup);
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddGroup.class);
                startActivityForResult(intent, REQUEST_CODE_GET_EXPENSE);
            }
        });

        dbhelper = new DatabaseHelper(getContext());
        db = dbhelper.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT g.* from groups g, groupUsers gu, users u WHERE g.gid = gu.gid AND u.uid = ?", new String[]{this.getArguments().getString("userId", "null")});
        for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            groupName.add(res.getString(1));
            groupId.add(res.getString(0));
        }

        ListView listView = view.findViewById(R.id.lvlistview2);
        groupListView = new GroupListView(getActivity(), groupName);
        listView.setAdapter(groupListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), GroupInfo.class);
                intent.putExtra("groupName", groupName.get(position));
                intent.putExtra("groupId", groupId.get(position));
                startActivity(intent);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE_GET_EXPENSE == requestCode && data != null) {
            String groupname;
            String groupId;
            groupname = data.getStringExtra("groupName");
            groupId = data.getStringExtra("groupId");

            this.groupName.add(groupname);
            this.groupId.add(groupId);
            groupListView.notifyDataSetChanged();
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}
