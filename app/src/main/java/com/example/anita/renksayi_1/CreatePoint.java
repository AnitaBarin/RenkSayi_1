package com.example.anita.renksayi_1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class CreatePoint extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ModPoints";
    public static final String FIELD1 = "who";
    public static final String FIELD2 = "game";
    public static final String FIELD3 = "difficulty";
    public static final String FIELD4 = "right";
    public static final String FIELD5 = "wrong";
    public static final String FIELD6 = "time";
    public static final String FIELD7 = "point";
    private ArrayList<String> puanDetayAl;
    private HashMap<String,puanlar> puanTumAl;

    public CreatePoint(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE  IF NOT EXISTS `points` ( `rowid` INTEGER,`who` TEXT, `game` TEXT, `difficulty` INTEGER, `right` INTEGER, `wrong` INTEGER, `time` TEXT,`sure` INTEGER, `point` INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void selectDb(SQLiteDatabase db, ArrayList<String> puanDetayAl, HashMap<String,puanlar> puanTumAl){
        Cursor cursor = db.rawQuery("SELECT rowid, who, game, difficulty, right, wrong, time, point from points",null);
        //puanDetayAl= new ArrayList<String>();
        int a=cursor.getCount();
        while (cursor.moveToNext()) {
            puanDetayAl= new ArrayList<String>();
            puanlar puanlarAl= new puanlar();
            puanlarAl.setIdKim(cursor.getInt(0));
            puanlarAl.setKim(cursor.getString(1));
            puanlarAl.setOyun(cursor.getString(2));
            puanlarAl.setZorluk(cursor.getInt(3));
            puanlarAl.setDogru(cursor.getInt(4));
            puanlarAl.setHata(cursor.getInt(5));
            puanlarAl.setZaman(cursor.getString(6));
            puanlarAl.setSure(cursor.getInt(7));
            ////////////süreyi yazamıyorum........ bunu  time alanına eklemeliyim sanırım
            puanlarAl.setPuans(cursor.getInt(8));

            String   kim = cursor.getString(0);
            String   oyun = cursor.getString(1);

        }
    }

    public  void insertDb(SQLiteDatabase db){
        db.execSQL("INSERT INTO points (who, game,  point)VALUES ('yyy','OYUN',50)");

    }
}
