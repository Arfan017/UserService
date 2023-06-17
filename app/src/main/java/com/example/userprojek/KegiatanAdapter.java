package com.example.userprojek;

import static com.example.userprojek.networking.Konfigurasi.URL_IMAGES;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.userprojek.modul.ModelKegiatan;

import java.util.List;

public class KegiatanAdapter extends RecyclerView.Adapter<KegiatanAdapter.KegiatanViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelKegiatan> ListKegiatan;

    public KegiatanAdapter(Context mCtx, List<ModelKegiatan> listKegiatan, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListKegiatan = listKegiatan;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public KegiatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listkegiatan, parent, false);
        return new KegiatanViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull KegiatanViewHolder holder, int position) {
        ModelKegiatan dataKegiatan = ListKegiatan.get(position);

        holder.TVtgl.setText(dataKegiatan.getTanggal());
        holder.TVnamakegiatan.setText(dataKegiatan.getKegiatan());
        holder.TVpenanggungJwb.setText(dataKegiatan.getPenanggungJwb());
        Glide.with(mCtx).load(URL_IMAGES+dataKegiatan.getGambar()).into(holder.Imgkegiatan);
    }

    @Override
    public int getItemCount() {
        return ListKegiatan.size();
    }

    public class KegiatanViewHolder extends RecyclerView.ViewHolder {
        TextView TVtgl, TVnamakegiatan, TVpenanggungJwb;
        ImageView Imgkegiatan;
        public KegiatanViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            TVtgl = view.findViewById(R.id.tvTgl);
            TVnamakegiatan = view.findViewById(R.id.tvkegiatan);
            TVpenanggungJwb = view.findViewById(R.id.tvPenanggungJwb);
            Imgkegiatan = itemView.findViewById(R.id.imgKegiatan);

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
