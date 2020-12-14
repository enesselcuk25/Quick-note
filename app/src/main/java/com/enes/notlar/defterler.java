package com.enes.notlar;

import java.io.Serializable;

public class defterler implements Serializable {

    private int defter_id;
    private String defter_ad;
    private  String tarih;
    private String baslik;
    private String saat;


    public defterler(int defter_id, String defter_ad, String tarih, String baslik, String saat) {
        this.defter_id = defter_id;
        this.defter_ad = defter_ad;
        this.tarih = tarih;
        this.baslik = baslik;
        this.saat = saat;
    }

    public int getDefter_id() {
        return defter_id;
    }

    public void setDefter_id(int defter_id) {
        this.defter_id = defter_id;
    }

    public String getDefter_ad() {
        return defter_ad;
    }

    public void setDefter_ad(String defter_ad) {
        this.defter_ad = defter_ad;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }
}
