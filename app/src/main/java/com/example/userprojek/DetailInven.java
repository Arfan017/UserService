package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailInven extends AppCompatActivity {

    BottomNavigationView bott;
    TextView namas,kond,ket,jum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DETAIL INVEN MASJID");
        setContentView(R.layout.activity_detail_inven);

        namas = findViewById(R.id.namaaset);
        kond = findViewById(R.id.kondisi);
        ket = findViewById(R.id.ketera);
        jum = findViewById(R.id.juminven);
        bott = findViewById(R.id.botdetinven);

        String nama = getIntent().getStringExtra("namaaset");
        String kn = getIntent().getStringExtra("kondisi");
        String kete = getIntent().getStringExtra("keter");
        String jm = getIntent().getStringExtra("jumlah");

        namas.setText(nama);
        kond.setText(kn);
        ket.setText(kete);
        jum.setText(jm);

        bott.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homm:
                        Intent inten = new Intent(DetailInven.this, MenuDash.class);
                        startActivity(inten);
                        finish();
                        break;
                    case R.id.exii:
                        finish();
                        System.exit(0);
                        break;
                    case R.id.bak:
                        Intent intenn = new Intent(DetailInven.this, DataInvenMasjid.class);
                        startActivity(intenn);
                        finish();
                        break;
                }
                return false;
            }
        });

    }
}