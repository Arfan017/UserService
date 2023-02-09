package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatZakat {

    @SerializedName("Jenis_zakat")
    private String jenisZakat;

    @SerializedName("id_zakat")
    private String idZakat;

    @SerializedName("jumlah_beri")
    private String jumlahBeri;

    @SerializedName("hasil")
    private String hasil;

    public String getJenisZakat(){
        return jenisZakat;
    }

    public String getIdZakat(){
        return idZakat;
    }

    public String getJumlahBeri(){
        return jumlahBeri;
    }

    public String getHasil(){
        return hasil;
    }
}