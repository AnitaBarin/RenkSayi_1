package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.view.Menu;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.*;
import android.os.Bundle;
import android.support.v7.widget.*;
//import android.support.v7.widget.RecyclerView.OnFlingListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static android.support.v7.widget.RecyclerView.*;

public class MainActivity  extends Activity {

    private static final int REQUEST_USER = 100;
    public String intentDonus, intentDonusB;
    GridView gridviewBeginner, gridviewTip, gridviewZorluk;
    ImageButton imageButtonTutor,imageButtonGame, imageButtonTrk,imageButtonEng;
    LinearLayout linearLayoutBase, linearLayoutForGrid, linearLayoutForGridAlt, linearLayoutRandomSayi, linearLayoutWrongCountText, linearLayoutRightCountText;
    RelativeLayout.LayoutParams baseLayoutParams;
    LinearLayout.LayoutParams linearLayoutForGridParams, linearLayoutForGridAltParams, linearLayoutRandomSayiParams, textRandomParams, linearLayoutRightWrongParams, RightWrongTextImageParams,
            fireworkParams,fireworkParams1,fireworkParams2,fireworkParams3,fireworkParams4,fireworkParams5,endTimeParams,wrongParams,tutParams, butEngParams,butTrkParams;
    int beginnerColumnNum, beginnerRowNum;
    int screenWidth, screenHeight;
    int minSayi = 0;
    int maxSayi = 3;
    int randomSayi, beklenenYanlisAdet, bittiMi;
    int rightCount = 0;
    int wrongCount = 0;
    int gridLeft, gridHeight, gridWidth, boxLeft, boxHeight, boxWidth, betweenTwo;
    Integer[] imageRandomSayi = {R.drawable.button_base};
    Integer[] imageTipleri;//={R.drawable.lineer,R.drawable.terslineer,R.drawable.diagonal,R.drawable.maxihelix,R.drawable.midihelix,R.drawable.minihelix};
    String[] tipler;
    String[] zorlukSayi;
    String[] sayilar;
    String[] oyunlarAdi;
    String[] puanTable;
    String secilenTip, zorlukDeger, secilenDil,secilenIs;
    String oyunlarAdim, zorlukAdim, tipZorlukm;
    String myPlace = "";
    private MediaPlayer mPlayerWork = new MediaPlayer();
    private MediaPlayer mPlayerTrueFalse = new MediaPlayer();
    private MediaPlayer mPlayerEnd = new MediaPlayer();
    private MediaPlayer mPlayerTimeEnd = new MediaPlayer();
    private MediaPlayer mPlayerCongrate = new MediaPlayer();
    long startTime;
    long finishTime;
    int difference, puanKazan;
    long nowTime;
    GifImage gifImageCongrate,gifImageCongrate1,gifImageCongrate2,gifImageCongrate3,gifImageCongrate4,gifImageCongrate5,gifImageTimeEnd,gifImageWrong;
    ViewFlipper tutorialFlip;
    int tutorialImages[];
    int tutorialSayi;
    private float initialX;
    String gidenInsSQL;



    TextView textViewRandom, textViewWrongText, textViewWrongCount, textViewRightText, textViewRightCount;
    ImageView imageViewWrong, imageViewRight;

    String DATABASE_NAME= "Modpoints.db";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        //////db db db db db db///////////////

      CreatePoint createPoint;
        SQLiteDatabase db = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        createPoint=new CreatePoint(this);
        createPoint.onCreate(db);
      //  createPoint.insertDb(db);
      // createPoint.selectDb(db);


        ///////////db db db db db db ///////////


        randomSayi = getRandomSayi(minSayi, maxSayi);

        /////////---------------------------------dimension alma---------------------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeight = textVD.getScreenHeight(this);
        screenWidth = textVD.getScreenWidth(this);





        ///////////////-----------------Ingilizce mi Türkçe mi seçimi

        langSec();

        //////////////-----------------tutorial için flipper ve imageları tanımlama--------------

       //tutorialAc();




        ////////--------------------------genel base layout  tanımlama----------------------
        ///layout tanımlama
        linearLayoutBase = new LinearLayout(this);
        linearLayoutForGrid = new LinearLayout(this);
        linearLayoutForGridAlt = new LinearLayout(this);
        linearLayoutRandomSayi = new LinearLayout(this);
        linearLayoutRandomSayi.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRightCountText = new LinearLayout(this);
        linearLayoutWrongCountText = new LinearLayout(this);

        baseLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutBase.setLayoutParams(baseLayoutParams);
        addContentView(linearLayoutBase, baseLayoutParams);



        ///////////-----------------------------oyunlar için tip seçimi fonksiyonu çağırma------------------------------
      // tipAlma();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }




    ///////////-------------------------------------adapter bölümü----------------------------------------------

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





    /////////////-----------------------------------oyun mod  sayı üretimi------------------------------------
    //////mod4 için random sayı üretme
    private int getRandomSayi(int minSayiG, int maxSayiG) {
        int randomSayiG;
        Random random = new Random();
        randomSayiG = random.nextInt(maxSayiG - minSayiG + 1) + minSayiG;
        return randomSayiG;
    }

    ///////------------------------------------intentlerin çağrılması----------------------------------------
    ////////intent çağırma

    //////Tip alma için intent çalıştırma
    private void tipAlma() {
        //String gidenDeger;
        //gidenDeger=String.valueOf(positionZorluk);
        myPlace = "Tip";
        Intent intentTipAl = new Intent(this, TipSecim.class);
        //intentZorlukAl.putExtra("giden", gidenDeger);
        intentDonus = "tip";
        intentTipAl.putExtra("intentTipi", intentDonus);
        intentTipAl.putExtra("dilim",secilenDil);
        startActivityForResult(intentTipAl, REQUEST_USER);

    }

    //////zorluk alma için intent çalıştırma
    private void zorlukAlma(int positionZorluk) {
        myPlace = "Zorluk";
        String gidenDeger;
        gidenDeger = String.valueOf(positionZorluk);
        Intent intentZorlukAl = new Intent(this, ZorlukSecim.class);
        intentZorlukAl.putExtra("giden", gidenDeger);
        intentDonus = "zorluk";
        intentZorlukAl.putExtra("intentTipi", intentDonus);
        startActivityForResult(intentZorlukAl, REQUEST_USER);

    }


    ///////////////---------tutorial seçiminde tutorial açmak için---------------
    private void tutorialAc(String secilenDilim) {
        myPlace = "Tutorial";
        Intent intentTutor = new Intent(this, Tutor.class);
        intentDonus = "tutorial";
        intentTutor.putExtra("intentTipi", intentDonus);
        startActivityForResult(intentTutor, REQUEST_USER);}


    //////////////--------------dil seçimi ingilizce/türkçe seçimi için
    private void langSec(){
        myPlace="Language";
        Intent intentLang= new Intent(this,LangSec.class);
        intentDonus="language";
        intentLang.putExtra("intentTipi",intentDonus);
        startActivityForResult(intentLang, REQUEST_USER);

    }


    ////////////---------------oyun mu tutorial mı seç

    private void isSec(String languageSec){
        myPlace="İş";
        Intent intentIs= new Intent(this,IsSec.class);
        intentDonus="iş";
        intentIs.putExtra("intentTipi",intentDonus);
        intentIs.putExtra("dilim",languageSec);
        startActivityForResult(intentIs, REQUEST_USER);

    }

    /////////////----------puanları kayıt için

    public void puanKaydet(){
        myPlace="Kayıt";
        Intent intentKayit= new Intent(this,PuanSave.class);
        intentDonus="Kayit";
        //////günün tarihini alma
        DateFormat df = new SimpleDateFormat(("dd-MMM-yyyy"));
        String dateS = df.format(Calendar.getInstance().getTime());
        ////////-----oyun ve xorluk tipine göre puan alma
        if (oyunlarAdim.equals("Lineer") || oyunlarAdim.equals("Linear")){
          puanTable= this.getResources().getStringArray(R.array.lineerpoint);
        }
        if (oyunlarAdim.equals("TersLineer") || oyunlarAdim.equals("InverseLinear")){
            puanTable= this.getResources().getStringArray(R.array.terslineerpoint);
        }
        if (oyunlarAdim.equals("Diagonal") || oyunlarAdim.equals("Diagonal")){
            puanTable= this.getResources().getStringArray(R.array.diagonalpoint);
        }
        if (oyunlarAdim.equals("Helix") || oyunlarAdim.equals("Helix")){
            puanTable= this.getResources().getStringArray(R.array.helixpoint);
        }
        if (oyunlarAdim.equals("MiniHelix") || oyunlarAdim.equals("MiniHelix")){
            puanTable= this.getResources().getStringArray(R.array.minihelixpoint);
        }
        if (oyunlarAdim.equals("MidiHelix") || oyunlarAdim.equals("MidiHelix")){
            puanTable= this.getResources().getStringArray(R.array.midihelixpoint);
        }
        puanKazan= Integer.valueOf(puanTable[Integer.valueOf(zorlukDeger)]);
        ///// oyun süresini bulma - tarih ile süre birlikte time lanına yazılıyor.
        long diff1=(finishTime-startTime)/1000;
        //////sql oluşturma
        gidenInsSQL="insert into points (who, game,difficulty,right,wrong,time,point)  values ('ben','"+oyunlarAdim+"',"+zorlukDeger+","+rightCount+","+wrongCount+",'"+dateS+"/"+diff1+"',"+puanKazan+")";
        ///////intent ile gidiş
        intentKayit.putExtra("intentTipi",intentDonus);
        intentKayit.putExtra("insSql",gidenInsSQL);
        startActivityForResult(intentKayit,REQUEST_USER);


    }

    //////////--------------puanları alabilmek için-----------
    private void puanAlma(){
        myPlace="Puan";
        Intent intentPuan= new Intent(this,PuanlarAl.class);
        intentDonus="Puan";
        intentPuan.putExtra("intentTipi",intentDonus);
        startActivityForResult(intentPuan, REQUEST_USER);

    }


    ///////--------------------------intentlerin dönüşleri-------------------------------------
    /////////////------------intent dönüşleri için

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_USER) {
            if (resultCode == RESULT_OK) {
                intentDonusB = data.getStringExtra("tipDonus");

            }
        }

        //////////////---------------Tip seçimi için intent
        if (intentDonusB.equals("TipSecim")) {
            Integer tipImage[] = (Integer[]) data.getSerializableExtra("tipResim");
            String tipAdi[] = (String[]) data.getSerializableExtra("tipAd");

            ///////------------------tip seçimi için grid tanımlama
            gridviewTip = new GridView(this);
            gridviewTip.setNumColumns(3);

            //////-------------------tip gridi için adapter çağırma
            adaptForGridTip(gridviewTip, tipAdi, tipImage);

            ///// ------------------tip gridinin ekrana eklenmesi
            LinearLayout.LayoutParams listParamsTip = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayoutBase.addView(gridviewTip, listParamsTip);

            //////////////////-----------------tip seçimi için clicklistener
            gridviewTip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int updatePosition1 = (int) id + 1;
                    secilenTip = String.valueOf(position);
                    String textToast = "İşaretlediğiniz " + position + "  numaralı satırdaki   gösterilecektir";
                    Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_SHORT);
                   // toast.show();
                    /////////Zorluk alma için intent çalıştırmaya gidiş
                    ///////tip 0,1,2,3   ise zorluk seçimi yapılır, tip 4,5 ise zorluk direkt 5tir
                    if (position > 3) {
                        zorlukDeger = null;
                        oyunGridYap(secilenTip, zorlukDeger);
                    }
                    if (position < 4) {
                        zorlukAlma(position);
                    }

                }
            });
        }


        ////////////-------------zorluk seçimi için intent
        if (intentDonusB.equals("ZorlukSecim")) {
            Integer zorlukImage[] = (Integer[]) data.getSerializableExtra("zorluklar");
            String zorlukSayi[] = {"", "", "", "", "", ""};
            ///////tip seçimi için grid tanımlama
            gridviewZorluk = new GridView(this);
            gridviewZorluk.setNumColumns(3);

            ///////------------Zorluk seçimi için adapter çağırma
            adaptForGridTip(gridviewZorluk, zorlukSayi, zorlukImage);

            /////---------------zorluk gridinin ekrana eklenmesi
            LinearLayout.LayoutParams listParamsTip = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            linearLayoutBase.removeAllViewsInLayout();
            linearLayoutBase.addView(gridviewZorluk, listParamsTip);

            /////////-------------zorluk gridi için clicklistener
            gridviewZorluk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int updatePosition1 = (int) id + 1;
                    zorlukDeger = String.valueOf(position);
                    String textToast = "Seçtiğiniz tip -" + secilenTip + "- seçtiğiniz zorluk ise -" + zorlukDeger + "- olmuştur.";
                    Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_LONG);
                   // toast.show();

                    ////////--------------grid çağırma
                    oyunGridYap(secilenTip, zorlukDeger);

                }
            });
        }

        //////////-------------tutorial gösterme geri dönüşü--------

        if (intentDonusB.equals("TutorAc")){
            String textToast = "İşaretlediğiniz tutorial   gösterilecektir";
            Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_SHORT);
            toast.show();
            isSec(secilenDil);

        }

        ////////////----------------dil seçimi

        if (intentDonusB.equals("Language")){
            secilenDil=data.getStringExtra("lang");
            String textToast = "Dil seçtin";
            Toast toast = Toast.makeText(getApplicationContext(), textToast, Toast.LENGTH_SHORT);
            toast.show();
            isSec(secilenDil);
        }

        ///////-------------yapılacak işin seçimi-------------

        if(intentDonusB.equals("Is")){
            secilenIs=data.getStringExtra("is");
            ///////////--------------oyun ise-----------------
           if (secilenIs.equals("Oyun")){
            tipAlma();
           }
            /////////////-------------tutor ise------------
            if(secilenIs.equals("Tutor")){
                tutorialAc(secilenDil);
            }
            /////////////-----------puan ise------------
            if(secilenIs.equals("Puan")){
                puanAlma();
            }
        }


        /////////////-------------puanlar kısmından dönüş için----------
        if (intentDonusB.equals("Puan")){
            isSec(secilenDil);

        }
    }

    //////////////-------------------------------oyun gridi ekranı tasarlanması--------------------------------------------

    public void oyunGridYap(String secilmisTip, String secilenZorluk) {

        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        myPlace = "Oyun";

        ///////-------------grid ve kutu için size tanımlama
        gridLeft = screenWidth / 20;
        gridHeight = (screenHeight / 100) * 100;
        gridWidth = gridHeight;
        boxLeft = 0;
        boxHeight = (screenHeight / 10) * 6;
        boxWidth = boxHeight;
        betweenTwo = screenHeight / 20;

        ////////////-----------grid  için alt ekran ve parametre tanımlama
        linearLayoutForGridParams = new LinearLayout.LayoutParams(gridWidth, gridHeight);
        linearLayoutForGrid.setBackgroundResource(R.mipmap.back_date_paper);
        linearLayoutForGrid.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutForGridParams.leftMargin = gridLeft;
        linearLayoutForGrid.setId(1);
        linearLayoutBase.removeAllViewsInLayout();
        linearLayoutBase.addView(linearLayoutForGrid, linearLayoutForGridParams);

        /////////////---------------------------randomsayının gösterimi için alt ekran ve parametre
        linearLayoutRandomSayiParams = new LinearLayout.LayoutParams(boxWidth, screenHeight);
        linearLayoutRandomSayi.setBackgroundResource(R.mipmap.back_butce_paper);
        linearLayoutRandomSayi.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRandomSayiParams.leftMargin = betweenTwo;
        linearLayoutRandomSayi.setLayoutParams(linearLayoutRandomSayiParams);
        linearLayoutRandomSayi.setId(2);
        linearLayoutBase.addView(linearLayoutRandomSayi);


        /////////////-------------random sayı için textview tanımlanması
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


        //////////////--------------doğru yanlış gösterimi için layout tanımı
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


        ///////////////-------------doğru yanlış sayısı gösterim alanıtanımlama
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


        //////////////////----------------grid için satır ve sütün sayısı tanımlama
        beginnerColumnNum = ((Integer.valueOf(zorlukDeger) * 2) + 2);
        beginnerRowNum = beginnerColumnNum;


        /////-----------------------------------------------------
        ///////---------------------gridi dolduracak verilerin tanımlanması--------------------------

        String oyunum, sayim;
        //////////------------------oyun adları ve zorluklar için res/arrays verilerini alma
        if (secilenDil.equals("Trk")) {
            oyunlarAdi = this.getResources().getStringArray(R.array.tipler);
        }
        if(secilenDil.equals("Eng")){
            oyunlarAdi = this.getResources().getStringArray(R.array.tipler);
        }
        String[] zorlukAdi = this.getResources().getStringArray(R.array.zorluk);
        String[] tipZorluk = this.getResources().getStringArray(R.array.TumTipZorluk);

        /////////-------------------seçilen oyun tipine göre Arrayden oyunun adını bulma
        oyunlarAdim = oyunlarAdi[Integer.valueOf(secilenTip)];

        ///////----------------mini ve midi helix için sadece zorluk 6 var, bu nedenle sadece ad ile verileri var. diğerleri için zorluk değeri ile ismi birleştirme
        if (oyunlarAdim.equals("MiniHelix")) {
            tipZorlukm = oyunlarAdim;
        } else if (oyunlarAdim.equals("MidiHelix")) {
            tipZorlukm = oyunlarAdim;
        } else {
            zorlukAdim = zorlukAdi[Integer.valueOf(secilenZorluk)];
            tipZorlukm = oyunlarAdim + zorlukAdim;
        }


        //////////// -----------------oyuntip ve zorluk birleşince oluşan isim ile res/arrays verilerinden seçilen oyun için geçerli olan verileri bulma
        oyunum = tipZorlukm;
        int arryid = this.getResources().getIdentifier(oyunum, "array",
                this.getPackageName());
        final String[] sayilar = this.getResources().getStringArray(arryid);


        //////////////------------------------------------oyun gridi---------------------------------
        ////grid tanımlama, grid parametre tanımlama
        gridviewBeginner = new GridView(this);
        gridviewBeginner.setNumColumns(beginnerColumnNum);
        LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        listParams.topMargin = screenHeight / 15;


        //////-----------------grid için adapter çalıştırma
        adaptForGrid(gridviewBeginner, sayilar, imageRandomSayi);


        /////--------------- gridi ekrana eklenmesi
        linearLayoutForGrid.addView(gridviewBeginner, listParams);
        startTime=System.currentTimeMillis();

        //////--------------oyun başlarken herhangi bir nedenle bitiş kontrolüne "0" atanması
        bittiMi=0;


        //////--------------zaman hesaplama için başlangıç zamanı ve geçebilir süre tanımlama
        //////--------------zaman hesaplama için başlangıç zamanı ve geçebilir süre tanımlama


        final Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {

            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
        ///////------------saat tik tak sesi---------------
        mPlayerWork = MediaPlayer.create(MainActivity.this, R.raw.allworking);
        mPlayerWork.start();
        //startTime=System.currentTimeMillis();


        ////////------------timer tanımlama-------------------
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                gridviewBeginner.post(new Runnable() {
                    @Override
                    public void run() {
                        if (bittiMi==0) {
                            ////////---------------zaman dolduğunda yapılması istenenin tanımlandığı yer-------------
                            String textToast111 = "İşaretlediğiniz zaman  numaralı satırdaki   gösterilecektir";
                            Toast toast = Toast.makeText(getApplicationContext(), textToast111, Toast.LENGTH_SHORT);
                           // toast.show();
                            bittiMi=1;
                            vibe.vibrate(300);
                            //////ses efekti
                            mPlayerWork.stop();
                            mPlayerTimeEnd = MediaPlayer.create(MainActivity.this, R.raw.timeended);
                            mPlayerTimeEnd.start();
                            gridviewBeginner.setBackgroundColor(Color.RED);
                            gridviewBeginner.setEnabled(false);
                            gifImageTimeEnd=new GifImage(MainActivity.this);
                            gifImageTimeEnd.setGifImageResource(R.drawable.timeend0);
                            endTimeParams = new LinearLayout.LayoutParams(250,250);
                            endTimeParams.leftMargin=(screenWidth/4);endTimeParams.topMargin=screenHeight/8;

                            gifImageTimeEnd.setLayoutParams(endTimeParams);
                            addContentView(gifImageTimeEnd, endTimeParams);
                        }
                    }
                });
            }
        };

        ////////------------------zorluğa göre geçmesi gereken zamanın tanımlanması ve timer için schedule çalıştırılması
        difference= Integer.valueOf((int) Math.pow(2,(Integer.valueOf(zorlukDeger)+2)));
        timer.schedule(timerTask, Integer.valueOf(difference*1000)); // 1000 = 1 second.



        //////////////////////////////---------------------oyun gridi için clicklistener eklenmesi-------------------
        /////////////////---------------oyun gridi için clicklistener eklenmesi
        gridviewBeginner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                int updatePosition = (int) id;
                int sayim = Integer.valueOf(sayilar[updatePosition]);

                sayiModuBul mod = new sayiModuBul(sayim);
                int modum = mod.getSayiMod();

                ///////------------click edilen griditemin doğru mu yanlış mı olduğunun kontrolü-----------------

                ////------------eğer doğru seçim yapıldı ise
                if (modum == randomSayi) {
                    rightCount = rightCount + 1;
                    if(rightCount>=(Math.pow((Integer.valueOf(zorlukDeger)+1),2))){
                        ///////////------tüm doğrular bulundu ise
                        bittiMi=2;
                        vibe.vibrate(300);
                        mPlayerWork.stop();
                        mPlayerCongrate = MediaPlayer.create(MainActivity.this, R.raw.congrate);
                        mPlayerCongrate.start();
                        gridviewBeginner.setBackgroundColor(Color.GREEN);
                        gridviewBeginner.setEnabled(false);

                        ////////--------congrate gif için-----------------
                        //////GifImage.java da tanımlanan ifimage kullanılıyor. 6 adet gif için

                       gifImageCongrate=new GifImage(MainActivity.this);gifImageCongrate1=new GifImage(MainActivity.this);
                        gifImageCongrate2=new GifImage(MainActivity.this);gifImageCongrate3=new GifImage(MainActivity.this);
                        gifImageCongrate4=new GifImage(MainActivity.this);gifImageCongrate5=new GifImage(MainActivity.this);
                        gifImageCongrate.setGifImageResource(R.drawable.firework3);gifImageCongrate1.setGifImageResource(R.drawable.confetti1);
                        gifImageCongrate2.setGifImageResource(R.drawable.firework4);gifImageCongrate3.setGifImageResource(R.drawable.confetti2);
                        gifImageCongrate4.setGifImageResource(R.drawable.firework5);gifImageCongrate5.setGifImageResource(R.drawable.confetti3);
                        fireworkParams = new LinearLayout.LayoutParams(250,250);fireworkParams1=new LinearLayout.LayoutParams(250,250);
                        fireworkParams2 = new LinearLayout.LayoutParams(250,250);fireworkParams3=new LinearLayout.LayoutParams(250,250);
                        fireworkParams4 = new LinearLayout.LayoutParams(250,250);fireworkParams5=new LinearLayout.LayoutParams(250,250);
                        fireworkParams.leftMargin=0;fireworkParams.topMargin=0;
                        fireworkParams1.leftMargin=screenWidth/3;fireworkParams1.topMargin=0;
                        fireworkParams2.leftMargin=(screenWidth*2)/3;fireworkParams2.topMargin=0;
                        fireworkParams3.leftMargin=0;fireworkParams3.topMargin=screenHeight/2;
                        fireworkParams4.leftMargin=screenWidth/3;fireworkParams4.topMargin=screenHeight/2;
                        fireworkParams5.leftMargin=(screenWidth*2)/3;fireworkParams5.topMargin=screenHeight/2;

                        gifImageCongrate.setLayoutParams(fireworkParams);gifImageCongrate1.setLayoutParams(fireworkParams1);
                        gifImageCongrate2.setLayoutParams(fireworkParams2);  gifImageCongrate3.setLayoutParams(fireworkParams3);
                        gifImageCongrate4.setLayoutParams(fireworkParams4);  gifImageCongrate5.setLayoutParams(fireworkParams5);
                        addContentView(gifImageCongrate, fireworkParams);addContentView(gifImageCongrate1, fireworkParams1);
                        addContentView(gifImageCongrate2, fireworkParams2);addContentView(gifImageCongrate3, fireworkParams3);
                        addContentView(gifImageCongrate4, fireworkParams4);addContentView(gifImageCongrate5, fireworkParams5);
                        finishTime=System.currentTimeMillis();
                        puanKaydet();



                    }
                    else {
                        ////////------------henüz tüm doğrular bulunmadı ise
                        ///////vibration efekti
                        vibe.vibrate(50);
                        //////ses efekti
                        mPlayerTrueFalse = MediaPlayer.create(MainActivity.this, R.raw.right);
                        mPlayerTrueFalse.start();
                        view.setBackgroundColor(Color.GREEN);
                    }

                }
                ////////---------------eğer yanlış seçim yapıldı ise
                else if (randomSayi != modum) {
                    wrongCount = wrongCount + 1;
                    if (wrongCount >= ((Integer.valueOf(zorlukDeger) * 2) + 2)) {
                        //////eğer yanlış sayısı zoruluğa göre değişen değere eşit veya büyük ise
                        ///////vibration efekti
                        bittiMi=3;
                        vibe.vibrate(300);
                        //////ses efekti
                        mPlayerWork.stop();
                        mPlayerEnd = MediaPlayer.create(MainActivity.this, R.raw.allwrong);
                        mPlayerEnd.start();
                        view.setBackgroundColor(Color.RED);
                        gridviewBeginner.setEnabled(false);
                        gifImageWrong=new GifImage(MainActivity.this);
                        gifImageWrong.setGifImageResource(R.drawable.wrong);
                        wrongParams = new LinearLayout.LayoutParams(250,250);
                        wrongParams.leftMargin=(screenWidth/4);wrongParams.topMargin=screenHeight/8;

                        gifImageWrong.setLayoutParams(wrongParams);
                        addContentView(gifImageWrong, wrongParams);

                    } else {
                        //////----------------eğer yanlış sayısı zoruluğa göre değişen değerden küçük ise
                        ///////vibration efekti
                        vibe.vibrate(200);
                        //////ses efekti
                        mPlayerTrueFalse = MediaPlayer.create(MainActivity.this, R.raw.wrong);
                        mPlayerTrueFalse.start();
                        view.setBackgroundColor(Color.RED);
                    }
                }
                ;
                textViewRightCount.setText(String.valueOf(rightCount));
                textViewWrongCount.setText(String.valueOf(wrongCount));
                linearLayoutRightCountText.removeView(textViewRightCount);
                linearLayoutWrongCountText.removeView(textViewWrongCount);
                linearLayoutRightCountText.addView(textViewRightCount);
                linearLayoutWrongCountText.addView(textViewWrongCount);

            }

        });
    }

    /////////////----------------geri tuşuna basıldığında----------------
    @Override
    public void onBackPressed() {
        ///////----------------tip seçimi sayfasında geri tuşuna basılırsa çıkılır.
        if (myPlace.equals("Tip")) {
            //super.onBackPressed();
            linearLayoutBase.removeAllViewsInLayout();
            isSec(secilenDil);
        }
        ///////----------------zorluk seçimi sayfasında geri tuşuna baslırsa tip seçimi sayfasına döner, yeni tip seçilir.
        if (myPlace.equals("Zorluk")) {
            linearLayoutBase.removeAllViewsInLayout();
            tipAlma();
        }
        ///////----------------oyun gridi sayfasında geri tuşuna basılırsa zorluk seçimi sayfasına döner. yeni zorluk seçilir. tuşa basmadan önce yapılmış doğru ve yanlış clicklerin sayısı sıfırlanır.
        if (myPlace.equals("Oyun")) {
            rightCount = 0;
            wrongCount = 0;
            if (bittiMi==1){////----zaman bitti  ise
                ViewGroup viewHolder = (ViewGroup)gifImageTimeEnd.getParent();
                viewHolder.removeView(gifImageTimeEnd);
                mPlayerTimeEnd.stop();
            }
            if (bittiMi==2){//////------tüm doğrular bulundu ise
                ViewGroup viewHolder = (ViewGroup)gifImageCongrate.getParent();
                viewHolder.removeView(gifImageCongrate);viewHolder.removeView(gifImageCongrate1);viewHolder.removeView(gifImageCongrate2);
                viewHolder.removeView(gifImageCongrate3);viewHolder.removeView(gifImageCongrate4);viewHolder.removeView(gifImageCongrate5);
                viewHolder.removeView(gifImageTimeEnd);
                mPlayerCongrate.stop();
            }
            if (bittiMi==3){///////------max yanlış yapıldı ise
                ViewGroup viewHolder = (ViewGroup)gifImageWrong.getParent();
                viewHolder.removeView(gifImageWrong);
                mPlayerEnd.stop();
            }
            linearLayoutRightCountText.removeAllViewsInLayout();
            linearLayoutWrongCountText.removeAllViewsInLayout();
            linearLayoutRandomSayi.removeAllViewsInLayout();
            linearLayoutForGrid.removeAllViewsInLayout();
            linearLayoutBase.removeAllViewsInLayout();
            mPlayerWork.stop();
            zorlukAlma(Integer.valueOf(secilenTip));
        }

    }

}
