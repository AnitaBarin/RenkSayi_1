package com.example.anita.renksayi_1;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.max;
import static android.R.attr.wallpaperCloseEnterAnimation;
import static android.R.id.button1;

public class MainActivity extends AppCompatActivity {

    GridView gridviewBeginner;
    LinearLayout linearLayoutBase,linearLayoutForGrid, linearLayoutRandomSayi, linearLayoutWrongCountText,linearLayoutRightCountText;
    RelativeLayout.LayoutParams baseLayoutParams;
    LinearLayout.LayoutParams linearLayoutForGridParams,linearLayoutRandomSayiParams, textRandomParams,linearLayoutRightWrongParams,RightWrongTextImageParams;
    int beginnerColumnNum, beginnerRowNum;
    int screenWidth, screenHeight;
    int minSayi=1;
    int maxSayi=4;
    int randomSayi;
    int rightCount=0;
    int wrongCount=0;
    int gridLeft, gridHeight,gridWidth,boxLeft,boxHeight,boxWidth,betweenTwo;
    Integer[] imageRandomSayi={R.drawable.button_base};
    TextView textViewRandom, textViewWrongText,textViewWrongCount,textViewRightText,textViewRightCount;
    ImageView imageViewWrong,imageViewRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        randomSayi = getRandomSayi(minSayi, maxSayi);


        ///layout tanımlama
        linearLayoutBase = new LinearLayout(this);
        linearLayoutForGrid = new LinearLayout(this);
        linearLayoutRandomSayi = new LinearLayout(this);
        linearLayoutRandomSayi.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRightCountText = new LinearLayout(this);
        linearLayoutWrongCountText = new LinearLayout(this);


        baseLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutBase.setLayoutParams(baseLayoutParams);
        addContentView(linearLayoutBase, baseLayoutParams);


        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeight = textVD.getScreenHeight(this);
        screenWidth = textVD.getScreenWidth(this);

        ///grid ve kutu için size tanımlama
        gridLeft = screenWidth / 20;
        gridHeight = (screenHeight / 10) * 9;
        gridWidth = gridHeight;
        boxLeft = 0;
        boxHeight = (screenHeight / 10) * 6;
        boxWidth = boxHeight;
        betweenTwo = screenHeight / 20;

        /////grid  için alt ekran ve parametre tanımlama
        linearLayoutForGridParams = new LinearLayout.LayoutParams(gridWidth, gridHeight);
        linearLayoutForGrid.setBackgroundResource(R.mipmap.back_date_paper);
        linearLayoutForGrid.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutForGridParams.leftMargin = gridLeft;
        linearLayoutForGrid.setId(1);
        linearLayoutBase.addView(linearLayoutForGrid, linearLayoutForGridParams);

        ///randomsayının gösterimi için alt ekran ve parametre
        linearLayoutRandomSayiParams = new LinearLayout.LayoutParams(boxWidth, screenHeight);
        linearLayoutRandomSayi.setBackgroundResource(R.mipmap.back_butce_paper);
        linearLayoutRandomSayi.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRandomSayiParams.leftMargin = betweenTwo;
        linearLayoutRandomSayi.setLayoutParams(linearLayoutRandomSayiParams);
        linearLayoutRandomSayi.setId(2);
        linearLayoutBase.addView(linearLayoutRandomSayi);


        ////random sayı için textview tanımlanması
        textViewRandom = new TextView(this);
        textRandomParams = new LinearLayout.LayoutParams(boxWidth / 2, boxHeight / 2);
        textRandomParams.leftMargin = (boxWidth / 5);
        textRandomParams.topMargin = (boxHeight / 10);
        textViewRandom.setText(String.valueOf(randomSayi));
        textViewRandom.setTextSize(50);
        textViewRandom.setTypeface(Typeface.DEFAULT_BOLD);
        textViewRandom.setBackgroundResource(R.drawable.border);
        textViewRandom.setGravity(Gravity.CENTER);
        textViewRandom.setLayoutParams(textRandomParams);
        linearLayoutRandomSayi.addView(textViewRandom);


        ///doğru yanlış gösterimi için layout tanımı
        linearLayoutRightWrongParams = new LinearLayout.LayoutParams(boxWidth, (screenHeight - boxHeight - (boxHeight / 20)) / 2);
        linearLayoutRightCountText.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutWrongCountText.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutRightWrongParams.leftMargin = betweenTwo;
        linearLayoutRightWrongParams.topMargin = boxHeight / 20;
        // linearLayoutRightCountText.setBackgroundColor(Color.BLUE);
        linearLayoutRightCountText.setLayoutParams(linearLayoutRightWrongParams);
        //linearLayoutWrongCountText.setBackgroundColor(Color.RED);
        linearLayoutWrongCountText.setLayoutParams(linearLayoutRightWrongParams);
        linearLayoutRandomSayi.addView(linearLayoutRightCountText);
        linearLayoutRandomSayi.addView(linearLayoutWrongCountText);


        ////doğru yanlış sayısı gösterim alanıtanımlama
        textViewRightCount = new TextView(this);
        // textViewRightText=new TextView(this);
        textViewWrongCount = new TextView(this);
        // textViewWrongText=new TextView(this);
        imageViewRight = new ImageView(this);
        imageViewWrong = new ImageView(this);
        textViewRightCount.setText(String.valueOf(rightCount));
        textViewWrongCount.setText(String.valueOf(wrongCount));
        textViewRightCount.setTextSize(40);
        textViewWrongCount.setTextSize(40);
        textViewRightCount.setTypeface(Typeface.DEFAULT_BOLD);
        textViewWrongCount.setTypeface(Typeface.DEFAULT_BOLD);
        textViewWrongCount.setGravity(Gravity.CENTER);
        textViewRightCount.setGravity(Gravity.CENTER);
        imageViewWrong.setImageResource(R.drawable.no);
        imageViewRight.setImageResource(R.drawable.yes);
        RightWrongTextImageParams = new LinearLayout.LayoutParams(boxWidth / 2, ViewGroup.LayoutParams.MATCH_PARENT);
        imageViewRight.setLayoutParams(RightWrongTextImageParams);
        imageViewWrong.setLayoutParams(RightWrongTextImageParams);
        textViewRightCount.setLayoutParams(RightWrongTextImageParams);
        textViewWrongCount.setLayoutParams(RightWrongTextImageParams);
        linearLayoutRightCountText.addView(imageViewRight);
        linearLayoutRightCountText.addView(textViewRightCount);
        linearLayoutWrongCountText.addView(imageViewWrong);
        linearLayoutWrongCountText.addView(textViewWrongCount);


        ////grid için satır ve sütün sayısı tanımlama
        beginnerColumnNum = 4;
        beginnerRowNum = 4;

        ///////gridi dolduracak verilerin tanımlanması
        final String[] sayilar = {"1", "2", "3", "4", "8", "7", "6", "5", "9", "10", "11", "12", "16", "15", "14", "13"};


        ////grid tanımlama, grid parametre tanımlama
        gridviewBeginner = new GridView(this);
        gridviewBeginner.setNumColumns(beginnerColumnNum);
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //////grid için adapter çalıştırma
        adaptForGrid(gridviewBeginner, sayilar, imageRandomSayi);


        ///// gridi ekrana eklenmesi
        linearLayoutForGrid.addView(gridviewBeginner, listParams);

        /////grid için clicklistener eklenmesi
        gridviewBeginner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int updatePosition = (int) id;

                int sayim = Integer.valueOf(sayilar[updatePosition]);

                sayiModuBul mod = new sayiModuBul(sayim);
                int modum = mod.getSayiMod();

                String textToast = "İşaretlediğiniz " + position + "  numaralı satırdaki  " + modum + " kayıt güncelleme için" + randomSayi + " gösterilecektir";
                Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG);
                toast.show();
                if(modum==randomSayi){
                    rightCount=rightCount+1;
                }
                else if (randomSayi != modum)
                {wrongCount=wrongCount+1;
                };
                textViewRightCount.setText(String.valueOf(rightCount));
                textViewWrongCount.setText(String.valueOf(wrongCount));
                linearLayoutRightCountText.removeView(textViewRightCount);
                linearLayoutWrongCountText.removeView(textViewWrongCount);
                linearLayoutRightCountText.addView(textViewRightCount);
                linearLayoutWrongCountText.addView(textViewWrongCount);

            }

        });
    }


    //////adapter çağırma
    public void adaptForGrid(GridView gridViewUpdatealttip, String[] sayiTipleri, Integer[] randomSayiImages) {
        updateTipAdapter adapter = new
                updateTipAdapter(MainActivity.this, sayiTipleri, randomSayiImages);
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
