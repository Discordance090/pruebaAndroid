package com.Holass.chuteapp.models;

import android.content.Context;

import com.Holass.chuteapp.DataHelper;

public class User {
    int ID;
    DataHelper dh;
    String name;
    String lastName;
    String password;
    String eMail;
    int phoneNumber;

    public User(Context context, String name, String lastName, String password, String eMail, int phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        dh = new DataHelper(context,"usuarios", null, 1);
    }

    public void login(){
    }
}
