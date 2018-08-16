package com.example.anita.renksayi_1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PuanSave extends AppCompatActivity {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Modpoints.db";
    String insSQL, upSQL,selSQL;
    String gelen,gelenSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /////////////-----------gelen intent bilgisi alma-------------
        Intent intentSave = getIntent();
        gelen = intentSave.getStringExtra("intentTipi");
        gelenSQL=intentSave.getStringExtra("insSql");
        SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

        //insSQL="insert into points (who, game,difficulty,right,wrong,time,point)  values ('ben','oyun',5,8,4,'25',70)";
        //upSQL="update points set right=5, wrong=2, time='50', point=200 where rowid=2";

        if (gelen.equals("Kayit")){
            insertDbS(db,gelenSQL);
        }

    }
    public  void insertDbS(SQLiteDatabase db, String insSQL){
        db.execSQL(insSQL);

    }
    public  void updateDbS(SQLiteDatabase db){
        db.execSQL(upSQL);

    }

    public void selectDbS(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery(selSQL, null);
    }

}
