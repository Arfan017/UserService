package com.example.userprojek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.R;
import com.example.userprojek.modul.DatPetugasJum;
import com.example.userprojek.modul.DatZakat;

import java.util.List;

public class AdapZakat extends RecyclerView.Adapter<AdapZakat.AdapterHolder> {
    private Context context;
    private List<DatZakat> datzak;

    public AdapZakat(Context context, List<DatZakat> datzak){
        this.context = context;
        this.datzak = datzak;
    }

    @NonNull
    @Override
    public AdapZakat.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datazakat,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        DatZakat dtmodel = datzak.get(position);
        String j = dtmodel.getJenisZakat();
        String jm = dtmodel.getJumlahBeri();
        String wa = dtmodel.getHasil();

        holder.jen.setText(j);
        holder.jum.setText(jm);
        holder.wak.setText(wa);

    }

    @Override
    public int getItemCount() {
        return datzak.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView jen, jum, wak;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            jen = itemView.findViewById(R.id.jenzakat);
            jum = itemView.findViewById(R.id.jumzakat);
            wak = itemView.findViewById(R.id.waktuzakat);
        }
    }
}
