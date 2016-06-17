package com.example.lucifer.morbis.model;

/**
 * Created by lucifer on 3/28/2016.
 */
public class RekmedItemDummy {

    private String date;
    private String riwayat;

    public RekmedItemDummy(String date, String riwayat) {
        this.date = date;
        this.riwayat = riwayat;
    }

    public String getRiwayat() {
        return riwayat;
    }

    public void setRiwayat(String riwayat) {
        this.riwayat = riwayat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
