package com.example.anita.renksayi_1;

import android.app.Activity;
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
    String gelen,tipiSorgu, gelenInsSQL, gelenMinSQL,gelenSaySQL,gelenUpSQL, intentDonus;
    int puanSay,puanRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        /////////////-----------gelen intent bilgisi alma-------------
        Intent intentSave = getIntent();
        gelen = intentSave.getStringExtra("intentTipi");
        gelenInsSQL=intentSave.getStringExtra("insSql");
        gelenMinSQL=intentSave.getStringExtra("minSql");
        gelenSaySQL=intentSave.getStringExtra("saySql");
        gelenUpSQL=intentSave.getStringExtra("upSql");
        SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);

      ///////////---- önce kayıt sayısı bulunacak
        tipiSorgu="Say";
        selectDbS(db,gelenSaySQL, tipiSorgu);
        //////----------kayıt sayısı 10 ise minimum puan değeri bulunur ve update edilir.
        if( puanSay==10){
            tipiSorgu="Min";
            selectDbS(db,gelenMinSQL,tipiSorgu);
            gelenUpSQL=gelenUpSQL+puanRow+"";
            updateDbS(db,gelenUpSQL);
        }
        ////////////------kayıt sayısı 10dan az ise yeni değerler insert edilir.
        if (puanSay<10){
            insertDbS(db,gelenInsSQL);
        }

    }


    public  void insertDbS(SQLiteDatabase db, String insSQL){
        db.execSQL(insSQL);

    }
    public  void updateDbS(SQLiteDatabase db,String upSQL){
        db.execSQL(upSQL);

    }

    public void selectDbS(SQLiteDatabase db, String selSQL, String tipSorgu) {
        Cursor cursor = db.rawQuery(selSQL, null);
        //////----kayıt sayısı bulunacak ise
        if (tipSorgu.equals("Say")){
            while (cursor.moveToNext()){
            puanSay=cursor.getInt(0);
            }
        }
        //////-------minimum puan bulunacak ise
        if (tipSorgu.equals("Min")){
                while (cursor.moveToNext()){
                    puanRow=cursor.getInt(0);
                    }
                }
        }

    @Override
    public void onBackPressed() {

        ///////////----------------intent geri dönüş------------
        Intent intentSaveDonus = new Intent();
        intentDonus="Save";
        intentSaveDonus.putExtra("tipDonus", intentDonus);
        setResult(Activity.RESULT_OK, intentSaveDonus);
        finish();

    }
}
