package com.example.userprojek.modul;

public class ModelKeuangan {
    private int id_keuangan;
    private String harian;
    private int jumlah;
    private String tipe_transaksi;
    private String kategori_transaksi;
    private int saldo_sekarang;
    private int saldo_akhir;
    private String keterangan;

    public ModelKeuangan(int id_keuangan, String harian, int jumlah, String tipe_transaksi, String kategori_transaksi, int saldo_sekarang, int saldo_akhir, String keterangan) {
        this.id_keuangan = id_keuangan;
        this.harian = harian;
        this.jumlah = jumlah;
        this.tipe_transaksi = tipe_transaksi;
        this.kategori_transaksi = kategori_transaksi;
        this.saldo_sekarang = saldo_sekarang;
        this.saldo_akhir = saldo_akhir;
        this.keterangan = keterangan;
    }

    public ModelKeuangan() {

    }

    public int getId_keuangan() {
        return id_keuangan;
    }

    public String getHarian() {
        return harian;
    }

    public int getJumlah() {
        return jumlah;
    }
    public String getTipe_transaksi() {
        return tipe_transaksi;
    }

    public String getKategori_transaksi() {
        return kategori_transaksi;
    }

    public int getSaldo_sekarang() {
        return saldo_sekarang;
    }

    public int getSaldo_akhir() {
        return saldo_akhir;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
