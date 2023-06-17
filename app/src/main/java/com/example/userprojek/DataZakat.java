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

import com.example.userprojek.adapter.AdapPetugas;
import com.example.userprojek.adapter.AdapZakat;
import com.example.userprojek.modul.DatPetugasJum;
import com.example.userprojek.modul.DatZakat;
import com.example.userprojek.networking.ApiClient;
import com.example.userprojek.networking.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataZakat extends AppCompatActivity {

    List<DatZakat> listzakat;
    BottomNavigationView bott;
    ApiInterface api;
    RecyclerView recy;
    AdapZakat adp;
    LinearLayoutManager linearLayoutManager;
    ProgressBar barr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DATA ZAKAT");
        setContentView(R.layout.activity_data_zakat);
        bott = findViewById(R.id.botzakat);
        recy = findViewById(R.id.recyzakat);
        barr = findViewById(R.id.prograszakat);

        linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        baiklah();
        bott.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataZakat.this, MenuDash.class);
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
                Intent inn = new Intent(DataZakat.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataZakat.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataZakat.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataZakat.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataZakat.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataZakat.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataZakat.this, DataPetugasJum.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }
    public void baiklah(){
        api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DatZakat>> call = api.getDataZakat();
        call.enqueue(new Callback<List<DatZakat>>() {
            @Override
            public void onResponse(Call<List<DatZakat>> call, Response<List<DatZakat>> response) {
                barr.setVisibility(View.GONE);
                listzakat = response.body();
                adp = new AdapZakat(DataZakat.this, listzakat);
                recy.setAdapter(adp);
                adp.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<DatZakat>> call, Throwable t) {
                Toast.makeText(DataZakat.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}