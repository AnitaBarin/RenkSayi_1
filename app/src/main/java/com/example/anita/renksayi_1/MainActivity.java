package com.example.anita.renksayi_1;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.max;

public class MainActivity extends AppCompatActivity {

    GridView gridviewBeginner;
    LinearLayout linearLayoutForGrid;
    LinearLayout.LayoutParams linearLayoutForGridParams;
    int beginnerColumnNum, beginnerRowNum;
    int screenWidth, screenHeight;
    int minSayi=1;
    int maxSayi=4;
    int randomSayi;
    Integer[] imageUpdateGelirGider={R.drawable.button_base};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        randomSayi = getRandomSayi(minSayi,maxSayi);

        ///layout tanımlama
        linearLayoutForGrid= new LinearLayout(this);

        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeight = textVD.getScreenHeight(this);
        screenWidth = textVD.getScreenWidth(this);

        /////grid  için alt ekran ve parametre tanımlama
        linearLayoutForGridParams = new LinearLayout.LayoutParams(screenWidth/2, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutForGrid.setBackgroundResource(R.mipmap.back_date_paper);
        linearLayoutForGrid.setOrientation(LinearLayout.HORIZONTAL);
        addContentView(linearLayoutForGrid, linearLayoutForGridParams);

        ////grid için satı ve sütün sayısı tanımlama
        beginnerColumnNum=4;
        beginnerRowNum=4;

        ///////gridi dolduracak verilerin tanımlanması
        final String[] gelirGiderTipleri = {"1","2","3","4","8","7","6","5","9","10","11","12","16","15","14","13"};


        ////grid tanımlama, grid parametre tanımlama
        gridviewBeginner= new GridView(this);
        gridviewBeginner.setNumColumns(beginnerColumnNum);
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //////grid için adapter çalıştırma
        adaptForGrid(gridviewBeginner, gelirGiderTipleri, imageUpdateGelirGider);


        ///// gridi ekrana eklenmesi
        linearLayoutForGrid.addView(gridviewBeginner, listParams);

        /////grid için clicklistener eklenmesi
        gridviewBeginner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int updatePosition=(int)id;

                int sayim =Integer.valueOf(gelirGiderTipleri[updatePosition]);

                sayiModuBul mod=new sayiModuBul(sayim);
                int modum=mod.getSayiMod();

                String textToast = "İşaretlediğiniz "+position+ "  numaralı satırdaki  "+modum+" kayıt güncelleme için"+randomSayi+" gösterilecektir";
                Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }


    //////adapter çağırma
    public void adaptForGrid(GridView gridViewUpdatealttip, String[] gelirGiderAltTipleri, Integer[] gelirGiderAltTipImages) {
        updateTipAdapter adapter = new
                updateTipAdapter(MainActivity.this, gelirGiderAltTipleri, gelirGiderAltTipImages);
        // listViewUpdate = new ListView(this);
        gridViewUpdatealttip.setAdapter(adapter);
    }

    //////mod4 için random sayı üretme
    private int getRandomSayi(int minSayiG,int maxSayiG){
        int randomSayiG;
        Random random=new Random();
        randomSayiG = random.nextInt(maxSayiG-minSayiG+1)+minSayiG;
        return randomSayiG;
    }


}
