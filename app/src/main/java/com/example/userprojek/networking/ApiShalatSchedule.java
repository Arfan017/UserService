package com.example.userprojek.networking;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.userprojek.modul.Shalat;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.MessageFormat;
import java.util.ArrayList;

public class ApiShalatSchedule {
    private Context context;
    private OnResponse onResponse;

    public ApiShalatSchedule(Context context, OnResponse onResponse) {
        this.context = context;
        this.onResponse = onResponse;
    }

    public void getShalat(String idCity) {
        AndroidThreeTen.init(context);
        String url = MessageFormat.format(Api.shalatSchedule, idCity, LocalDate.now());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Shalat shalat = new Shalat();
                    ArrayList<String> names = new ArrayList<>();
                    ArrayList<LocalTime> times = new ArrayList<>();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                    JSONObject json = new JSONObject(response);
                    JSONObject queryJson = json.getJSONObject("query");
                    JSONObject jadwalJson = json.getJSONObject("jadwal");
                    JSONObject dataJson = jadwalJson.getJSONObject("data");
                    shalat.setCityCode(queryJson.getString("kota"));
                    shalat.setDateToday(dataJson.getString("tanggal"));
                    names.add("Imsak");
                    times.add(LocalTime.parse(dataJson.getString("imsak"), dateTimeFormatter));
                    names.add("Subuh");
                    times.add(LocalTime.parse(dataJson.getString("subuh"), dateTimeFormatter));
                    names.add("Terbit");
                    times.add(LocalTime.parse(dataJson.getString("terbit"), dateTimeFormatter));
                    names.add("Dhuha");
                    times.add(LocalTime.parse(dataJson.getString("dhuha"), dateTimeFormatter));
                    names.add("Dzuhur");
                    times.add(LocalTime.parse(dataJson.getString("dzuhur"), dateTimeFormatter));
                    names.add("Ashar");
                    times.add(LocalTime.parse(dataJson.getString("ashar"), dateTimeFormatter));
                    names.add("Magrib");
                    times.add(LocalTime.parse(dataJson.getString("maghrib"), dateTimeFormatter));
                    names.add("Isya");
                    times.add(LocalTime.parse(dataJson.getString("isya"), dateTimeFormatter));

                    shalat.setName(names);
                    shalat.setTimes(times);

                    onResponse.onResponse(shalat);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onResponse.errorResponse(error);
            }
        });

        execute(stringRequest);
    }

    private void execute(StringRequest stringRequest) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }

    public interface OnResponse {
        void onResponse(Shalat shalat);

        void errorResponse(VolleyError error);
    }
}
