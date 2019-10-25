package com.example.bookcenter.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookcenter.Entity.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where id = :id")
    User findByID(String id);

    @Query("SELECT COUNT(*) from user where username=:username and pwd=:pwd")
    int countUsers(String username, String pwd);

    @Insert
    void insert(User user);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

   @Update
    void update(User user);
}
