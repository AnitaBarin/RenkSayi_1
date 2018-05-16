package com.example.anita.renksayi_1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
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

    private static final int REQUEST_USER = 100;
    public String intentDonus;
    GridView gridviewBeginner, gridviewTip,gridviewZorluk;
    LinearLayout linearLayoutBase,linearLayoutForGrid, linearLayoutForGridAlt, linearLayoutRandomSayi, linearLayoutWrongCountText,linearLayoutRightCountText;
    RelativeLayout.LayoutParams baseLayoutParams;
    LinearLayout.LayoutParams linearLayoutForGridParams,linearLayoutForGridAltParams,linearLayoutRandomSayiParams, textRandomParams,linearLayoutRightWrongParams,RightWrongTextImageParams;
    int beginnerColumnNum, beginnerRowNum;
    int screenWidth, screenHeight;
    int minSayi=1;
    int maxSayi=4;
    int randomSayi,beklenenYanlisAdet;
    int rightCount=0;
    int wrongCount=0;
    int gridLeft, gridHeight,gridWidth,boxLeft,boxHeight,boxWidth,betweenTwo;
    Integer[] imageRandomSayi={R.drawable.button_base};
    Integer[] imageTipleri;//={R.drawable.lineer,R.drawable.terslineer,R.drawable.diagonal,R.drawable.maxihelix,R.drawable.midihelix,R.drawable.minihelix};
    String[]  tipler;
    String[]  zorlukSayi;
    String[] sayilar;
    String secilenTip,zorlukDeger;
    private MediaPlayer mPlayerKir= new MediaPlayer();


    TextView textViewRandom, textViewWrongText,textViewWrongCount,textViewRightText,textViewRightCount;
    ImageView imageViewWrong,imageViewRight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        randomSayi = getRandomSayi(minSayi, maxSayi);



        ////////--------------------------genel base layout  tanımlama----------------------
        ///layout tanımlama
        linearLayoutBase = new LinearLayout(this);
        linearLayoutForGrid = new LinearLayout(this);
        linearLayoutForGridAlt=new LinearLayout(this);
        linearLayoutRandomSayi = new LinearLayout(this);
        linearLayoutRandomSayi.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRightCountText = new LinearLayout(this);
        linearLayoutWrongCountText = new LinearLayout(this);

        baseLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutBase.setLayoutParams(baseLayoutParams);
        addContentView(linearLayoutBase, baseLayoutParams);

        /////////----------dimension alma-----------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeight = textVD.getScreenHeight(this);
        screenWidth = textVD.getScreenWidth(this);


        ///////////--------------oyunlar için tip seçimi fonksiyonu çağırma------------------
        tipAlma();

    }



    ///////////----------------------adapter bölümü-----------------------------

    ////////oyun tipi seçimi adapter
    private void adaptForGridTip(GridView gridviewTipim, String[] tiplerim, Integer[] imageTiplerim) {
        tipSecimiAdapter adapter = new
                tipSecimiAdapter(MainActivity.this, tiplerim, imageTiplerim);
        // listViewUpdate = new ListView(this);
        gridviewTipim.setAdapter(adapter);
    }


    //////oyunu açan adapter çağırma
    public void adaptForGrid(GridView gridViewUpdatealttip, String[] sayiTipleri, Integer[] randomSayiImages) {
        updateTipAdapter adapter = new
                updateTipAdapter(MainActivity.this, sayiTipleri, randomSayiImages);
        // listViewUpdate = new ListView(this);
        gridViewUpdatealttip.setAdapter(adapter);
    }


    /////////////----------------------oyun mod  sayı üretimi----------------------
    //////mod4 için random sayı üretme
    private int getRandomSayi(int minSayiG,int maxSayiG){
        int randomSayiG;
        Random random=new Random();
        randomSayiG = random.nextInt(maxSayiG-minSayiG+1)+minSayiG;
        return randomSayiG;
    }

    ///////------------------------intentlerin çağrılması------------------------------
    ////////intent çağırma

    //////Tip alma için intent çalıştırma
    private void tipAlma(){
        //String gidenDeger;
        //gidenDeger=String.valueOf(positionZorluk);
        Intent intentTipAl = new Intent(this, TipSecim.class);
        //intentZorlukAl.putExtra("giden", gidenDeger);
        intentDonus="tip";
        intentTipAl.putExtra("intentTipi",intentDonus);
        startActivityForResult(intentTipAl, REQUEST_USER);

    }

    //////zorluk alma için intent çalıştırma
    private void zorlukAlma(int positionZorluk){
        String gidenDeger;
        gidenDeger=String.valueOf(positionZorluk);
        Intent intentZorlukAl = new Intent(this, ZorlukSecim.class);
        intentZorlukAl.putExtra("giden", gidenDeger);
        intentDonus="zorluk";
        intentZorlukAl.putExtra("intentTipi",intentDonus);
        startActivityForResult(intentZorlukAl, REQUEST_USER);

    }



    ///////--------------------------intentlerin dönüşleri-------------------------------------
    /////////////intent dönüşleri için

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_USER) {
            if (resultCode == RESULT_OK) {
                intentDonus = data.getStringExtra("tipDonus");

            }
        }

        //////////////Tip seçimi için intent
        if(intentDonus.equals("TipSecim")){
            Integer tipImage []=(Integer[]) data.getSerializableExtra("tipResim");
            String tipAdi []=(String []) data.getSerializableExtra("tipAd");

            ///////tip seçimi için grid tanımlama
            gridviewTip = new GridView(this);
            gridviewTip.setNumColumns(3);

            //////tip gridi için adapter çağırma
            adaptForGridTip(gridviewTip, tipAdi, tipImage);

            ///// tip gridinin ekrana eklenmesi
            LinearLayout.LayoutParams listParamsTip = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayoutBase.addView(gridviewTip, listParamsTip);

            //////////////////tip seçimi için clicklistener
            gridviewTip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int updatePosition1 = (int) id+1;
                    secilenTip=String.valueOf(position);
                    String textToast = "İşaretlediğiniz " + position + "  numaralı satırdaki   gösterilecektir";
                    Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_SHORT);
                    toast.show();
                    /////////Zorluk alma için intent çalıştırmaya gidiş
                    ///////tip 0,1,2,3   ise zorluk seçimi yapılır, tip 4,5 ise zorluk direkt 5tir
                    if (position>3){
                        zorlukDeger="5";
                    }
                    if (position<4){
                        zorlukAlma(position);
                    }

                }
            });
        }



        ////////////zorluk seçimi için intent
        if (intentDonus.equals("ZorlukSecim")) {
           Integer zorlukImage[]= (Integer[]) data.getSerializableExtra("zorluklar");
            String zorlukSayi []={"","","","","",""};
            ///////tip seçimi için grid tanımlama
            gridviewZorluk = new GridView(this);
            gridviewZorluk.setNumColumns(3);

            ///////Zorluk seçimi için adapter çağırma
            adaptForGridTip(gridviewZorluk, zorlukSayi, zorlukImage);

            /////zorluk gridinin ekrana eklenmesi
            LinearLayout.LayoutParams listParamsTip = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayoutBase.removeAllViewsInLayout();
            linearLayoutBase.addView(gridviewZorluk, listParamsTip);
            /////////zorluk gridi için clicklistener

        gridviewZorluk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int updatePosition1 = (int) id+1;
                zorlukDeger=String.valueOf(position);
                String textToast = "Seçtiğiniz tip -" + secilenTip+"- seçtiğiniz zorluk ise -" + zorlukDeger + "- olmuştur.";
                Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG);
                toast.show();
                oyunGridYap(secilenTip,zorlukDeger);

            }
        });
        }
    }


    public void oyunGridYap(String secilmisTip,String secilenZorluk){

       final  Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        ///grid ve kutu için size tanımlama
        gridLeft = screenWidth / 20;
        gridHeight = (screenHeight / 100) * 100;
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
        linearLayoutBase.removeAllViewsInLayout();
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
        beginnerColumnNum = 12;
        beginnerRowNum = 12;





        /////-----------------------------------------------------
        ///////gridi dolduracak verilerin tanımlanması
        final String[] sayilar = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100","101","102","103","104","105","106","107","108","109","110","111","112","113","114","115","116","117","118","119","120","121","122","123","124","125","126","127","128","129","130","131","132","133","134","135","136","137","138","139","140","141","142","143","144"};

                //////{"1", "2", "3", "4", "8", "7", "6", "5", "9", "10", "11", "12", "16", "15", "14", "13"};


        ////grid tanımlama, grid parametre tanımlama
        gridviewBeginner = new GridView(this);
        gridviewBeginner.setNumColumns(beginnerColumnNum);
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listParams.topMargin=screenHeight/15;

        //////grid için adapter çalıştırma
        adaptForGrid(gridviewBeginner, sayilar, imageRandomSayi);


        ///// gridi ekrana eklenmesi

        linearLayoutForGrid.addView(gridviewBeginner, listParams);

        /////oyun gridi için clicklistener eklenmesi
        /////oyun gridi için clicklistener eklenmesi
        gridviewBeginner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                int updatePosition = (int) id;

                int sayim = Integer.valueOf(sayilar[updatePosition]);

                sayiModuBul mod = new sayiModuBul(sayim);
                int modum = mod.getSayiMod();

                String textToast = "İşaretlediğiniz " + position + "  numaralı satırdaki  " + modum + " kayıt güncelleme için" + randomSayi + " gösterilecektir";
                Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG);
                //toast.show();
                if(modum==randomSayi){
                    rightCount=rightCount+1;
                    ///////vibration efekti
                    vibe.vibrate(50);
                    //////ses efekti
                    mPlayerKir=MediaPlayer.create(MainActivity.this,R.raw.right);
                    mPlayerKir.start();
                    view.setBackgroundColor(Color.GREEN);
                }
                else if (randomSayi != modum)
                {wrongCount=wrongCount+1;
                    ///////vibration efekti
                    vibe.vibrate(200);
                    //////ses efekti
                    mPlayerKir=MediaPlayer.create(MainActivity.this,R.raw.wrong);
                    mPlayerKir.start();


                    view.setBackgroundColor(Color.RED);
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

}
