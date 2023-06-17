package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatKeuangan{

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("id_keuangan")
	private String idKeuangan;

	@SerializedName("harian")
	private String harian;

	@SerializedName("nama_pemberi")
	private String namaPemberi;

	@SerializedName("alamat")
	private String alamat;

	public String getNoHp(){
		return noHp;
	}

	public String getJumlah(){
		return jumlah;
	}

	public String getIdKeuangan(){
		return idKeuangan;
	}

	public String getHarian(){
		return harian;
	}

	public String getNamaPemberi(){
		return namaPemberi;
	}

	public String getAlamat(){
		return alamat;
	}
}