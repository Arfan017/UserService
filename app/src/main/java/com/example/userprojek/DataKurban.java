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
    TableLayout t;
    ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("");
        setContentView(R.layout.activity_data_kurban);
        t = findViewById(R.id.tabkurban);
        initViews();
        botnavii = findViewById(R.id.botkurban);
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
        Call<List<DatKurban>> call = api.getDatakurban();
        call.enqueue(new Callback<List<DatKurban>>() {
            @Override
            public void onResponse(Call<List<DatKurban>> call, Response<List<DatKurban>> response) {
                if(response.isSuccessful()){
                    addHeaders();
                    listkurban = response.body();
                    for (int i = 0; i < listkurban.size(); i++) {
                        TableRow tr = new TableRow(DataKurban.this);
                        tr.setLayoutParams(getLayoutParams());
                        tr.addView(getRowsTextView(0, listkurban.get(i).getIdQurban(), Color.BLACK, Typeface.BOLD, R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listkurban.get(i).getPemberiKurban(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER ));
                        tr.addView(getRowsTextView(0, listkurban.get(i).getJenisHewan(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        t.addView(tr, getTblLayoutParams());
                    }
                }else{
                }
            }
            @Override
            public void onFailure(Call<List<DatKurban>> call, Throwable t) {
                Toast.makeText(DataKurban.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addHeaders() {

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());

        tr.addView(getTextView(0, "ID KURBAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "PEMBERI KURBAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "JENIS HEWAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        t.addView(tr, getTblLayoutParams());
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