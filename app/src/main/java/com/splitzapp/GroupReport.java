package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.splitzapp.listview.GroupReportListView;

import java.util.ArrayList;
import java.util.List;

public class GroupReport extends AppCompatActivity {

    String groupName;
    String groupId;
    List<String> username;
    List<String> positive;
    List<String> negative;
    GroupReportListView groupReportListView;

    public GroupReport() {
        username = new ArrayList<>();
        positive = new ArrayList<>();
        negative = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_report);

        TextView tvGroupName = findViewById(R.id.tvReportGroupName);
        groupName = getIntent().getStringExtra("groupName");
        groupId = getIntent().getStringExtra("groupId");

        tvGroupName.setText(groupName);

        ListView listView = findViewById(R.id.lvlistview6);
        groupReportListView = new GroupReportListView(this, username, positive, negative);
        listView.setAdapter(groupReportListView);
    }

    public void btnCloseReport(View view) {
        finish();
    }
}
