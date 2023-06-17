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
import com.example.userprojek.R;
import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.modul.DatPetugasJum;

import java.util.List;

public class AdapPetugas extends RecyclerView.Adapter<AdapPetugas.AdapterHolder> {
    private Context context;
    private List<DatPetugasJum> datpet;

    public AdapPetugas(Context context, List<DatPetugasJum> datpet){
        this.context = context;
        this.datpet = datpet;
    }

    @NonNull
    @Override
    public AdapPetugas.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datapetugas,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        DatPetugasJum dtmodel = datpet.get(position);
        String khat = dtmodel.getPenceramah();
        String tem = dtmodel.getTema();
        String imm = dtmodel.getImamShalat();
        String azz = dtmodel.getPtgsAdzan();

        holder.cr.setText(khat);
        holder.tm.setText(tem);
        holder.ima.setText(imm);
        holder.az.setText(azz);

    }

    @Override
    public int getItemCount() {
        return datpet.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView cr, tm, ima, az;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            cr = itemView.findViewById(R.id.khatib);
            tm = itemView.findViewById(R.id.tema);
            az = itemView.findViewById(R.id.adzan);
            ima = itemView.findViewById(R.id.imam);
        }
    }
}