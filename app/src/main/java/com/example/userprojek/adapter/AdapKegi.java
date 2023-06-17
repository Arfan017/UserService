package com.example.userprojek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.userprojek.R;
import com.example.userprojek.modul.DatInventaris;
import com.example.userprojek.modul.DatKegiatan;

import java.util.List;

public class AdapKegi extends RecyclerView.Adapter<AdapKegi.AdapterHolder> {
    private Context context;
    private List<DatKegiatan> datkeg;

    public AdapKegi(Context context, List<DatKegiatan> datkeg){
        this.context = context;
        this.datkeg = datkeg;
    }

    @NonNull
    @Override
    public AdapKegi.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datakegiatan,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        DatKegiatan dtmodel = datkeg.get(position);
        String hr = dtmodel.getHari();
        String keg = dtmodel.getKegiatan();
        String jw = dtmodel.getPenangungjwb();
        String ft = dtmodel.getFoto();
        String ur = "https://projekandro.000webhostapp.com/img/";
        Glide.with(context).load(ur+ft).thumbnail().into(holder.fot);

        holder.hari.setText(hr);
        holder.kegi.setText("Kegiatan: "+keg);
        holder.jwb.setText(jw);

    }

    @Override
    public int getItemCount() {
        return datkeg.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView hari, kegi, jwb;
        ImageView fot;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.harikegiatan);
            kegi = itemView.findViewById(R.id.namakegiatan);
            jwb = itemView.findViewById(R.id.jawabkegiatan);
            fot = itemView.findViewById(R.id.fotkeg);
        }
    }
}
