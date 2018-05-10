package com.example.anita.renksayi_1;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by anita on 24.1.2018.
 */

public class veriListeleri extends AppCompatActivity{

    ArrayList<String> simpleSayiDizi;
    private HashMap<String,ArrayList> tumDiziler;

    String[] zorluklari;
    String[] tipleri;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        tumDiziler = new HashMap<String ,ArrayList>();

        zorluklari= getResources().getStringArray(R.array.zorluk);
        tipleri=getResources().getStringArray(R.array.tipler);

        for (int i=0;i<tipleri.length;i++){
            for (int k=0;k<zorluklari.length;k++){



            }

        }


    }


}
