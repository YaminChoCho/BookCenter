package com.example.bookcenter.DB;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.bookcenter.DAO.BookDAO;
import com.example.bookcenter.DAO.UserDAO;
import com.example.bookcenter.Entity.Book;
import com.example.bookcenter.Entity.User;

@Database(entities = {User.class, Book.class},exportSchema = false,version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDAO userDAO();
    public abstract BookDAO bookDAO(



    );

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-db")
                            // allow queries on the main thread.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
