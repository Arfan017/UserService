package com.example.userprojek.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.userprojek.R;
import com.example.userprojek.modul.City;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<City> cities;
    private OnSelected onSelected;

    public CityAdapter(Context context, ArrayList<City> cities, OnSelected onSelected) {
        this.context = context;
        this.cities = cities;
        this.onSelected = onSelected;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.item_city, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final City city = cities.get(i);
        viewHolder.tvNameItemCity.setText(city.getName());
        viewHolder.mcvItemCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSelected.onSelectedItem(city);
            }
        });

        return view;
    }

    private static class ViewHolder {
        private TextView tvNameItemCity;
        private MaterialCardView mcvItemCity;

        private ViewHolder(View itemView) {
            this.tvNameItemCity = itemView.findViewById(R.id.cit);
            this.mcvItemCity = itemView.findViewById(R.id.mcv_item_city);

        }
    }

    public interface OnSelected {
        void onSelectedItem(City city);
    }
}

