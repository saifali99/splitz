package com.splitzapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.splitzapp.R;
import com.splitzapp.activity.LoginActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText fullname;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText confirmPass;
    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullname = (EditText)findViewById(R.id.etFullname);
        username = (EditText)findViewById(R.id.etUsername);

        email = (EditText)findViewById(R.id.etEmail);

        password = (EditText)findViewById(R.id.etPassword);
        confirmPass = (EditText)findViewById(R.id.etConfirmPass);
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
    }

    public void btnRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

//        if(!isEmail(email)) {
//            email.setError("Invalid Email");

            String[] args = {username.getText().toString(), password.getText().toString()};
            db.execSQL("INSERT INTO users(username, password) VALUES (?, ?)", args);

            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
//        }
    }

/*    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }*/
}
