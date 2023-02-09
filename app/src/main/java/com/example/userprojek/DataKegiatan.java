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

import com.example.userprojek.modul.DatKegiatan;
import com.example.userprojek.networking.ApiClient;
import com.example.userprojek.networking.ApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataKegiatan extends AppCompatActivity {
    List<DatKegiatan> listkegiatan;
    BottomNavigationView botnav;
    TableLayout tab;
    ApiInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("");
        setContentView(R.layout.activity_data_kegiatan);
        tab = findViewById(R.id.tabkegiatan);
        initViews();
        botnav = findViewById(R.id.botkegiatan);
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
        Call<List<DatKegiatan>> call = api.getDataKegiatan();
        call.enqueue(new Callback<List<DatKegiatan>>() {
            @Override
            public void onResponse(Call<List<DatKegiatan>> call, Response<List<DatKegiatan>> response) {
                if(response.isSuccessful()){
                    addHeaders();
                    listkegiatan = response.body();
                    for (int i = 0; i < listkegiatan.size(); i++) {
                        TableRow tr = new TableRow(DataKegiatan.this);
                        tr.setLayoutParams(getLayoutParams());
                        tr.addView(getRowsTextView(0, listkegiatan.get(i).getIdKegiatan(), Color.BLACK, Typeface.BOLD, R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listkegiatan.get(i).getHari(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER ));
                        tr.addView(getRowsTextView(0, listkegiatan.get(i).getWaktu(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listkegiatan.get(i).getKegiatan(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tr.addView(getRowsTextView(0, listkegiatan.get(i).getPenangungjwb(), Color.BLACK, Typeface.BOLD ,R.color.white, Gravity.CENTER));
                        tab.addView(tr, getTblLayoutParams());
                    }
                }else{
                }
            }
            @Override
            public void onFailure(Call<List<DatKegiatan>> call, Throwable t) {
                Toast.makeText(DataKegiatan.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addHeaders() {

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(getLayoutParams());

        tr.addView(getTextView(0, "ID KEGIATAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "HARI", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "WAKTU", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "KEGIATAN", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
        tr.addView(getTextView(0, "PENANGGUNG JAWAB", Color.WHITE, Typeface.BOLD, R.color.black, Gravity.CENTER));
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