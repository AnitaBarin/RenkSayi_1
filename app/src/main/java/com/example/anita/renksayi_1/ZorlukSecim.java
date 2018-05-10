package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

public class ZorlukSecim extends AppCompatActivity {
    String intentTipi="ZorlukSecim";
    String gelenTip;
    Integer[] zorlukImageAl;
    String[]  zorlukSayiAl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent=getIntent();
        gelenTip=intent.getStringExtra("giden");





        Integer[] zorlukImageAl={R.drawable.sifir,R.drawable.bir,R.drawable.iki,R.drawable.uc,R.drawable.dort, R.drawable.bes};

        Intent intentTipDonus = new Intent();
        intentTipDonus.putExtra("tipDonus", intentTipi);

        intentTipDonus.putExtra("zorluklar", zorlukImageAl);
        setResult(Activity.RESULT_OK, intentTipDonus);
        finish();

    }

}
