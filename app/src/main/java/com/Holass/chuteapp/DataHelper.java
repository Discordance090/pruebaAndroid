package com.Holass.chuteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY, name TEXT, lastname TEXT, password TEXT, email TEXT, phonenumber INTEGER)");
        db.execSQL("CREATE TABLE equipos (id INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE jugadores (id INTEGER PRIMARY KEY, user INT, team INT)");
        db.execSQL("CREATE TABLE canchas (id INTEGER PRIMARY KEY, name TEXT, address TEXT, lat DOUBLE, long DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS equipos");
        db.execSQL("DROP TABLE IF EXISTS jugadores");
        db.execSQL("DROP TABLE IF EXISTS canchas");
        db.execSQL("CREATE TABLE usuarios (id INTEGER PRIMARY KEY, name TEXT, lastname TEXT, password TEXT, email TEXT, phonenumber INTEGER)");
        db.execSQL("CREATE TABLE equipos (id INTEGER PRIMARY KEY, name TEXT)");
        db.execSQL("CREATE TABLE jugadores (id INTEGER PRIMARY KEY, user INT, team INT)");
        db.execSQL("CREATE TABLE canchas (id INTEGER PRIMARY KEY, name TEXT, address TEXT, lat DOUBLE, long DOUBLE)");
    }
}
