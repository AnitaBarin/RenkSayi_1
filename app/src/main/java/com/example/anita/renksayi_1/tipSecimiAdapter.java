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

public class tipSecimiAdapter extends  ArrayAdapter<String>{

private final Activity context1;
private final String[] web1;
private final Integer[] imageId1;
public tipSecimiAdapter(Activity context,
                        String[] web, Integer[] imageId) {
        super(context, R.layout.tip_secim, web);
        this.context1 = context;
        this.web1 = web;
        this.imageId1 = imageId;

        }
@Override
public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context1.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.tip_secim, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageUpdateGelirGiderTipi1);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textUpdateGelirGiderTipi1);
        dimens textVD = new dimens();
       int screenHeight = textVD.getScreenHeight(context1);
        int screenWidth = textVD.getScreenWidth(context1);
        imageView.setMaxWidth(screenWidth/20);
        imageView.setMaxHeight(screenWidth/20);
        txtTitle.setMinWidth(screenWidth/6);
        txtTitle.setMinHeight(screenWidth/6);
        int fortextVWidth = ((screenWidth * 9 / 10) / 4);
        int sabitSize = (int) Math.floor(fortextVWidth/ 250);
        int fortextSize = 12 + (sabitSize * 4);



        txtTitle.setText(web1[position]);
    txtTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    txtTitle.setTextSize(fortextSize);
        imageView.setImageResource(imageId1[position]);
        return rowView;
        }
        }
