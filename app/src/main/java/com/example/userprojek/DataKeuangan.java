package com.example.userprojek;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.userprojek.adapter.AdapKeuangan;
import com.example.userprojek.modul.DatKeuangan;
import com.example.userprojek.modul.DatKurban;
import com.example.userprojek.modul.ModelKeuangan;
import com.example.userprojek.modul.TotalUang;
import com.example.userprojek.networking.ApiClient;
import com.example.userprojek.networking.ApiInterface;
import com.example.userprojek.networking.Konfigurasi;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;

public class DataKeuangan extends AppCompatActivity implements RecyclerViewInterface{
    BottomNavigationView botna;
    TextView tot;
    List<ModelKeuangan> ListKeuangan;
    RecyclerView recyclerView;
    private String strJson, _url = Konfigurasi.URL_TOTAL_KEUANGAN;
    private OkHttpClient client;
    private okhttp3.Response reponse;
    private okhttp3.Request request;
    int saldo_akhir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        super.onCreate(savedInstanceState);
        this.setTitle("DATA KEUANGAN MASJID");
        setContentView(R.layout.activity_data_keuangan);

        client = new OkHttpClient();
        new GetDataKeuangan().execute();

        ListKeuangan = new ArrayList<>();

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tot = findViewById(R.id.total);
        botna = findViewById(R.id.botkeuangan);

        GetDataUang();

        botna.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.hom:
                        Intent inten = new Intent(DataKeuangan.this, MenuDash.class);
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
                Intent inn = new Intent(DataKeuangan.this, DataKeuangan.class);
                startActivity(inn);
                finish();
                break;
            case R.id.jadsol:
                Intent inte = new Intent(DataKeuangan.this, MainActivity.class);
                startActivity(inte);
                finish();
                break;
            case R.id.zak:
                Intent ite = new Intent(DataKeuangan.this, DataZakat.class);
                startActivity(ite);
                finish();
                break;
            case R.id.kurb:
                Intent it = new Intent(DataKeuangan.this, DataKurban.class);
                startActivity(it);
                finish();
                break;
            case R.id.inven:
                Intent innt = new Intent(DataKeuangan.this, DataInvenMasjid.class);
                startActivity(innt);
                finish();
                break;
            case R.id.kegmas:
                Intent intet = new Intent(DataKeuangan.this, DataKegiatan.class);
                startActivity(intet);
                finish();
                break;
            case R.id.petjum:
                Intent intett = new Intent(DataKeuangan.this, DataPetugasJum.class);
                startActivity(intett);
                finish();
                break;
        }
        return true;
    }
    public class GetDataKeuangan extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            request = new okhttp3.Request.Builder().url(_url).build();
            try {
                reponse = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                strJson = reponse.body().string();
                updateUserData(strJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserData(String strJson) {
        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);
            saldo_akhir = child.getInt("saldo_akhir");

            NumberFormat formatter = new DecimalFormat("#,###");
            String formattedNumber = formatter.format(saldo_akhir);

            tot.setText("Rp" + formattedNumber);

        } catch (JSONException e) {
//            throw new RuntimeException(e);
        }
    }

    private void GetDataUang() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Konfigurasi.URL_GET_KEUANGAN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                ListKeuangan.add(new ModelKeuangan(
                                        product.getInt("id_keuangan"),
                                        product.getString("harian"),
                                        product.getInt("jumlah"),
                                        product.getString("tipe_transaksi"),
                                        product.getString("kategori_transaksi"),
                                        product.getInt("saldo_sekarang"),
                                        product.getInt("saldo_akhir"),
                                        product.getString("keterangan")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            KeuanganAdapter adapter = new KeuanganAdapter(DataKeuangan.this, ListKeuangan, DataKeuangan.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DataKeuangan.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
    @Override
    public void onItemClick(int position) {

        int _jumlah = ListKeuangan.get(position).getJumlah();
        int Saldo_sekarang = ListKeuangan.get(position).getSaldo_sekarang();
        int Saldo_akhir = ListKeuangan.get(position).getSaldo_akhir();

        Intent intent = new Intent(DataKeuangan.this, DetailTransaksi.class);
        intent.putExtra("Hari", ListKeuangan.get(position).getHarian());
        intent.putExtra("Jumlah", ConvertToRp(_jumlah));
        intent.putExtra("Tipe_transaksi", ListKeuangan.get(position).getTipe_transaksi());
        intent.putExtra("Kategori_transaksi", ListKeuangan.get(position).getKategori_transaksi());
        intent.putExtra("Saldo_sekarang", ConvertToRp(Saldo_sekarang));
        intent.putExtra("Saldo_akhir", ConvertToRp(Saldo_akhir));
        intent.putExtra("Keterangan", ListKeuangan.get(position).getKeterangan());
        startActivity(intent);
    }

    static String ConvertToRp(int x) {
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(x);
        return formattedNumber;
    }
}