package com.example.anita.renksayi_1;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by anita on 16.3.2017.
 */

public class dimens {
    int dpHeight=0, dpWidth=0, density=0, screenHeight=0,screenWidth=0;
    WindowManager wm;
    Resources res;
    DisplayMetrics displaymetrics;


    public dimens(Context context) {
        getdisplayheightWidth(context);
        getDpHeight();
        getDensity();
        getDpWidth();
        getScreenHeight(context);
        getScreenWidth(context);

    }

    public dimens() {
        
    }

    void getdisplayheightWidth(Context context) {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
       density= (int)(Resources.getSystem().getDisplayMetrics().density);
        screenHeight = displaymetrics.heightPixels;
        dpHeight=screenHeight/density;
       // screenWidth = displaymetrics.widthPixels;
        dpWidth=screenWidth/density;
    }

    public int getDpHeight() {
        return dpHeight;
    }

    public int getDpWidth() {

        return dpWidth;
    }

    public int getDensity(){
    return density;}
    
    public int getScreenHeight(Context context){
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        return screenHeight;
    }
    
    public int getScreenWidth(Context context){
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        displaymetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        screenWidth = displaymetrics.widthPixels;
        return screenWidth;
    }
}

