package com.example.timemaster.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timemaster.model.Folder;


import java.util.List;

@Dao
public interface FolderDao {

    @Query("SELECT * FROM Folder")
    List<Folder> getAll();

    @Query("SELECT * FROM Folder")
    LiveData<List<Folder>> getAllLiveData();//бновлять список с данными легко

    //возврат списка с определенными айди
    @Query("SELECT * FROM Note WHERE uid IN (:folderIds)")
    List<Folder> loadAllByIds(int[] folderIds);

    @Query("SELECT * FROM Note WHERE uid = :uid LIMIT 1")
    Folder findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)//если захочу вставить в бд заметку с id, которая существует, будет поизведена замена сущености на новую
    void insert(Folder folder);

    @Update
    void update(Folder folder);

    @Delete
    void delete(Folder folder);

}

