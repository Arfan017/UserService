package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.networking.ApiClient;
import com.example.userprojek.networking.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataInvenMasjid extends AppCompatActivity {

    BottomNavigationView botnavi;
    List<DatInventaris> listinven;
    TableLayout tab;
    ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("");
        setContentView(R.layout.activity_data_inven_masjid);
        tab = findViewById(R.id.tabinven);
        botnavi = findViewById(R.id.botinven);
        initViews();
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

    @NonNull
    private TableRow.LayoutParams getLayoutParams() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(1, 1, 1, 1);
        params.weight = 1;
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }

    public void initViews(){
        api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DatInventaris>> call = api.getDataInven();
        call.enqueue(new Callback<List<DatInventaris>>() {
            @Override
            public void onResponse(Call<List<DatInventaris>> call, Response<List<DatInventaris>> response) {
                if(response.isSuccessful()){
                    addHeaders();
                    listinven = response.body();
                    for (int i = 0; i < listinven.size(); i++) {
                        TableRow tr = new TableRow(DataInvenMasjid.this);
                        tr.setLayoutParams(getLayoutParams());
                        tr.addView(getRowsTextView(0, listinven.get(i).getId(), Color.BLACK, Typeface.BOLD, R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listinven.get(i).getNamaAset(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER ));
                        tr.addView(getRowsTextView(0, listinven.get(i).getJumlah(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listinven.get(i).getSatuan(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listinven.get(i).getKondisi(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listinven.get(i).getKeterangan(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tab.addView(tr, getTblLayoutParams());
                    }
                }else{
                }
            }
            @Override
            public void onFailure(Call<List<DatInventaris>> call, Throwable t) {
                Toast.makeText(DataInvenMasjid.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addHeaders() {

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());

        tr.addView(getTextView(0, "ID", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "NAMA ASET", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "JUMLAH", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "SATUAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "KONDISI", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "KETERANGAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tab.addView(tr, getTblLayoutParams());
    }
    private TextView getTextView(int id, String title, int color, int typeface, int bgColor, int grav) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setGravity(grav);
        tv.setPadding(10, 10, 10, 10);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        return tv;
    }

    private TextView getRowsTextView(int id, String title, int color, int typeface,int bgColor, int grav) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setGravity(grav);
        tv.setText(title);
        tv.setTextColor(color);
        tv.setPadding(10, 10, 10, 10);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundResource(bgColor);
        tv.setLayoutParams(getLayoutParams());
        return tv;
    }
}