package com.example.ihomeservices.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihomeservices.model.Oficio;

import java.util.List;

public class ListaOficiosAdapter extends RecyclerView.Adapter {

    private List<Oficio> listaOficios;
    private View.OnClickListener clickListener;

    public ListaOficiosAdapter(List<Oficio> listaOficios, View.OnClickListener clickListener) {
        this.listaOficios = listaOficios;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        itemView.setOnClickListener(clickListener);

        return new OficioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OficioViewHolder) {
            ((OficioViewHolder) holder).text1.setText(listaOficios.get(position).toString());
        }
    }

    @Override
    public int getItemCount() {
        return listaOficios.size();
    }

    public static class OficioViewHolder extends RecyclerView.ViewHolder {

        public TextView text1;

        public OficioViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(android.R.id.text1);
        }
    }
}
