package com.example.bookcenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookcenter.DB.AppDatabase;
import com.example.bookcenter.Entity.User;

public class RegisterActivity extends AppCompatActivity {
EditText username;
EditText pwd;
Button btnrRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText)findViewById(R.id.rusername);
        pwd = (EditText)findViewById(R.id.rpwd);
        btnrRegister = (Button)findViewById(R.id.btnrRegister);

        btnrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(username.getText().toString(), pwd.getText().toString());
                AppDatabase.getAppDatabase(RegisterActivity.this).userDAO().insert(user);
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(RegisterActivity.this,"Register Successful",Toast.LENGTH_LONG).show();


            }

        });

    }
}
