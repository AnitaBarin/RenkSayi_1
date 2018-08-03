package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LangSec extends AppCompatActivity implements View.OnClickListener{
    ImageButton imageButtonTutor, imageButtonGame, imageButtonTrk, imageButtonEng;
    LinearLayout.LayoutParams buttonParam1, buttonParam2;
    int screenHeightL, screenWidthL;
    String gelen;
    String intentTipi;
    String langWhat="Trk";
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        rl=new RelativeLayout(this);
        rl= (RelativeLayout)findViewById(R.id.activity_main);

        /////////////-----------gelen intent bilgisi alma-------------
        Intent intentTut = getIntent();
        gelen = intentTut.getStringExtra("intentTipi");

        /////////---------------------------------dimension alma---------------------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeightL = textVD.getScreenHeight(this);
        screenWidthL = textVD.getScreenWidth(this);


        /////////////butonları ekleme

        imageButtonEng = new ImageButton(this);
        imageButtonTrk = new ImageButton(this);
        imageButtonEng.setImageResource(R.drawable.button_eng);
        imageButtonTrk.setImageResource(R.drawable.button_trk);
        imageButtonTrk.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageButtonEng.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int butWSize = screenWidthL / 5;
        int butHSize = butWSize / 4 * 3;
        buttonParam1 = new LinearLayout.LayoutParams(butWSize, butHSize);
        buttonParam2 = new LinearLayout.LayoutParams(butWSize, butHSize);

        buttonParam1.leftMargin = screenWidthL / 10;
        buttonParam1.topMargin = screenHeightL / 10;
        buttonParam2.leftMargin = screenWidthL / 10;
        buttonParam2.topMargin = screenHeightL - (screenHeightL / 10) - (screenHeightL / 10) - butHSize;
        imageButtonEng.setLayoutParams(buttonParam1);
        imageButtonTrk.setLayoutParams(buttonParam2);
        addContentView(imageButtonEng, buttonParam1);
        addContentView(imageButtonTrk, buttonParam2);
        int aaaa = imageButtonEng.getId();
        imageButtonEng.setOnClickListener(this);
        imageButtonTrk.setOnClickListener(this);
        imageButtonEng.setId(1);
        imageButtonEng.setId(2);
    }


    /////////////-------------button seçimi sonrasında seçilen dili alma
    public void onClick(View v) {
        int buttonNo=v.getId();
        if (imageButtonEng.getId()==buttonNo){
          langWhat="Eng";
        }
        if (imageButtonTrk.getId()==buttonNo){
           langWhat="Trk";
        }

        //////////////---------------  butonlar ekrandan silme-------------
        ViewGroup viewHolder = (ViewGroup)imageButtonEng.getParent();
        viewHolder.removeView(imageButtonEng);
        ViewGroup viewHolder1 = (ViewGroup)imageButtonTrk.getParent();
        viewHolder1.removeView(imageButtonTrk);
        if(((RelativeLayout)rl ).getChildCount() > 0)
        {((RelativeLayout) rl).removeAllViews();}

        ///////////------------dil seçimi sonrası dönüş
        Intent intentTutDonus = new Intent();
        intentTipi="Language";
        intentTutDonus.putExtra("tipDonus", intentTipi);
        intentTutDonus.putExtra("lang",langWhat);

        setResult(Activity.RESULT_OK, intentTutDonus);
        finish();
    }



    /////////BACK butonuna basıldığında  geri dönüş için-------------
    @Override
    public void onBackPressed() {


//////////////---------------  butonlar ekrandan silme-------------
        ViewGroup viewHolder = (ViewGroup)imageButtonEng.getParent();
        viewHolder.removeView(imageButtonEng);
        ViewGroup viewHolder1 = (ViewGroup)imageButtonTrk.getParent();
        viewHolder1.removeView(imageButtonTrk);

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
