package com.example.anita.renksayi_1;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anita on 12.3.2017.
 */

public class updateTipAdapter extends  ArrayAdapter<String>{

private final Activity context;
private final String[] web;
//private final Integer[] imageId;
public updateTipAdapter(Activity context,
                        String[] web, Integer[] imageId) {
        super(context, R.layout.update_gelir_gider, web);
        this.context = context;
        this.web = web;
        //this.imageId = imageId;

        }
@Override
public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.update_gelir_gider, null, true);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imageUpdateGelirGiderTipi);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textUpdateGelirGiderTipi);
    //txtTitle.setBackgroundResource(R.drawable.button_base);
        dimens textVD = new dimens();
       int screenHeight = textVD.getScreenHeight(context);
        int screenWidth = textVD.getScreenWidth(context);
        //imageView.setMaxWidth(screenWidth/20);
       // imageView.setMaxHeight(screenWidth/20);
       // txtTitle.setMaxWidth(screenHeight/20);
        //txtTitle.setMaxHeight(screenHeight/5);
    //txtTitle.setMaxWidth(screenHeight/50);
    txtTitle.setWidth(screenHeight/14);
    txtTitle.setHeight(screenHeight/14);
        int fortextVWidth = ((screenWidth * 9 / 10) / 4);
        int sabitSize = (int) Math.floor(fortextVWidth/ 250);
        int fortextSize = 12 + (sabitSize * 4);



        txtTitle.setText(web[position]);
    //txtTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    txtTitle.setTextSize(fortextSize*3/4);
       // imageView.setImageResource(imageId[position]);
        return rowView;
        }
        }
