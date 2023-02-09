package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatKurban {

    @SerializedName("pemberi_kurban")
    private String pemberiKurban;

    @SerializedName("jenis_hewan")
    private String jenisHewan;

    @SerializedName("id_qurban")
    private String idQurban;

    public String getPemberiKurban(){
        return pemberiKurban;
    }

    public String getJenisHewan(){
        return jenisHewan;
    }

    public String getIdQurban(){
        return idQurban;
    }
}