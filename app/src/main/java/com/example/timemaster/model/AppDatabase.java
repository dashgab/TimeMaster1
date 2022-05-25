package com.example.timemaster.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
//import com.example.timemaster.model.User;

@Database(entities = {Note.class, /*User.class*/}, version = 1, exportSchema = false)//не удалять
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    //public abstract UserDao userDao();
}
