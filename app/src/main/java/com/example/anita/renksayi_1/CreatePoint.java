package com.example.anita.renksayi_1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import java.util.ArrayList;
import java.util.HashMap;

public class CreatePoint extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Modpoints";
    public static final String FIELD1 = "who";
    public static final String FIELD2 = "game";
    public static final String FIELD3 = "difficulty";
    public static final String FIELD4 = "right";
    public static final String FIELD5 = "wrong";
    public static final String FIELD6 = "time";
    public static final String FIELD7 = "point";
    private ArrayList<puanlar> puanDetayAl;
    private HashMap<String,puanlar> puanTumAl;
    String gelentip, gelenSql;

    public CreatePoint   (Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE  IF NOT EXISTS `points` ( `rowid` INTEGER,`who` TEXT, `game` TEXT, `difficulty` INTEGER, `right` INTEGER, `wrong` INTEGER, `time` TEXT,`sure` INTEGER, `point` INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

   /* public void selectDb(SQLiteDatabase db, ArrayList<puanlar> puanDetayAl, HashMap<String,puanlar> puanTumAl){
        ////SQLiteDatabase db, ArrayList<puanlar> puanDetayAl, HashMap<String,puanlar> puanTumAl
        Cursor cursor = db.rawQuery("SELECT  who, game, difficulty, right, wrong, time, point from points",null);
        //puanDetayAl= new ArrayList<String>();
        int a=cursor.getCount();
        puanDetayAl= new ArrayList<puanlar>();
        int sira;
        sira=0;
        while (cursor.moveToNext()) {

            puanlar puanlarAl= new puanlar();
            puanlarAl.setIdKim(0);
            puanlarAl.setKim(cursor.getString(0));
            puanlarAl.setOyun(cursor.getString(1));
            puanlarAl.setZorluk(cursor.getInt(2));
            puanlarAl.setDogru(cursor.getInt(3));
            puanlarAl.setHata(cursor.getInt(4));
            puanlarAl.setZaman(cursor.getString(5));
            puanlarAl.setSure(0);
            ////////////süreyi yazamıyorum........ bunu  time alanına eklemeliyim sanırım
            puanlarAl.setPuans(cursor.getInt(6));
            puanDetayAl.add(sira,puanlarAl);
            puanlarAl=null;
            sira=sira+1;
        }
    }*/

    public  void insertDb(SQLiteDatabase db){
       db.execSQL("INSERT INTO points ( game,difficulty,  point)VALUES ('OYUND',4,80)");

    }
}
