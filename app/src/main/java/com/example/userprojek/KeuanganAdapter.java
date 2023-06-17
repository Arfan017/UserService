package com.example.userprojek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userprojek.modul.ModelKeuangan;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class KeuanganAdapter extends RecyclerView.Adapter<KeuanganAdapter.KeuanganViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    private final Context mCtx;
    private final List<ModelKeuangan> ListKeuangan;

    public KeuanganAdapter(Context mCtx, List<ModelKeuangan> listKeuangan, RecyclerViewInterface recyclerViewInterface) {
        this.mCtx = mCtx;
        this.ListKeuangan = listKeuangan;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public KeuanganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_keuangan, parent, false);
        return new KeuanganViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull KeuanganAdapter.KeuanganViewHolder holder, int position) {
        ModelKeuangan dataKeuangan = ListKeuangan.get(position);

        int _jumlah = dataKeuangan.getJumlah();

        holder.tv_tgl.setText(dataKeuangan.getHarian());
        holder.tv_transaksi.setText(dataKeuangan.getTipe_transaksi());
        holder.tv_jumlah.setText(ConvertToRp(_jumlah));
    }

    @Override
    public int getItemCount() {
        return ListKeuangan.size();
    }

    public class KeuanganViewHolder extends RecyclerView.ViewHolder {
        TextView tv_tgl, tv_transaksi, tv_jumlah;
        public KeuanganViewHolder(@NonNull View view, RecyclerViewInterface recyclerViewInterface) {
            super(view);

            tv_tgl = view.findViewById(R.id.tv_tgl);
            tv_transaksi = view.findViewById(R.id.Transaksi);
            tv_jumlah = view.findViewById(R.id.Jumlah);

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

    static String ConvertToRp(int x) {
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(x);
        return formattedNumber;
    }
}
