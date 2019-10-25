package com.example.bookcenter.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.bookcenter.Entity.Book;

import java.util.List;
@Dao
public interface BookDAO {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book where id = :id")
    Book findByID(String id);

    @Query("SELECT COUNT(*) from book")
    int countBooks();

    @Insert
    void insert(Book book);

    @Insert
    void insertAll(Book... books);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);
}
