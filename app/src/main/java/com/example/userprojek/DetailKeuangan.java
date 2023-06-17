package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailKeuangan extends AppCompatActivity {
 TextView namp,nop,al,tang,jum;
    BottomNavigationView bott;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("DETAIL KEUANGAN");
        setContentView(R.layout.activity_detail_keuangan);

        namp = findViewById(R.id.namapemberi);
        nop = findViewById(R.id.nhp);
        al = findViewById(R.id.alam);
        tang = findViewById(R.id.tnggluang);
        jum = findViewById(R.id.bayar);
        bott = findViewById(R.id.botdetuang);

        String nampe = getIntent().getStringExtra("namapemberi");
        String hp = getIntent().getStringExtra("nohp");
        String alm = getIntent().getStringExtra("alamat");
        String tg = getIntent().getStringExtra("tanggal");
        String jm = getIntent().getStringExtra("jumlah");

        namp.setText(nampe);
        nop.setText(hp);
        al.setText(alm);
        tang.setText(tg);
        jum.setText(jm);


        bott.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homm:
                        Intent inten = new Intent(DetailKeuangan.this, MenuDash.class);
                        startActivity(inten);
                        finish();
                        break;
                    case R.id.exii:
                        finish();
                        System.exit(0);
                        break;
                    case R.id.bak:
                        Intent intenn = new Intent(DetailKeuangan.this, DataKeuangan.class);
                        startActivity(intenn);
                        finish();
                        break;
                }
                return false;
            }
        });

    }
}