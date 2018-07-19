package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class Tutor extends AppCompatActivity {

    String gelen;
    ViewFlipper tutorialFlip;
    int tutorialImages[];
    int tutorialSayi;
    private float initialX;
    LinearLayout.LayoutParams tutParams;
    String gelenTip;
    String intentTipi="TutorAc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        /////////////-----------gelen intent bilgisi alma-------------
        Intent intentTut=getIntent();
        gelen=intentTut.getStringExtra("intentTipi");


        //////////////--------------tutorial için resimleri alma-------------------
        tutorialFlip = new ViewFlipper(this);
        int tutorialImages[] = {R.drawable.tut01, R.drawable.tut02, R.drawable.tut03, R.drawable.tut04, R.drawable.tut05, R.drawable.tut06, R.drawable.tut07, R.drawable.tut08,
                R.drawable.tut09, R.drawable.tut10, R.drawable.tut11, R.drawable.tut12, R.drawable.tut13, R.drawable.tut14, R.drawable.tut15, R.drawable.tut16, R.drawable.tut17};
        tutorialSayi = tutorialImages.length;
        for (int k = 0; k < tutorialImages.length; k++) {
            setFlipperImage(tutorialImages[k]);
        }

        /////////////-------tutorial ekrana ekleme-------------
        tutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        addContentView(tutorialFlip, tutParams);

    }

    ///////////-------------tutorial resim ekleme programcığı--------------
    private void setFlipperImage(int res) {
        ImageView imgv=new ImageView(this);
        imgv.setBackgroundResource(res);
        tutorialFlip.addView(imgv);

    }

    /////////////---------------------tutorial için sayfa kaydırma--------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(android.R.menu.class, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (initialX > finalX) {
                    if (tutorialFlip.getDisplayedChild() == tutorialSayi-1)
                        break;

                    tutorialFlip.setInAnimation(this,R.anim.slide_in_left);
                    tutorialFlip.showNext();
                } else {
                    if (tutorialFlip.getDisplayedChild() == 0)
                        break;

                    tutorialFlip.setOutAnimation(this,R.anim.slide_out_right);
                    tutorialFlip.showPrevious();

                }
                break;
        }
        return false;

    }


    /////////BACK butonuna basıldığında  geri dönüş için-------------
    @Override
    public void onBackPressed() {
//////////////---------------  tutorial ekrandan silme-------------
        ViewGroup viewHolder = (ViewGroup)tutorialFlip.getParent();
        viewHolder.removeView(tutorialFlip);

        ///////////----------------intent geri dönüş------------
        Intent intentTutDonus = new Intent();
        intentTipi="TutorAc";
        intentTutDonus.putExtra("tipDonus", intentTipi);

        setResult(Activity.RESULT_OK, intentTutDonus);
        finish();


    }

}