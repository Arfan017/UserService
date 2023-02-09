package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatInventaris{

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("kondisi")
    private String kondisi;

    @SerializedName("nama_aset")
    private String namaAset;

    @SerializedName("satuan")
    private String satuan;

    @SerializedName("id")
    private String id;

    public String getKeterangan(){
        return keterangan;
    }

    public String getJumlah(){
        return jumlah;
    }

    public String getKondisi(){
        return kondisi;
    }

    public String getNamaAset(){
        return namaAset;
    }

    public String getSatuan(){
        return satuan;
    }

    public String getId(){
        return id;
    }
}