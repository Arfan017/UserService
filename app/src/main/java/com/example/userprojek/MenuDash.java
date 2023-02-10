package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MenuDash extends AppCompatActivity implements View.OnClickListener {

    ImageSlider imagslid;
    BottomNavigationView botnavigas;
    CardView jdwl,inve,kegi,keu,kur,petug,zak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("");
        setContentView(R.layout.activity_menu_dash);
        jdwl = findViewById(R.id.jadwal);
        keu = findViewById(R.id.keuangan);
        kegi = findViewById(R.id.kegiatan);
        petug = findViewById(R.id.petugas);
        zak = findViewById(R.id.zakat);
        kur = findViewById(R.id.kurban);
        inve = findViewById(R.id.inven);
        imagslid = findViewById(R.id.imaslide);

        jdwl.setOnClickListener(this);
        keu.setOnClickListener(this);
        kegi.setOnClickListener(this);
        petug.setOnClickListener(this);
        zak.setOnClickListener(this);
        kur.setOnClickListener(this);
        inve.setOnClickListener(this);

        imagesl();
        botnavigas = findViewById(R.id.botmenu);
        botnavigas.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(MenuDash.this, MenuDash.class);
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

    public void imagesl(){
        ArrayList<SlideModel> slidemod = new ArrayList<>();
        slidemod.add(new SlideModel(R.drawable.masjid1, ScaleTypes.FIT));
        slidemod.add(new SlideModel(R.drawable.masjid2, ScaleTypes.FIT));
        slidemod.add(new SlideModel(R.drawable.masjid3, ScaleTypes.FIT));
        slidemod.add(new SlideModel(R.drawable.masjid4, ScaleTypes.FIT));
        imagslid.setImageList(slidemod, ScaleTypes.FIT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jadwal:
                Intent in = new Intent(MenuDash.this, MainActivity.class);
                startActivity(in);
                finish();
                break;
            case R.id.keuangan:
                Intent inn = new Intent(MenuDash.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.kegiatan:
                Intent innn = new Intent(MenuDash.this, DataKegiatan.class);
                startActivity(innn);
                finish();
                break;
            case R.id.petugas:
                Intent i = new Intent(MenuDash.this, DataPetugasJum.class);
                startActivity(i);
                finish();
                break;
            case R.id.kurban:
                Intent ii = new Intent(MenuDash.this, DataKurban.class);
                startActivity(ii);
                finish();
                break;
            case R.id.zakat:
                Intent inte = new Intent(MenuDash.this, DataZakat.class);
                startActivity(inte);
                finish();
                break;
            case R.id.inven:
                Intent intent = new Intent(MenuDash.this, DataInvenMasjid.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}