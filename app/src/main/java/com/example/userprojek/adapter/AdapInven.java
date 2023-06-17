package com.example.userprojek.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.DetailInven;
import com.example.userprojek.DetailKeuangan;
import com.example.userprojek.R;
import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.modul.DatKeuangan;

import java.util.List;

public class AdapInven extends RecyclerView.Adapter<AdapInven.AdapterHolder> {
    private Context context;
    private List<DatInventaris> datlist;

    public AdapInven(Context context, List<DatInventaris> datlist){
        this.context = context;
        this.datlist = datlist;
    }

    @NonNull
    @Override
    public AdapInven.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datainven,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        DatInventaris dtmodel = datlist.get(position);
        String namaber = dtmodel.getNamaAset();
        String kon = dtmodel.getKondisi();
        String money = dtmodel.getJumlah();
        String kete = dtmodel.getKeterangan();

        holder.nm.setText(namaber);
        holder.konn.setText(kon);
        holder.jm.setText(money);
        holder.ket.setText(kete);

    }

    @Override
    public int getItemCount() {
        return datlist.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nm, konn, jm, ket;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nm = itemView.findViewById(R.id.namaas);
            konn = itemView.findViewById(R.id.kondisibrg);
            jm = itemView.findViewById(R.id.jumlahinven);
            ket = itemView.findViewById(R.id.keterinven);
        }
    }
}
