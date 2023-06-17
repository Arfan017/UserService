package com.example.userprojek;

import static com.example.userprojek.networking.Konfigurasi.URL_IMAGES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.userprojek.adapter.AdapInven;
import com.example.userprojek.modul.ModelInventory;
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

public class DataInvenMasjid extends AppCompatActivity implements RecyclerViewInterface {
    BottomNavigationView botnavi;
    List<ModelInventory> ListInventaris;
    ApiInterface api;
    RecyclerView recyclerView;
//    ProgressBar barr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DATA INVENTARIS MASJID");
        setContentView(R.layout.activity_data_inven_masjid);

        botnavi = findViewById(R.id.botinven);
        recyclerView = findViewById(R.id.recyinven);
//        barr = findViewById(R.id.prograsinven);

        ListInventaris = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetDataInventory();

        botnavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataInvenMasjid.this, MenuDash.class);
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
        switch (item.getItemId()){
            case R.id.uangmas:
                Intent inn = new Intent(DataInvenMasjid.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataInvenMasjid.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataInvenMasjid.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataInvenMasjid.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataInvenMasjid.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataInvenMasjid.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataInvenMasjid.this, DataPetugasJum.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }

    private void GetDataInventory() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_INVENTORY,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting _Inventory object from json array
                                JSONObject _Inventory = array.getJSONObject(i);

                                //adding the _Inventaris to _Inventaris list
                                ListInventaris.add(new ModelInventory(
                                        _Inventory.getString("id_inventaris"),
                                        _Inventory.getString("nama_aset"),
                                        _Inventory.getString("jumlah"),
                                        _Inventory.getString("satuan"),
                                        _Inventory.getString("kondisi"),
                                        _Inventory.getString("keterangan")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            InventoryAdapter adapter = new InventoryAdapter(DataInvenMasjid.this, ListInventaris, DataInvenMasjid.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DataInvenMasjid.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(DataInventaris.this);
//        View view = getLayoutInflater().inflate(R.layout.dialog_detail_Inventaris, null);
//        builder.setTitle("Detail Inventaris");
//
//        TvTanggal = (TextView) view.findViewById(R.id.tanggal);
//        TvWaktu = (TextView) view.findViewById(R.id.waktu);
//        TvInventaris = (TextView) view.findViewById(R.id.Inventaris);
//        TvPenganggung = (TextView) view.findViewById(R.id.penanggung);
//        TvKeterangan = (TextView) view.findViewById(R.id.keterangan);
//        ImgInventaris = (ImageView) view.findViewById(R.id.ImgInventaris);
//
//        TvTanggal.setText(ListInventaris.get(position).getTanggal());
//        TvWaktu.setText(ListInventaris.get(position).getWaktu());
//        TvInventaris.setText(ListInventaris.get(position).getInventaris());
//        TvPenganggung.setText(ListInventaris.get(position).getPenanggungJwb());
//        TvKeterangan.setText(ListInventaris.get(position).getKeterangan());
//        Glide.with(DataInventaris.this).load(URL_IMAGES+ListInventaris.get(position).getGambar()).into(ImgInventaris);
//
//        builder.setView(view);
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }
}





//    public void oke(){
//        api = ApiClient.getClient().create(ApiInterface.class);
//        Call<List<DatInventaris>> call = api.getDataInven();
//        call.enqueue(new Callback<List<DatInventaris>>() {
//            @Override
//            public void onResponse(Call<List<DatInventaris>> call, Response<List<DatInventaris>> response) {
//                barr.setVisibility(View.GONE);
//                listinven = response.body();
//                adp = new AdapInven(DataInvenMasjid.this, listinven);
//                recy.setAdapter(adp);
//                adp.notifyDataSetChanged();
//            }
//            @Override
//            public void onFailure(Call<List<DatInventaris>> call, Throwable t) {
//                    Toast.makeText(DataInvenMasjid.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }