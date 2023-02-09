package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatKegiatan{

    @SerializedName("hari")
    private String hari;

    @SerializedName("penangungjwb")
    private String penangungjwb;

    @SerializedName("kegiatan")
    private String kegiatan;

    @SerializedName("waktu")
    private String waktu;

    @SerializedName("id_kegiatan")
    private String idKegiatan;

    public String getHari(){
        return hari;
    }

    public String getPenangungjwb(){
        return penangungjwb;
    }

    public String getKegiatan(){
        return kegiatan;
    }

    public String getWaktu(){
        return waktu;
    }

    public String getIdKegiatan(){
        return idKegiatan;
    }
}