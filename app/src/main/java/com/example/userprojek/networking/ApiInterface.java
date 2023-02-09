package com.example.userprojek.networking;

import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.modul.DatKegiatan;
import com.example.userprojek.modul.DatPetugasJum;
import com.example.userprojek.modul.DatZakat;
import com.example.userprojek.modul.DatKurban;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("data_zakat.php")
    Call<List<DatZakat>> getDataZakat();

    @GET("kurban.php")
    Call<List<DatKurban>> getDatakurban();

    @GET("investaris.php")
    Call<List<DatInventaris>> getDataInven();

    @GET("kegiatanmesjid.php")
    Call<List<DatKegiatan>> getDataKegiatan();

    @GET("petugas_jumat.php")
    Call<List<DatPetugasJum>> getDataPetugas();
}
