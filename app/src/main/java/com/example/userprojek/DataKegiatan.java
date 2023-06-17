package com.example.userprojek;

import static com.example.userprojek.networking.Konfigurasi.URL_IMAGES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.userprojek.adapter.AdapKegi;
import com.example.userprojek.modul.DatKegiatan;
import com.example.userprojek.modul.ModelKegiatan;
import com.example.userprojek.networking.ApiInterface;
import com.example.userprojek.networking.Konfigurasi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataKegiatan extends AppCompatActivity implements RecyclerViewInterface{
    BottomNavigationView botnav;
    RecyclerView recyclerView;
    AdapKegi adp;
    LinearLayoutManager linearLayoutManager;
//    ProgressBar barr;
    List<DatKegiatan> datke;
    ApiInterface api;
    List<ModelKegiatan> ListKegiatan;
    private TextView TvTanggal, TvWaktu, TvKegiatan, TvPenganggung, TvKeterangan;
    private ImageView ImgKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DATA KEGIATAN MASJID");
        setContentView(R.layout.activity_data_kegiatan);
        botnav = findViewById(R.id.botkegiatan);
        recyclerView = findViewById(R.id.recykegi);
//        barr = findViewById(R.id.prograskegi);

        ListKegiatan = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        jahat();
        GetDataKegiatan();
        botnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataKegiatan.this, MenuDash.class);
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
                Intent inn = new Intent(DataKegiatan.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataKegiatan.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataKegiatan.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataKegiatan.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataKegiatan.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataKegiatan.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataKegiatan.this, DataPetugasJum.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }

    private void GetDataKegiatan() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_KEGIATAN,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting _kegiatan object from json array
                                JSONObject _kegiatan = array.getJSONObject(i);

                                //adding the _kegiatan to _kegiatan list
                                ListKegiatan.add(new ModelKegiatan(
                                        _kegiatan.getString("id_kegiatan"),
                                        _kegiatan.getString("tanggal"),
                                        _kegiatan.getString("waktu"),
                                        _kegiatan.getString("kegiatan"),
                                        _kegiatan.getString("penanggungJwb"),
                                        _kegiatan.getString("keterangan"),
                                        _kegiatan.getString("gambar")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            KegiatanAdapter adapter = new KegiatanAdapter(DataKegiatan.this, ListKegiatan, DataKegiatan.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DataKegiatan.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DataKegiatan.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_detail_kegiatan, null);
        builder.setTitle("Detail Kegiatan");

        TvTanggal = (TextView) view.findViewById(R.id.tanggal);
        TvWaktu = (TextView) view.findViewById(R.id.waktu);
        TvKegiatan = (TextView) view.findViewById(R.id.kegiatan);
        TvPenganggung = (TextView) view.findViewById(R.id.penanggung);
        TvKeterangan = (TextView) view.findViewById(R.id.keterangan);
        ImgKegiatan = (ImageView) view.findViewById(R.id.ImgKegiatan);

        TvTanggal.setText(ListKegiatan.get(position).getTanggal());
        TvWaktu.setText(ListKegiatan.get(position).getWaktu());
        TvKegiatan.setText(ListKegiatan.get(position).getKegiatan());
        TvPenganggung.setText(ListKegiatan.get(position).getPenanggungJwb());
        TvKeterangan.setText(ListKegiatan.get(position).getKeterangan());
        Glide.with(DataKegiatan.this).load(URL_IMAGES+ListKegiatan.get(position).getGambar()).into(ImgKegiatan);

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}