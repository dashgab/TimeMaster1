package com.example.timemaster.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Folder implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "id_user")
    public int id_user;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "color")
    public String color;

    public Folder() {

    }

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
}