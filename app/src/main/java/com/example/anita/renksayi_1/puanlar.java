package com.example.anita.renksayi_1;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by anita on 3.8.2018.
 */

public class puanlar implements Serializable {

    private  Integer idKim;
    private String kim;
    private String oyun;
    private Integer zorluk;
    private Integer dogru;
    private Integer hata;
    private String zaman;
    private Integer suresi;
    private Integer puans;

    public puanlar(){}

    public puanlar (Integer idKim1,String kim1,String oyun1,Integer zorluk1, Integer dogru1, Integer hata1, String zaman1,Integer sure1, Integer puans1 ){
        this.idKim=idKim1;
        this.kim=kim1;
        this.oyun=oyun1;
        this.zorluk=zorluk1;
        this.dogru=dogru1;
        this.hata=hata1;
        this.suresi=sure1;
        this.zaman=zaman1;
        this.puans=puans1;
    }

    public Integer getIdKim() {return idKim;}
    public void setIdKim(Integer idKim1) {this.idKim = idKim1;}

    public String getKim() {return kim;}
    public void setKim(String kim1) {this.kim = kim1;}

    public String getOyun() {return oyun;}
    public void setOyun(String oyun1) {this.oyun = oyun1;}

    public Integer getZorluk() {return zorluk;    }
    public void setZorluk(Integer zorluk1) {this.zorluk = zorluk1;}

    public Integer getDogru() {return dogru;    }
    public void setDogru(Integer dogru1) {this.dogru = dogru1;}

    public Integer getHata() {return hata;    }
    public void setHata(Integer hata1) {this.hata = hata1;}

    public Integer getSure() {return suresi;    }
    public void setSure(Integer sure1) {this.suresi = sure1;}

    public Integer getPuans() {return puans;    }
    public void setPuans(Integer puans1) {this.puans = puans1;}

    public String getZaman() {return zaman; }
    public void setZaman(String zaman1) { this.zaman = zaman1;  }
}
