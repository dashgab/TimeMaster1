package com.example.timemaster;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.timemaster.model.Folder;
import com.example.timemaster.model.Note;
//import com.example.timemaster.repositories.FolderRepository;

import java.util.List;

public class FolderViewModel extends ViewModel {


    /*private FolderRepository folderRepository;
    private LiveData<List<Folder>> mFolderList;

    public FolderViewModel() {
        folderRepository = new FolderRepository();
        mFolderList = folderRepository.getFolderList();
    }

    public LiveData<List<Folder>> getAllFolders() {
        return mFolderList;
    }

     */


    private LiveData<List<Folder>> folderLiveData = App.getInstance().getFolderDao().getAllLiveData();//список заметок

    public LiveData<List<Folder>> getFolderLiveData() {
        return folderLiveData;
    }

}
