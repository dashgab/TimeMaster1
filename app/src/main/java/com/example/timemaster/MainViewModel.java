package com.example.timemaster;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.timemaster.model.Note;
//import com.example.timemaster.model.User;

import java.util.List;

public class MainViewModel extends ViewModel {
    //доступ к данным

    private LiveData<List<Note>> noteLiveData = App.getInstance().getNoteDao().getAllLiveData();//список заметок

    public LiveData<List<Note>> getNoteLiveData() {
        return noteLiveData;
    }

    //private LiveData<List<User>> userLiveData = App.getInstance().getUserDao().getAllLiveData();//список заметок

    //public LiveData<List<User>> getUserLiveData() {
        //return userLiveData;
    //}


}
