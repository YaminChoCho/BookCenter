package com.example.bookcenter;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bookcenter.DB.AppDatabase;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText pwd;
    Button btnLogin;
    TextView tvRegister;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        username = (EditText)findViewById(R.id.username);
        pwd = (EditText)findViewById(R.id.pwd);
        tvRegister = (TextView)findViewById(R.id.tvRegister);

        //for login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = AppDatabase.getAppDatabase(MainActivity.this).userDAO().countUsers(username.getText().toString(), pwd.getText().toString());
                if (count>=1) {
                    Intent intent = new Intent(MainActivity.this, TabsActivity.class);
                    startActivity(intent);
                    //for sharePreference
                    pref = getSharedPreferences("user_detail",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",username.getText().toString());
                    editor.putString("password",pwd.getText().toString());
                    editor.putString("image","@drawable/add_person");
                    editor.putString("bookImage","@drawable/add_book");
                    editor.putBoolean("isLogged", true);
                    editor.commit();
                }

            }


        });
        //for register
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }


        });
    }
}
