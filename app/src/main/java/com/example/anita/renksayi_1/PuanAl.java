package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class PuanAl extends AppCompatActivity {

    private ArrayList<String> puanDetay;
    private HashMap<String,puanlar> puanTum;
    String DATABASE_NAME= "Midpoints.db";
    String gelenler;
    int screenHeightL, screenWidthL;
    String gelen;
    String intentTipi;
    String puan1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puan_al);

        /////////////-----------gelen intent bilgisi alma-------------
        Intent intentPuanlar = getIntent();
        gelenler = intentPuanlar.getStringExtra("intentTipi");

        /////////---------------------------------dimension alma---------------------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeightL = textVD.getScreenHeight(this);
        screenWidthL = textVD.getScreenWidth(this);


        //////////////////--------------puan alma işini başlatma----------
        puanDetay= new ArrayList<String>();
        puanTum= new HashMap<>();
        CreatePoint createPoint;
        SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        createPoint=new CreatePoint(this);
        createPoint.selectDb(db,puanDetay, puanTum);




        /////////////--------------intent dönüşü---------------
        Intent intentTutDonus = new Intent();
        intentTipi="Puan";
        puan1="Puan";
        intentTutDonus.putExtra("tipDonus", intentTipi);
        intentTutDonus.putExtra("lang",puan1);

        setResult(Activity.RESULT_OK, intentTutDonus);
        finish();
    }

    /////////BACK butonuna basıldığında  geri dönüş için-------------
    @Override
    public void onBackPressed() {


//////////////---------------  butonlar ekrandan silme-------------
        //ViewGroup viewHolder = (ViewGroup)imageButtonEng.getParent();
        //viewHolder.removeView(imageButtonEng);
        //ViewGroup viewHolder1 = (ViewGroup)imageButtonTrk.getParent();
        //viewHolder1.removeView(imageButtonTrk);

        ///////////----------------intent geri dönüş------------
        Intent intentTutDonus = new Intent(Intent.ACTION_MAIN);
        intentTutDonus.addCategory(Intent.CATEGORY_HOME);
        intentTutDonus.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //  intentTipi="Language";
        // intentTutDonus.putExtra("tipDonus", intentTipi);
        //intentTutDonus.putExtra("lang",langWhat);

        startActivity(intentTutDonus);
        // setResult(Activity.RESULT_OK, intentTutDonus);
        finish();
        System.exit(0);


    }

}
