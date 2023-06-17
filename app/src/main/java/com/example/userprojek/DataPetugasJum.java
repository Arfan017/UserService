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
import com.example.userprojek.adapter.PetugasJumatAdapter;
import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.modul.DatPetugasJum;
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

public class DataPetugasJum extends AppCompatActivity implements RecyclerViewInterface {
    List<DatPetugasJum> listpetugas;
    BottomNavigationView botn;
    ApiInterface api;
    RecyclerView recyclerView;
    AdapPetugas adp;
    LinearLayoutManager linearLayoutManager;
//    ProgressBar barr;
    List<ModelPetugasJumat> ListPetugasJumat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DATA PETUGAS JUMAT");
        setContentView(R.layout.activity_data_petugas_jum);
        botn = findViewById(R.id.botpetugas);
//        barr = findViewById(R.id.prograspetugas);

        ListPetugasJumat = new ArrayList<>();

        recyclerView = findViewById(R.id.recypetugas);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetDataPetugasJumat();
        botn.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataPetugasJum.this, MenuDash.class);
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
                Intent inn = new Intent(DataPetugasJum.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataPetugasJum.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataPetugasJum.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataPetugasJum.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataPetugasJum.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataPetugasJum.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataPetugasJum.this, DataPetugasJum.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }
    private void GetDataPetugasJumat() {
//        barr.setVisibility(View.GONE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_PETUGAS_JUMAT,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {

                                //getting Pertugas object from json array
                                JSONObject Petugas = array.getJSONObject(i);

                                //adding the Pertugas to Pertugas list
                                ListPetugasJumat.add(new ModelPetugasJumat(
                                        Petugas.getString("id_penceramah"),
                                        Petugas.getString("tanggal"),
                                        Petugas.getString("penceramah"),
                                        Petugas.getString("tema"),
                                        Petugas.getString("imam"),
                                        Petugas.getString("muadzin")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            PetugasJumatAdapter adapter = new PetugasJumatAdapter(DataPetugasJum.this, ListPetugasJumat, DataPetugasJum.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DataPetugasJum.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
//        Intent intent = new Intent(editjumat.this, Edit_Petugas_Jumat.class);
//        intent.putExtra("id_penceramah", ListPetugasJumat.get(position).getId_penceramah());
//        intent.putExtra("tanggal", ListPetugasJumat.get(position).getTanggal()).toString();
//        intent.putExtra("penceramah", ListPetugasJumat.get(position).getPenceramah()).toString();
//        intent.putExtra("tema", ListPetugasJumat.get(position).getTema()).toString();
//        intent.putExtra("imam", ListPetugasJumat.get(position).getImam_sholat()).toString();
//        intent.putExtra("muadzin", ListPetugasJumat.get(position).getMuadzin()).toString();
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
//                adp = new AdapPetugas(DataPetugasJum.this, listpetugas);
//                recy.setAdapter(adp);
//                adp.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<DatPetugasJum>> call, Throwable t) {
//                Toast.makeText(DataPetugasJum.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }