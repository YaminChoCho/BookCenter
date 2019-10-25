package com.example.bookcenter;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookcenter.DB.AppDatabase;
import com.example.bookcenter.Entity.Book;

import java.util.List;

public class ShowDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvTitle;
    TextView tvAuthor;
    TextView tvPrice;
    TextView tvSummary;
    Button btnDelete;
    Button btnUpdate;
    List<Book> listBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        imageView = findViewById(R.id.detailImage);
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvPrice = findViewById(R.id.tvPrice);
        tvSummary = findViewById(R.id.tvSummary);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Book book = AppDatabase.getAppDatabase(ShowDetailsActivity.this).bookDAO().findByID(bundle.getString("id"));
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor());
            tvPrice.setText(book.getPrice());
            tvSummary.setText(book.getSummary());
            if(book.getImage()!=null) {
                Glide.with(this).load(Uri.parse(book.getImage())).into(imageView);
            }
        }

        //for delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = AppDatabase.getAppDatabase(ShowDetailsActivity.this).bookDAO().findByID(bundle.getString("id"));
                AppDatabase.getAppDatabase(ShowDetailsActivity.this).bookDAO().delete(book);
                Toast.makeText(ShowDetailsActivity.this, "Deleted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShowDetailsActivity.this, TabsActivity.class);
                startActivity(intent);
            }

        });

        //for update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = AppDatabase.getAppDatabase(ShowDetailsActivity.this).bookDAO().findByID(bundle.getString("id"));

                Intent intent = new Intent(ShowDetailsActivity.this, ToUpdateActivity.class);
                intent.putExtra("id",book.getId()+"");
                startActivity(intent);
            }

        });




    }
}
