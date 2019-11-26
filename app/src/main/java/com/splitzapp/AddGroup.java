package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddGroup extends AppCompatActivity {

    private Button confirm;
    private Button cancel;
    private EditText groupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        groupName = (EditText)findViewById(R.id.etGroupName);
        confirm = (Button)findViewById(R.id.btnConfirmGroup);
        cancel = (Button)findViewById(R.id.btnCloseGroup);

    }

    public void btnConfirmGroup(View view) {
        Intent i = new Intent();

        i.putExtra("groupname", groupName.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }

    public void btnCloseGroup(View view) {
        finish();
    }
}
