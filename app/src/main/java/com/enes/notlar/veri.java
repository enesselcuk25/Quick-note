package com.enes.notlar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class veri extends SQLiteOpenHelper {


    public veri(@Nullable Context context) {
        super(context, "ekle.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE defter (defter_id INTEGER PRIMARY KEY AUTOINCREMENT,defter_ad TEXT,tarih TEXT ,baslik TEXT,saat TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS defter");
        onCreate(db);
    }
}
