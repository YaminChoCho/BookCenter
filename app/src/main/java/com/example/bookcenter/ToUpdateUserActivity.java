package com.example.bookcenter;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookcenter.DB.AppDatabase;
import com.example.bookcenter.Entity.User;

public class ToUpdateUserActivity extends AppCompatActivity {
    ImageView imageView;
    EditText u;
    EditText p;
    Button btnUpdate;
    String imageName;
    Button btnLogout;
    SharedPreferences prf;
    ImageView im;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_update_user);

        imageView =  findViewById(R.id.editImage);
        u =  findViewById(R.id.editName);
        p =  findViewById(R.id.editPwd);
        //im = findViewById(R.id.editImage);
        btnUpdate =  findViewById(R.id.btnUpdatedProfile);




            //for sharePreference
            prf = getSharedPreferences("user_detail", MODE_PRIVATE);
            String name = prf.getString("username", "Your name");
            String pwd = prf.getString("password", "Your password");
            String image = prf.getString("image","Image");
            u.setText(name);
            p.setText(pwd);
          // Glide.with(this).load(Uri.parse(image)).into(imageView);

            //for image selecting
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = prf.edit();
                    editor.putString("username",u.getText().toString());
                    editor.putString("password",p.getText().toString());
                    editor.putString("image",imageName);
                    editor.commit();

                    User user = new User(u.getText().toString(), p.getText().toString(), imageName);

                    AppDatabase.getAppDatabase(ToUpdateUserActivity.this).userDAO().update(user);
                    Toast.makeText(ToUpdateUserActivity.this, "Updated!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ToUpdateUserActivity.this, TabsActivity.class);
                    startActivity(intent);
                }

            });


        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            //data.getData return the content URI for the selected Image
            Uri uri = data.getData();
            imageName = String.valueOf(uri);
            Log.i("hahahahahha", imageName);
            ImageView imageView = (ImageView) findViewById(R.id.editImage);
            Glide.with(this).load(uri).into(imageView);
        }
    }
}
