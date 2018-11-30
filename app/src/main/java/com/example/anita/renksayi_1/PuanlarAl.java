package com.example.anita.renksayi_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PuanlarAl extends AppCompatActivity {

    RelativeLayout relativeLayoutPuan;
    RelativeLayout.LayoutParams layoutParamsPuan;
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
    RelativeLayout.LayoutParams listParam, imageParam;
    private ArrayList<puanlar> puanDetay;
    private ArrayList<puanlar> puanDetay1;
    private HashMap<String,puanlar> puanTum;
    ImageView listSumImage;
    String selectTip,selectOyun;
    int selectZor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        relativeLayoutPuan=new RelativeLayout(this);
        layoutParamsPuan= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayoutPuan.setLayoutParams(layoutParamsPuan);
        addContentView(relativeLayoutPuan,layoutParamsPuan);


        /////////---------------------------------dimension alma---------------------------------
        ////ekran için dimension alma
        dimens textVD = new dimens();
        screenHeightL = textVD.getScreenHeight(this);
        screenWidthL = textVD.getScreenWidth(this);

        //////gelen intet bilgisi al

        Intent intentPuanlar = getIntent();
        String gelenler = intentPuanlar.getStringExtra("intentTipi");


        //////////////////--------------puan alma işini başlatma----------
        puanlar puanlarAlis = new puanlar();
        puanDetay = new ArrayList<puanlar>();
        puanTum = new HashMap<>();

        /////---------tum puan alma işini çağırma

       tumPuan();

    }


    /////////-------------tüm puan alma işi için select çağırma
    public void tumPuan (){
        SQLiteDatabase db1 = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
        selectTip="1";
        puanSQL = "select  game, difficulty, max(point) from points group by  game, difficulty order by  game, difficulty";
        selectDb(db1,selectTip,puanSQL);
    }


    /////////BACK butonuna basıldığında  geri dönüş için-------------
    @Override
    public void onBackPressed() {
//////////////---------------  butonlar ekrandan silme-------------
        ViewGroup viewHolder = (ViewGroup)listPuan.getParent();
        viewHolder.removeView(listPuan);
        ViewGroup viewHolder1 = (ViewGroup)listSumImage.getParent();
        viewHolder1.removeView(listSumImage);

        /////////------ tek bir oyunun tüm verisini gösteren detayda geri dönüş tuşu ise ana puan gösterme ekranına
        if (selectTip.equals("2")){
            tumPuan();
        }
        ///////---------ana puan ekranında geri dönüş tuşu ise işseçme ekranına
        if (selectTip.equals("1")) {
            ///////////----------------intent geri dönüş------------
            Intent intentTutDonus = new Intent(Intent.ACTION_MAIN);
            intentTutDonus.addCategory(Intent.CATEGORY_HOME);
            intentTutDonus.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentTipi = "Puan";
            intentTutDonus.putExtra("tipDonus", intentTipi);
            intentTutDonus.putExtra("puanNe", selectTip);
            setResult(Activity.RESULT_OK, intentTutDonus);
            finish();
        }
    }


    ////////------select ederek puan verilerini alma , diziye koyma ve ekranda gösterme ------------
    private void selectDb(SQLiteDatabase db, String selectTipim, String sqlPuan) {

        //////////-----ekranda veriyi gösterecek liste ve başlık resmi
        listPuan = new ListView(this);
        listSumImage=new ImageView(this);
        listSumImage.setImageResource(R.drawable.listimage);
        listSumImage.setScaleType(ImageView.ScaleType.FIT_XY);

        /*if (selectTipim.equals("1")){
        puanSQL = "select  game, difficulty, max(point) from points group by  game, difficulty order by  game, difficulty";}
        if (selectTipim.equals("2")){
            puanSQL = "select  game, difficulty, max(point) from points where game='OYUN' group by  game, difficulty order by  game, difficulty";
        }*/

        //////verilerin isim olarak alınacağı dizinin tanımlanması
        String[] from = {"Game", "Difficulty", "Point"};
        //////verilerin yazılacağı hedef textview ataması
        int target[] = new int[]{R.id.grid_item_game, R.id.grid_item_difficulty, R.id.grid_item_point};


        //////////----------puanları sql ile alma intenti-------
        Cursor cursor = db.rawQuery(puanSQL, null);
        puanDetayAl = new ArrayList<puanlar>();
        int sira;
        sira = 0;
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        while (cursor.moveToNext()) {
            HashMap pointMap = new HashMap<>();
            pointMap.put(from[0], cursor.getString(0));
            pointMap.put(from[1], String.valueOf(cursor.getString(1)));
            pointMap.put(from[2], String.valueOf(cursor.getInt(2)));
            Items.add(pointMap);
            pointMap = null;
            sira = sira + 1;
            sira = sira + 1;
        }

        //////////-------verileri listede gösterecek adapter tanımlanması
        final ListAdapter adapterTest = new SimpleAdapter(this, Items, R.layout.pointlist, from, target);
        listPuan.setAdapter(adapterTest);
        listPuan.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listPuan.setItemsCanFocus(true);
        listPuan.setVerticalScrollBarEnabled(true);
        listPuan.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
        listPuan.setItemChecked(1, true);


        ///////------listeyi ekrana ekleme işlemi
        listParam = new RelativeLayout.LayoutParams((screenWidthL*4)/5, (screenHeightL*3)/5);
        imageParam= new RelativeLayout.LayoutParams((screenWidthL*4)/5,(screenHeightL)/6);


        imageParam.addRule(RelativeLayout.ALIGN_PARENT_START);
        imageParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        listParam.addRule(RelativeLayout.ALIGN_START,listSumImage.getId());
        listParam.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        listParam.leftMargin = screenWidthL/6;
        listParam.topMargin =screenHeightL/5;
        imageParam.leftMargin=screenWidthL/6;
        imageParam.topMargin=(screenHeightL/5)-((screenHeightL)/6);
        listPuan.setLayoutParams(listParam);
        listSumImage.setLayoutParams(imageParam);
        relativeLayoutPuan.addView(listSumImage,imageParam);
        relativeLayoutPuan.addView(listPuan, listParam);
      //  addContentView(listSumImage,imageParam);
      //  addContentView(listPuan, listParam);

        ///////////-------listede dokunulan sıradaki oyun tipini alma
        listPuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int updatePosition = (int) id;
               //////////------oyun tipi alma
                HashMap pointMap = new HashMap<>();
                pointMap=Items.get(updatePosition);
                selectOyun= (String) pointMap.get("Game");
                String  selectZor1=(String) pointMap.get("Difficulty");
                selectZor=Integer.valueOf(selectZor1);

                //////-----seçilen oyuna ait tüm veri için veri alma hazırlığı
                selectTip="2";
                SQLiteDatabase db1 = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
                puanSQL = "select  game, difficulty, point from points where game='"+selectOyun+"' and difficulty="+selectZor+"";

                ////////-------yeni liste için ekranı ve listeyi temizleme
                ViewGroup viewHolder = (ViewGroup)listPuan.getParent();
                viewHolder.removeView(listPuan);
                ViewGroup viewHolder1 = (ViewGroup)listSumImage.getParent();
                viewHolder1.removeView(listSumImage);
                listPuan=null;
                listParam=null;
                listSumImage=null;
                imageParam=null;

                //////////-------- yeni sql ile seçilen oyuna ait veriyi alma için select çağırma
                selectDb(db1,selectTip,puanSQL);
                return false;
            }
        });

    }
}
