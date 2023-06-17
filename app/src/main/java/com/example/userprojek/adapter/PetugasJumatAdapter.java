package com.example.userprojek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.R;
import com.example.userprojek.RecyclerViewInterface;
import com.example.userprojek.modul.ModelPetugasJumat;

import java.util.List;

public class PetugasJumatAdapter extends RecyclerView.Adapter<PetugasJumatAdapter.PetugasJumatViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelPetugasJumat> ListPetugasJumat;

    public PetugasJumatAdapter(Context mCtx, List<ModelPetugasJumat> listPetugasJumat, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListPetugasJumat = listPetugasJumat;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public PetugasJumatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.datapetugas, parent, false);
        return new PetugasJumatViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PetugasJumatViewHolder holder, int position) {
        ModelPetugasJumat dataPetugasJumat = ListPetugasJumat.get(position);

        holder.tgl.setText(dataPetugasJumat.getTanggal());
        holder.cr.setText(dataPetugasJumat.getPenceramah());
        holder.tm.setText(dataPetugasJumat.getTema());
        holder.ima.setText(dataPetugasJumat.getImam_sholat());
        holder.az.setText(dataPetugasJumat.getMuadzin());
    }

    @Override
    public int getItemCount() {
        return ListPetugasJumat.size();
    }

    public class PetugasJumatViewHolder extends RecyclerView.ViewHolder {
        TextView cr, tm, ima, az, tgl;

        CardView rel;

        public PetugasJumatViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            tgl = itemView.findViewById(R.id.TVtgl);
            cr = itemView.findViewById(R.id.khatib);
            tm = itemView.findViewById(R.id.tema);
            az = itemView.findViewById(R.id.adzan);
            ima = itemView.findViewById(R.id.imam);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
