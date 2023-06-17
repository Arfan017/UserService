package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.userprojek.adapter.AdapInven;
import com.example.userprojek.adapter.AdapPetugas;
import com.example.userprojek.adapter.PengurusAdapter;
import com.example.userprojek.adapter.PetugasJumatAdapter;
import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.modul.DatPetugasJum;
import com.example.userprojek.modul.ModelPengurus;
import com.example.userprojek.modul.ModelPetugasJumat;
import com.example.userprojek.networking.ApiClient;
import com.example.userprojek.networking.ApiInterface;
import com.example.userprojek.networking.Konfigurasi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class DataPengurus extends AppCompatActivity implements RecyclerViewInterface {
    BottomNavigationView botn;
    ApiInterface api;
    RecyclerView recyclerView;
//    ProgressBar barr;
    List<ModelPengurus> ListPengurus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DATA PENGURUS MASJID");
        setContentView(R.layout.activity_data_pengurus);
        botn = findViewById(R.id.botpetugas);
//        barr = findViewById(R.id.prograspetugas);

        ListPengurus = new ArrayList<>();

        recyclerView = findViewById(R.id.recypengurus);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetDataPengurus();

        botn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataPengurus.this, MenuDash.class);
                        startActivity(inten);
                        finish();
                        break;
                    case R.id.exi:
                        finish();
                        System.exit(0);
                        break;
                }
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.uangmas:
                Intent inn = new Intent(DataPengurus.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataPengurus.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataPengurus.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataPengurus.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataPengurus.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataPengurus.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataPengurus.this, DataPengurus.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }
    private void GetDataPengurus() {
//        barr.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_PENGURUS,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                //getting Pertugas object from json array
                                JSONObject Pengurus = array.getJSONObject(i);

                                //adding the Pertugas to Pertugas list
                                ListPengurus.add(new ModelPengurus(
                                        Pengurus.getString("id_pengurus"),
                                        Pengurus.getString("nama_pengurus"),
                                        Pengurus.getString("jabatan"),
                                        Pengurus.getString("no_hp")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            PengurusAdapter adapter = new PengurusAdapter(DataPengurus.this, ListPengurus, DataPengurus.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DataPengurus.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
//        Intent intent = new Intent(editjumat.this, Edit_Petugas_Jumat.class);
//        intent.putExtra("id_penceramah", ListPengurus.get(position).getId_penceramah());
//        intent.putExtra("tanggal", ListPengurus.get(position).getTanggal()).toString();
//        intent.putExtra("penceramah", ListPengurus.get(position).getPenceramah()).toString();
//        intent.putExtra("tema", ListPengurus.get(position).getTema()).toString();
//        intent.putExtra("imam", ListPengurus.get(position).getImam_sholat()).toString();
//        intent.putExtra("muadzin", ListPengurus.get(position).getMuadzin()).toString();
//        startActivity(intent);
    }
}

//    public void nani() {
//        api = ApiClient.getClient().create(ApiInterface.class);
//        Call<List<DatPetugasJum>> call = api.getDataPetugas();
//        call.enqueue(new Callback<List<DatPetugasJum>>() {
//            @Override
//            public void onResponse(Call<List<DatPetugasJum>> call, Response<List<DatPetugasJum>> response) {
//                barr.setVisibility(View.GONE);
//                listpetugas = response.body();
//                adp = new AdapPetugas(DataPengurus.this, listpetugas);
//                recy.setAdapter(adp);
//                adp.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<DatPetugasJum>> call, Throwable t) {
//                Toast.makeText(DataPengurus.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }