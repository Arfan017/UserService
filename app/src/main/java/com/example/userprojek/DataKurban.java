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

import com.example.userprojek.adapter.AdapKurban;
import com.example.userprojek.adapter.AdapZakat;
import com.example.userprojek.modul.DatKurban;
import com.example.userprojek.modul.DatZakat;
import com.example.userprojek.networking.ApiClient;
import com.example.userprojek.networking.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataKurban extends AppCompatActivity {
    BottomNavigationView botnavii;
    List<DatKurban> listkurban;
    ApiInterface api;
    RecyclerView recy;
    AdapKurban adp;
    LinearLayoutManager linearLayoutManager;
    ProgressBar barr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DATA KURBAN");
        setContentView(R.layout.activity_data_kurban);
        botnavii = findViewById(R.id.botkurban);
        recy = findViewById(R.id.recykurban);
        barr = findViewById(R.id.prograskurban);

        linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        baik();
        botnavii.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataKurban.this, MenuDash.class);
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
                Intent inn = new Intent(DataKurban.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataKurban.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataKurban.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataKurban.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataKurban.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataKurban.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataKurban.this, DataPetugasJum.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }

    public void baik(){
        api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DatKurban>> call = api.getDatakurban();
        call.enqueue(new Callback<List<DatKurban>>() {
            @Override
            public void onResponse(Call<List<DatKurban>> call, Response<List<DatKurban>> response) {
                barr.setVisibility(View.GONE);
                listkurban = response.body();
                adp = new AdapKurban(DataKurban.this, listkurban);
                recy.setAdapter(adp);
                adp.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<DatKurban>> call, Throwable t) {
                Toast.makeText(DataKurban.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}