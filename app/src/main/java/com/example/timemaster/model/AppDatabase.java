package com.example.timemaster.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
//import com.example.timemaster.model.User;

@Database(entities = {Note.class, Folder.class/*User.class*/}, version = 4, exportSchema = false)//не удалять
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
    public abstract FolderDao folderDao();

    /*private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "dbmovie.db";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context) {
        if(sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        AppDatabase.DATABASE_NAME)
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                AppExecutors.getInstance().diskIO()
                                .execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        getInstance(context).folderDao().
                                                insertAll(FoldersData.populateFolderData());
                                    }
                                });
                            }
                        }).build();
            }
        }
        return sInstance;
    }*/
    //public abstract UserDao userDao();
}
