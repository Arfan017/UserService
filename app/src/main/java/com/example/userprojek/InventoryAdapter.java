package com.example.userprojek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.modul.ModelInventory;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelInventory> ListInventory;

    public InventoryAdapter(Context mCtx, List<ModelInventory> listInventory, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListInventory = listInventory;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.datainven, parent, false);
        return new InventoryViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        ModelInventory dataInventory = ListInventory.get(position);

        holder.nm.setText(dataInventory.getNama_aset());
        holder.konn.setText(dataInventory.getKondisi());
        holder.jm.setText(dataInventory.getJumlah());
        holder.ket.setText(dataInventory.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return ListInventory.size();
    }

    public class InventoryViewHolder extends RecyclerView.ViewHolder {
//        TextView idp, nam, jum, sat, kon, ket;
        TextView nm, konn, jm, ket;

        CardView rel;
        public InventoryViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            nm = itemView.findViewById(R.id.namaas);
            konn = itemView.findViewById(R.id.kondisibrg);
            jm = itemView.findViewById(R.id.jumlahinven);
            ket = itemView.findViewById(R.id.keterinven);
            rel = itemView.findViewById(R.id.lininven);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
