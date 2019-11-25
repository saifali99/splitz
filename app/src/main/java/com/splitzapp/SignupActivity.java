package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText fullname;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname = (EditText)findViewById(R.id.etFullname);
        username = (EditText)findViewById(R.id.etUsername);
        email = (EditText)findViewById(R.id.etEmail);
        password = (EditText)findViewById(R.id.etPassword);
        confirmPass = (EditText)findViewById(R.id.etConfirmPass);
    }

    public void btnRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("rUsername", username.getText().toString());
        intent.putExtra("rPassword", password.getText().toString());
        startActivity(intent);
    }
}
