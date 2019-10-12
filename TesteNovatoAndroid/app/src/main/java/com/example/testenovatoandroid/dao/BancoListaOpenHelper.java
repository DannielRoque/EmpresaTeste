package com.example.testenovatoandroid.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoListaOpenHelper extends SQLiteOpenHelper {

    public BancoListaOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String comando = "CREATE TABLE empresa(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT," +
                " sobrenome TEXT," +
                " datanascimento TEXT," +
                " cargo TEXT," +
                " nota INTEGER)";
        db.execSQL(comando);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
