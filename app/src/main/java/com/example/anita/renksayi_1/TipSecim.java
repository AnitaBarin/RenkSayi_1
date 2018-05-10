package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class TipSecim extends AppCompatActivity {

        String intentTipiT="TipSecim";
        String gelenTipT;
        Integer[] TypeImageAl;
        String[]  TypeSayiAl;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            this.setContentView(R.layout.activity_main);
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            Intent intent=getIntent();
            gelenTipT=intent.getStringExtra("giden");





            Integer[] TypeImageAl={R.drawable.lineer,R.drawable.terslineer,R.drawable.diagonal,R.drawable.maxihelix,R.drawable.midihelix,R.drawable.minihelix};
            String[]   TypeSayiAl={"Lineer","Ters Lineer", "Diagonal","Maxi Helix", "Midi Helix", "Mini Helix"};

            Intent intentTipDonus = new Intent();
            intentTipDonus.putExtra("tipDonus", intentTipiT);

            intentTipDonus.putExtra("tipResim", TypeImageAl);
            intentTipDonus.putExtra("tipAd",TypeSayiAl);
            setResult(Activity.RESULT_OK, intentTipDonus);
            finish();

        }

    }









