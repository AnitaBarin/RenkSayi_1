package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class PuanlarAl extends AppCompatActivity {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Modpoints.db";
    String gelenler;
    int screenHeightL, screenWidthL;
    String gelen;
    String intentTipi;
    String puan1;
    String puanSQL;
    ArrayList<puanlar> puanDetayAl;
    private static final int REQUEST_USER = 100;
    ListView listPuan;
    LinearLayout.LayoutParams listParam;
    private ArrayList<puanlar> puanDetay;
    private ArrayList<puanlar> puanDetay1;
    private HashMap<String,puanlar> puanTum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        listPuan = new ListView(this);

        TextView tv= new TextView(this);

        // tv= (TextView)findViewById(R.id.textView2);
        tv.setText("aaaaaaaaaaaaaaaaa");
        LinearLayout.LayoutParams prm= new LinearLayout.LayoutParams(50,50);
        prm.leftMargin=50;
        prm.topMargin=50;
        tv.setLayoutParams(prm);
        addContentView(tv,prm);


        /////////---------------------------------dimension alma---------------------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeightL = textVD.getScreenHeight(this);
        screenWidthL = textVD.getScreenWidth(this);


        Intent intentPuanlar = getIntent();
        String gelenler = intentPuanlar.getStringExtra("intentTipi");


        //////////////////--------------puan alma işini başlatma----------
        puanlar puanlarAlis = new puanlar();
        puanDetay = new ArrayList<puanlar>();
        puanTum = new HashMap<>();

        SQLiteDatabase db1 = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        selectDb(db1);


        /////////////--------------intent dönüşü---------------
        /*Intent intentTutDonus = new Intent();
        intentTipi = "Puan";
        puan1 = "Puan";
        intentTutDonus.putExtra("tipDonus", intentTipi);
        intentTutDonus.putExtra("lang", puan1);

        setResult(Activity.RESULT_OK, intentTutDonus);
        finish();*/
    }


    /////////BACK butonuna basıldığında  geri dönüş için-------------
    @Override
    public void onBackPressed() {


//////////////---------------  butonlar ekrandan silme-------------
        //ViewGroup viewHolder = (ViewGroup)imageButtonEng.getParent();
        //viewHolder.removeView(imageButtonEng);
        //ViewGroup viewHolder1 = (ViewGroup)imageButtonTrk.getParent();
        //viewHolder1.removeView(imageButtonTrk);

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

    private void selectDb(SQLiteDatabase db) {
        puanSQL = "select  game, difficulty, max(point) from points group by  game, difficulty order by  game, difficulty";
        //////verilerin isim olarak alınacağı dizinin tanımlanması
        String[] from = {"Game", "Difficulty", "Point"};
        //////verilerin yazılacağı hedef textview ataması
        int target[] = new int[]{R.id.grid_item_game, R.id.grid_item_difficulty, R.id.grid_item_point};


        //////////----------puanları sql ile alma intenti-------
        Cursor cursor = db.rawQuery(puanSQL, null);
        //puanDetayAl= new ArrayList<String>();
        int a = cursor.getCount();
        puanDetayAl = new ArrayList<puanlar>();
        int sira;
        sira = 0;
        ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        while (cursor.moveToNext()) {
            HashMap pointMap = new HashMap<>();
            puanlar puanlarAl = new puanlar();
            puanlarAl.setIdKim(0);
            puanlarAl.setKim("");
            puanlarAl.setOyun(cursor.getString(0));
            puanlarAl.setZorluk(cursor.getInt(1));
            puanlarAl.setDogru(0);
            puanlarAl.setHata(0);
            puanlarAl.setZaman("");
            puanlarAl.setSure(0);
            puanlarAl.setPuans(cursor.getInt(2));
            puanDetayAl.add(sira, puanlarAl);
            pointMap.put(from[0], cursor.getString(0));
            pointMap.put(from[1], String.valueOf(cursor.getString(1)));
            pointMap.put(from[2], String.valueOf(cursor.getInt(2)));
            Items.add(pointMap);
            pointMap = null;
            puanlarAl = null;
            sira = sira + 1;
        }
        final ListAdapter adapterTest = new SimpleAdapter(this, Items, R.layout.pointlist, from, target);

        listPuan.setAdapter(adapterTest);
        listPuan.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listPuan.setItemsCanFocus(true);
        listPuan.setVerticalScrollBarEnabled(true);
        listPuan.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);


        listPuan.setItemChecked(1, true);
        listParam = new LinearLayout.LayoutParams((screenWidthL*3)/5, (screenHeightL*3)/5);
        listParam.leftMargin = screenWidthL/5;
        listParam.topMargin = screenHeightL/5;
        listPuan.setLayoutParams(listParam);
        addContentView(listPuan, listParam);
        listPuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int updatePosition = (int) id;
                return false;
            }
        });

    }
}
