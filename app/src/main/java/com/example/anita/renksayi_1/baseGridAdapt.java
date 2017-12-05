package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by anita on 30.8.2017.
 */

/*kullanmıyorum 10/9/2017*/
    /*kullanmıyorum 10/9/2017*/
    /*kullanmıyorum 10/9/2017*/
    /*kullanmıyorum 10/9/2017*/
    /*kullanmıyorum 10/9/2017*/
public class baseGridAdapt extends BaseAdapter {

    private final Activity context;
    private final String[] gelirGiderTipleri ;
    public baseGridAdapt(Activity context,
                         String[] gelirGiderTipleri) {
        this.context = context;
        this.gelirGiderTipleri = gelirGiderTipleri;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        TextView txtTitle = new TextView(context) ;
        //txtTitle.setBackgroundResource(R.drawable.button_base);
        dimens textVD = new dimens();
        int screenHeight = textVD.getScreenHeight(context);
        int screenWidth = textVD.getScreenWidth(context);
        //imageView.setMaxWidth(screenWidth/20);
        // imageView.setMaxHeight(screenWidth/20);
    // txtTitle.setMaxWidth(screenHeight/20);
      //  txtTitle.setMaxHeight(screenHeight/5);
        int fortextVWidth = ((screenWidth * 9 / 10) / 4);
        int sabitSize = (int) Math.floor(fortextVWidth/ 250);
        int fortextSize = 12 + (sabitSize * 4);



        txtTitle.setText(gelirGiderTipleri[position]);
       // txtTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        //txtTitle.setTextSize(fortextSize*2);
        // imageView.setImageResource(imageId[position]);
        return txtTitle;
    }
    }

