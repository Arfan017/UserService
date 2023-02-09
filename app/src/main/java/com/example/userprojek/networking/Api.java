package com.example.userprojek.networking;

public interface Api {
    String baseUrl = "https://api.banghasan.com/";
    String allCity = baseUrl + "sholat/format/json/kota";
    String shalatSchedule = baseUrl + "sholat/format/json/jadwal/kota/{0}/tanggal/{1}";
}
