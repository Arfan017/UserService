package com.example.userprojek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.DataKurban;
import com.example.userprojek.R;
import com.example.userprojek.modul.DatKurban;
import com.example.userprojek.modul.DatZakat;

import java.util.List;

public class AdapKurban extends RecyclerView.Adapter<AdapKurban.AdapterHolder> {
    private Context context;
    private List<DatKurban> datkur;

    public AdapKurban(Context context, List<DatKurban> datkur){
        this.context = context;
        this.datkur = datkur;
    }

    @NonNull
    @Override
    public AdapKurban.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datakurban,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        DatKurban dtmodel = datkur.get(position);
        String j = dtmodel.getJenisHewan();
        String jm = dtmodel.getPemberiKurban();

        holder.jen.setText(j);
        holder.nam.setText(jm);

    }

    @Override
    public int getItemCount() {
        return datkur.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView jen, nam;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            jen = itemView.findViewById(R.id.jenishew);
            nam = itemView.findViewById(R.id.namkurban);
        }
    }
}