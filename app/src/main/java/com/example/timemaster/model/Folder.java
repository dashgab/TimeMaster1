package com.example.timemaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Folder {
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Folder(int uid, int id_user, String name, String color) {
        this.uid = uid;
        this.id_user = id_user;
        this.name = name;
        this.color = color;
    }

    public Folder() {

    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "id_user")
    public int id_user;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "color")
    public String color;






}

/*@Entity
public class Folder implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "id_user")
    public int id_user;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "color")
    public String color;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Folder folder = (Folder) o;
        return uid == folder.uid && id_user == folder.id_user && Objects.equals(name, folder.name) && Objects.equals(color, folder.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, id_user, name, color);
    }

    protected Folder(Parcel in) {
        uid = in.readInt();
        id_user = in.readInt();
        name = in.readString();
        color = in.readString();
    }

    public static final Creator<Folder> CREATOR = new Creator<Folder>() {
        @Override
        public Folder createFromParcel(Parcel in) {
            return new Folder(in);
        }

        @Override
        public Folder[] newArray(int size) {
            return new Folder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(uid);
        parcel.writeInt(id_user);
        parcel.writeString(name);
        parcel.writeString(color);
    }
}*/