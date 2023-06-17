package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class DatKegiatan{

	@SerializedName("hari")
	private String hari;

	@SerializedName("penangungjwb")
	private String penangungjwb;

	@SerializedName("foto")
	private String foto;

	@SerializedName("kegiatan")
	private String kegiatan;

	@SerializedName("id_kegiatan")
	private String idKegiatan;

	public String getHari(){
		return hari;
	}

	public String getPenangungjwb(){
		return penangungjwb;
	}

	public String getFoto(){
		return foto;
	}

	public String getKegiatan(){
		return kegiatan;
	}

	public String getIdKegiatan(){
		return idKegiatan;
	}
}