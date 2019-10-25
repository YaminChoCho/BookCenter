package com.example.bookcenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.bookcenter.Entity.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context mContext;
    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {

        this.bookList = bookList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView author;
        public ImageView book_image;

        private Book book;

        public MyViewHolder(View view) {
            super(view);

            author = (TextView) view.findViewById(R.id.author);
            book_image= (ImageView) view.findViewById(R.id.book_image);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Click on "+book.getAuthor(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, ShowDetailsActivity.class);
                    intent.putExtra("id",book.getId()+"");
                    mContext.startActivity(intent);

                }
            });
        }

        public void bindData(Book book) {

            this.book = book;
            author.setText(book.getAuthor());
            String b="";
            b = book.getImage();
            if(b !=null) {
                if(b==""){
                   // Glide.with(mContext).load("@drawable/add_book").into(book_image);

                }
            // loading album cover using Glide library
            Uri uri = Uri.parse(book.getImage());
                Glide.with(mContext).load(book.getImage()).into(book_image);
            }

        }
    }

    public BookAdapter(Context mContext, List<Book> bookList) {
        this.mContext = mContext;
        this.bookList = bookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_book_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bindData(book);
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }
}

