package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.splitzapp.listview.UserListView;
import com.splitzapp.tab.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddGroup extends AppCompatActivity {

    private Button btnConfirm;
    private Button btnCancel;
    private EditText etGroupName;
    private Button btnAddusers;
    private EditText etPersonname;

    private List<String> personName;
    private UserListView userListView;

    public AddGroup() {
        personName = new ArrayList<>(Arrays.asList("Person1","Person2"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        etGroupName = findViewById(R.id.etGroupName);
        btnAddusers = findViewById(R.id.btnAddUsers);
        btnConfirm = findViewById(R.id.btnConfirmGroup);
        btnCancel = findViewById(R.id.btnCloseGroup);
        etPersonname = findViewById(R.id.etpersonname);

        ListView listView = findViewById(R.id.lvlistview3);
        userListView = new UserListView(this, personName);
        listView.setAdapter(userListView);
    }

    public void btnAddUsers(View view) {
        String personName = etPersonname.getText().toString();
        //save the personNames that user add in data with there groupname as etGroupname

        if (!personName.isEmpty()) {
            this.personName.add(personName);

            userListView.notifyDataSetChanged();
        }

        etPersonname.setText("");
    }

    public void btnConfirmGroup(View view) {
        Intent intent = new Intent();
        intent.putExtra("groupName", etGroupName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void btnCloseGroup(View view) {
        finish();
    }

}
