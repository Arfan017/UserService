package com.example.userprojek;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailTransaksi extends AppCompatActivity {
    TextView Taggal, TipeTransaksi, KategoriTransaksi, JumlahTransaksi, saldo_sekarang, saldo_akhir, KeteranganTransaksi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        this.setTitle("DETAIL TRASAKSI");

        String Hari = getIntent().getStringExtra("Hari");
        String Jumlah = String.valueOf(getIntent().getStringExtra("Jumlah"));
        String Tipe_transaksi = getIntent().getStringExtra("Tipe_transaksi");
        String Kategori_transaksi = getIntent().getStringExtra("Kategori_transaksi");
        String Saldo_sekarang = String.valueOf(getIntent().getStringExtra("Saldo_sekarang"));
        String Saldo_akhir = String.valueOf(getIntent().getStringExtra("Saldo_akhir"));
        String Keterangan = getIntent().getStringExtra("Keterangan");

        Taggal = findViewById(R.id.TglTransaksi);
        TipeTransaksi = findViewById(R.id.TipeTransaksi);
        KategoriTransaksi = findViewById(R.id.KategoriTransaksi);
        JumlahTransaksi = findViewById(R.id.JumlahTransaksi);
        saldo_sekarang = findViewById(R.id.saldo_sekarang);
        saldo_akhir = findViewById(R.id.saldo_terakhir);
        KeteranganTransaksi = findViewById(R.id.KeteranganTransaksi);

        Taggal.setText(Hari);
        TipeTransaksi.setText(Tipe_transaksi);
        KategoriTransaksi.setText(Kategori_transaksi);
        JumlahTransaksi.setText(Jumlah);
        saldo_sekarang.setText(Saldo_sekarang);
        saldo_akhir.setText(Saldo_akhir);
        KeteranganTransaksi.setText(Keterangan);
    }
}