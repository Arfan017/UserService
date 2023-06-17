package com.example.userprojek.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.DetailKeuangan;
import com.example.userprojek.R;
import com.example.userprojek.modul.DatKeuangan;

import java.util.List;

public class AdapKeuangan extends RecyclerView.Adapter<AdapKeuangan.AdapterHolder> {
    private Context context;
    private List<DatKeuangan> datlist;

    public AdapKeuangan(Context context, List<DatKeuangan> datlist){
        this.context = context;
        this.datlist = datlist;
    }

    @NonNull
    @Override
    public AdapKeuangan.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datakeuangan,parent, false);
        AdapterHolder holder = new AdapterHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {

        DatKeuangan dtmodel = datlist.get(position);
        String namaber = dtmodel.getNamaPemberi();
        String tangga = dtmodel.getHarian();
        String money = dtmodel.getJumlah();

        holder.nmpem.setText(namaber);
        holder.tangg.setText(tangga);
        holder.ua.setText("RP"+money);

        holder.linear.setOnClickListener(v ->{

            Intent inten = new Intent(context, DetailKeuangan.class);
            inten.putExtra("namapemberi", dtmodel.getNamaPemberi());
            inten.putExtra("nohp", dtmodel.getNoHp());
            inten.putExtra("alamat", dtmodel.getAlamat());
            inten.putExtra("tanggal", dtmodel.getHarian());
            inten.putExtra("jumlah", dtmodel.getJumlah());
            context.startActivity(inten);
        });
    }

    @Override
    public int getItemCount() {
        return datlist.size();
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        TextView nmpem, tangg, ua;
        LinearLayout linear;
        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            nmpem = itemView.findViewById(R.id.nama_pem);
            tangg = itemView.findViewById(R.id.tanggaluang);
            ua = itemView.findViewById(R.id.uangberi);
            linear = itemView.findViewById(R.id.linkeuagan);
        }
    }
}

