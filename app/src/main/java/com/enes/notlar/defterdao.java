package com.enes.notlar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class defterdao {


    public ArrayList<defterler> tumdefterler(veri vt){
        ArrayList<defterler> defterArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM defter ORDER BY  defter_id DESC ",null);

        while (c.moveToNext()){
            defterler defter = new defterler(c.getInt(c.getColumnIndex("defter_id")),
                    c.getString(c.getColumnIndex("defter_ad")),
                    c.getString(c.getColumnIndex("tarih")),
                    c.getString(c.getColumnIndex("baslik")),
                    c.getString(c.getColumnIndex("saat")));



            defterArrayList.add(defter);
        }
        return defterArrayList;

    }
    public ArrayList<defterler> kisiara(veri vt,String not){

        ArrayList<defterler> defterlerArrayList = new ArrayList<>();

        SQLiteDatabase data = vt.getWritableDatabase();

        Cursor c = data.rawQuery("SELECT * FROM defter WHERE baslik like '%"+not+"%' ",null);

        while (c.moveToNext()){
            defterler defter = new defterler(c.getInt(c.getColumnIndex("defter_id")),
                    c.getString(c.getColumnIndex("defter_ad")),
                    c.getString(c.getColumnIndex("tarih")),
                    c.getString(c.getColumnIndex("baslik")),
                    c.getString(c.getColumnIndex("saat")));



            defterlerArrayList.add(defter);
        }
        data.close();
        return defterlerArrayList;




    }





    public void sil(veri vt,int defter_id){
        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("defter","defter_id=?",new String[]{String.valueOf(defter_id)});
    }

    public void ekle(veri vt,String defter_ad,String tarih,String baslik,String saat){
        SQLiteDatabase data = vt.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("defter_ad",defter_ad);
        values.put("tarih",tarih);
        values.put("baslik",baslik);
        values.put("saat",saat);



        data.insertOrThrow("defter",null,values);

        data.close();
    }

    public void duzenle(veri vt ,int defter_id ,String defter_ad,String tarih,String baslik,String saat){
        SQLiteDatabase db = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("defter_ad",defter_ad);
        values.put("tarih",tarih);
        values.put("baslik",baslik);
        values.put("saat",saat);
;


        db.update("defter",values,"defter_id=?",new String[]{String.valueOf(defter_id)});
        db.close();
    }
}
