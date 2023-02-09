package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatPetugasJum{

    @SerializedName("penceramah")
    private String penceramah;

    @SerializedName("tema")
    private String tema;

    @SerializedName("id_penceramah")
    private String idPenceramah;

    @SerializedName("imam_shalat")
    private String imamShalat;

    @SerializedName("ptgs_adzan")
    private String ptgsAdzan;

    public String getPenceramah(){
        return penceramah;
    }

    public String getTema(){
        return tema;
    }

    public String getIdPenceramah(){
        return idPenceramah;
    }

    public String getImamShalat(){
        return imamShalat;
    }

    public String getPtgsAdzan(){
        return ptgsAdzan;
    }
}