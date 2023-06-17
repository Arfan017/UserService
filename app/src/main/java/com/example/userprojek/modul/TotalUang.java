package com.example.userprojek.modul;

import com.google.gson.annotations.SerializedName;

public class TotalUang{
	@SerializedName("total_keseluruhan")
	private String totalKeseluruhan;
	public String getTotalKeseluruhan(){
		return totalKeseluruhan;
	}
}