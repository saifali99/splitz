package com.splitzapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.splitzapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText vUsername;
    private EditText vPassword;
    private Button login;
    private Button register;
    public DatabaseHelper dbhelper;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        vUsername = (EditText)findViewById(R.id.etVusername);
        vPassword = (EditText)findViewById(R.id.etVpassword);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnRegister);
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
    }

    public void btnRegister(View view) {
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }

    public void btnLogin(View view) {
        Cursor cursor;
        String m_argv[] = {vUsername.getText().toString(), vPassword.getText().toString()};
        cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", m_argv);
        cursor.moveToFirst();

        //if(username.equals(vUsername.getText().toString()) && password.equals(vPassword.getText().toString())) {
        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            i.putExtra("userId", cursor.getString(0));
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"Username or password incorrect",Toast.LENGTH_SHORT).show();
        }
    //    }
    }

}
