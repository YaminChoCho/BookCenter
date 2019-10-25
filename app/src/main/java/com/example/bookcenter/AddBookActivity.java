package com.example.bookcenter;

import android.content.Intent;
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
import com.example.bookcenter.Entity.Book;

public class AddBookActivity extends AppCompatActivity {
    ImageView image;
    EditText title;
    EditText author;
    EditText price;
    EditText summary;
    Button btnAdd;
    String imageName="";
    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        title = (EditText) findViewById(R.id.bookTitle);
        author = (EditText) findViewById(R.id.bookAuthor);
        price = (EditText) findViewById(R.id.bookPrice);
        summary = (EditText) findViewById(R.id.bookSummary);
        image = (ImageView) findViewById(R.id.bookImage);
        btnAdd = findViewById(R.id.btnAdd);

        //for image selecting
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        //for btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book;
               if(imageName==""){
                   imageName = "@drawable/add_book";
                   book = new Book(imageName, title.getText().toString(), author.getText().toString(), price.getText().toString(), summary.getText().toString());

               }
               else {
                   book = new Book(imageName, title.getText().toString(), author.getText().toString(), price.getText().toString(), summary.getText().toString());
               }
               AppDatabase.getAppDatabase(AddBookActivity.this).bookDAO().insert(book);
                Toast.makeText(AddBookActivity.this, "Inserted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddBookActivity.this, TabsActivity.class);
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
            ImageView imageView = (ImageView) findViewById(R.id.bookImage);
            Glide.with(this).load(uri).into(imageView);
        }
    }
}
