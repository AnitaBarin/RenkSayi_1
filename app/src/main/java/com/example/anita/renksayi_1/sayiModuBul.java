package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;

import static java.lang.Math.ceil;

/**
 * Created by anita on 11.10.2017.
 */

public class sayiModuBul {

    private Activity context;
    private int sayiMod=0;
    private final int gelenId;
    public sayiModuBul( int gelenId){


        this.gelenId=gelenId;
        getSayiMod();

    }



    public int getSayiMod() {
        sayiMod= (int) (gelenId - (ceil(gelenId/4))*4);
        return sayiMod;
    }
}
