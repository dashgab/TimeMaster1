package com.example.timemaster;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.timemaster.model.AppDatabase;
import com.example.timemaster.model.FolderDao;
import com.example.timemaster.model.FoldersData;
import com.example.timemaster.model.NoteDao;
//import com.example.timemaster.data.UserDao;

import java.util.Objects;
import java.util.concurrent.Executors;

import static com.example.timemaster.utils.Constants.CHANNEL_ID;

public class App extends Application {

    private AppDatabase database;
    private NoteDao noteDao;
    private FolderDao folderDao;
    //private UserDao userDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {//инициализация при старте процесса
        super.onCreate();

        if (instance == null) { instance = this;}

        createNotificationChannel();

        /*RoomDatabase.Callback rdc = new RoomDatabase.Callback() {
            public void onCreate (SupportSQLiteDatabase db) {
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        db.execSQL("INSERT INTO Folder VALUES(1, 1, \"Дом\", \"Желтый\")");
                    }
                });
            }
            public void onOpen (SupportSQLiteDatabase db) {
                // do something every time database is open
            }
        };*/

        instance = this;
        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "timemaster-db")
                .allowMainThreadQueries()//в бэграунд потоке сделать
                .fallbackToDestructiveMigration()
                .build();
        noteDao = database.noteDao();
        folderDao = database.folderDao();



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

    public FolderDao getFolderDao() {
        return folderDao;
    }

    public void setFolderDao(FolderDao folderDao) {
        this.folderDao = folderDao;
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
