package com.example.timemaster;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


import androidx.room.Room;

import com.example.timemaster.data.AppDatabase;
import com.example.timemaster.data.FolderDao;
import com.example.timemaster.data.NoteDao;
//import com.example.timemaster.data.UserDao;

import java.util.Objects;

import static com.example.timemaster.utils.Constants.CHANNEL_ID;

public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;
    //private UserDao userDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {//инициализация при старте процесса
        super.onCreate();
        createNotificationChannel();

        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "timemaster-db")
                .allowMainThreadQueries()//в бэграунд потоке сделать
                .build();
        noteDao = database.noteDao();
        //userDao = database.userDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public NoteDao getNoteDao() {
        return noteDao;
    }

    public void setNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    /*public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
*/

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CHANNEL_ID,
                    getResources().getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW);

            NotificationManager notificationManager = Objects.requireNonNull(
                    getSystemService(NotificationManager.class));

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
