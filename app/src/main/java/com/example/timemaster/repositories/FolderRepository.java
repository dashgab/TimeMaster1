/*package com.example.timemaster.repositories;

import androidx.lifecycle.LiveData;

import com.example.timemaster.App;
import com.example.timemaster.model.AppDatabase;
import com.example.timemaster.model.Folder;

import java.util.List;

public class FolderRepository {
    private LiveData<List<Folder>> folderList;

    //private LiveData<List<DrawerMenu>> drawerMenuList;

    public FolderRepository() {
        AppDatabase database = AppDatabase.getInstance(App.getInstance());

        folderList = database.folderDao().getAllLiveData();
        //drawerMenuList = database.folderDao().loadDrawerMenu();

    }

    public LiveData<List<Folder>> getFolderList() {return folderList;}

    /*public LiveData<List<DrawerMenu>> getDrawerMenuList() {
        return drawerMenuList;
    }
}*/
