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
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class IsSec extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButtonTutor, imageButtonGame, imageButtonPoint;
    LinearLayout.LayoutParams buttonParam1, buttonParam2, buttonParam3;
    int screenHeightI, screenWidthI;
    String gelen;
    String intentTipi;
    String dilSec;
    String isWhat="Oyun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /////////////-----------gelen intent bilgisi alma-------------
        Intent intentIsi = getIntent();
        gelen = intentIsi.getStringExtra("intentTipi");
        dilSec=intentIsi.getStringExtra("dilim");

        /////////---------------------------------dimension alma---------------------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeightI = textVD.getScreenHeight(this);
        screenWidthI = textVD.getScreenWidth(this);

        /////////////butonları ekleme

        imageButtonGame= new ImageButton(this);
        imageButtonTutor = new ImageButton(this);
        imageButtonPoint= new ImageButton(this);
        if (dilSec.equals("Eng")){
        imageButtonGame.setImageResource(R.drawable.button_game);
        imageButtonTutor.setImageResource(R.drawable.button_tutor);}
        imageButtonPoint.setImageResource(R.drawable.buttonpoint);
        if (dilSec.equals("Trk")){
            imageButtonGame.setImageResource(R.drawable.button_gametrk);
            imageButtonTutor.setImageResource(R.drawable.button_tutortrk);}
        imageButtonPoint.setImageResource(R.drawable.buttonpuan);

        imageButtonGame.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageButtonTutor.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageButtonPoint.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int butWSize = screenWidthI / 5;
        int butHSize = butWSize / 4 * 3;
        buttonParam1 = new LinearLayout.LayoutParams(butWSize, butHSize);
        buttonParam2 = new LinearLayout.LayoutParams(butWSize, butHSize);
        buttonParam3= new LinearLayout.LayoutParams(butWSize,butHSize);

        buttonParam1.leftMargin = screenWidthI / 10;
        buttonParam1.topMargin = screenHeightI / 10;
        buttonParam2.leftMargin = screenWidthI / 10;
        buttonParam2.topMargin = screenHeightI - (screenHeightI / 10) - (screenHeightI / 10) - butHSize;
        buttonParam3.rightMargin=screenWidthI/10;
        buttonParam3.topMargin=(screenHeightI/2) -butHSize;
        imageButtonGame.setLayoutParams(buttonParam1);
        imageButtonTutor.setLayoutParams(buttonParam2);
        imageButtonPoint.setLayoutParams(buttonParam3);
        addContentView(imageButtonGame, buttonParam1);
        addContentView(imageButtonTutor, buttonParam2);
        addContentView(imageButtonPoint,buttonParam3);
        imageButtonGame.setOnClickListener(this);
        imageButtonTutor.setOnClickListener(this);
        imageButtonPoint.setOnClickListener(this);
        imageButtonTutor.setId(1);
        imageButtonGame.setId(2);
        imageButtonPoint.setId(3);

    }



    @Override
    public void onClick(View v) {

        int buttonNo=v.getId();
        if (imageButtonGame.getId()==buttonNo){
            isWhat="Oyun";
        }
        if (imageButtonTutor.getId()==buttonNo){
            isWhat="Tutor";
        }
        if (imageButtonPoint.getId()==buttonNo){
            isWhat="Puan";
        }


        //////////////---------------  butonlar ekrandan silme-------------
        ViewGroup viewHolder = (ViewGroup)imageButtonGame.getParent();
        viewHolder.removeView(imageButtonGame);
        ViewGroup viewHolder1 = (ViewGroup)imageButtonTutor.getParent();
        viewHolder1.removeView(imageButtonTutor);

        ///////////------------dil seçimi sonrası dönüş
        Intent intentIsDonus = new Intent();
        intentTipi="Is";
        intentIsDonus.putExtra("tipDonus", intentTipi);
        intentIsDonus.putExtra("is",isWhat);

        setResult(Activity.RESULT_OK, intentIsDonus);
        finish();

    }


    /////////BACK butonuna basıldığında  geri dönüş için-------------
   @Override
    public void onBackPressed() {
//////////////---------------  butonlar ekrandan silme-------------
        ViewGroup viewHolder = (ViewGroup)imageButtonGame.getParent();
        viewHolder.removeView(imageButtonGame);
        ViewGroup viewHolder1 = (ViewGroup)imageButtonTutor.getParent();
        viewHolder1.removeView(imageButtonTutor);
       ViewGroup viewHolder2 = (ViewGroup)imageButtonPoint.getParent();
       viewHolder2.removeView(imageButtonPoint);

        ///////////----------------intent geri dönüş------------
        Intent intentIsDonus = new Intent();
        intentTipi="Is";
        intentIsDonus.putExtra("tipDonus", intentTipi);
        intentIsDonus.putExtra("is",isWhat);

        setResult(Activity.RESULT_OK, intentIsDonus);
        finish();


    }
}
