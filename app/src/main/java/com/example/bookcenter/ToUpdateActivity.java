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

public class ToUpdateActivity extends AppCompatActivity {
    ImageView imageView;
    EditText tvTitle;
    EditText tvAuthor;
    EditText tvPrice;
    EditText tvSummary;
    Button btnUpdate;
    String imageName;

    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_update);

        imageView = findViewById(R.id.imageForUpdate);
        tvTitle = findViewById(R.id.titleForUpdate);
        tvAuthor = findViewById(R.id.authorForUpdate);
        tvPrice = findViewById(R.id.priceForUpdate);
        tvSummary = findViewById(R.id.summaryForUpdate);
        btnUpdate = findViewById(R.id.btnUpdated);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Book book = AppDatabase.getAppDatabase(ToUpdateActivity.this).bookDAO().findByID(bundle.getString("id"));
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor());
            tvPrice.setText(book.getPrice());
            tvSummary.setText(book.getSummary());
            Glide.with(this).load(Uri.parse(book.getImage())).into(imageView);
        }

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

                Book book = new Book(Integer.parseInt(bundle.getString("id")),imageName,tvTitle.getText().toString(),tvAuthor.getText().toString(),tvPrice.getText().toString(),tvSummary.getText().toString());
                AppDatabase.getAppDatabase(ToUpdateActivity.this).bookDAO().update(book);
                Toast.makeText(ToUpdateActivity.this, "Updated!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ToUpdateActivity.this, TabsActivity.class);
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
            ImageView imageView = (ImageView) findViewById(R.id.imageForUpdate);
            Glide.with(this).load(uri).into(imageView);



        }
    }
}
