package com.splitzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText vUsername;
    private EditText vPassword;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vUsername = (EditText)findViewById(R.id.etVusername);
        vPassword = (EditText)findViewById(R.id.etVpassword);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnRegister);
    }

    public void btnRegister(View view) {
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }

    public void btnLogin(View view) {
        String username = getIntent().getStringExtra("rUsername");
        String password = getIntent().getStringExtra("rPassword");

//        if(username.equals(vUsername.getText().toString()) && password.equals(vPassword.getText().toString())) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//        }
    }

}
