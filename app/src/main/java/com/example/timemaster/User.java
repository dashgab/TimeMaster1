package com.example.timemaster;

import java.io.Serializable;

public class User implements Serializable {

    //private int id;
    private String mEmail;
    private String mPassword;
    private String mName;

    public User(String email, String password, String name) {
        mEmail = email;
        mPassword = password;
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
