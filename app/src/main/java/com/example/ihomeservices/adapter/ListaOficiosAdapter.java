package com.example.ihomeservices.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihomeservices.OnOficioClickListener;
import com.example.ihomeservices.OnTrabalhadorClickListener;
import com.example.ihomeservices.model.Oficio;

import java.util.List;

public class ListaOficiosAdapter extends RecyclerView.Adapter {

    private List<Oficio> listaOficios;
    private OnOficioClickListener onOficioClickListener;

    public ListaOficiosAdapter(List<Oficio> listaOficios, OnOficioClickListener onOficioClickListener) {
        this.listaOficios = listaOficios;
        this.onOficioClickListener = onOficioClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        return new OficioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OficioViewHolder) {
            ((OficioViewHolder) holder).text1.setText(listaOficios.get(position).toString());
            ((OficioViewHolder) holder).bind(listaOficios.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return listaOficios.size();
    }

    public class OficioViewHolder extends RecyclerView.ViewHolder {

        public TextView text1;
        private Oficio oficio;

        public OficioViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOficioClickListener.onOficioClicked(oficio);
                }
            });
        }

        public void bind(Oficio oficio) {
            this.oficio = oficio;
        }
    }
}
