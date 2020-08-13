package com.example.ihomeservices.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihomeservices.R;
import com.example.ihomeservices.model.Trabalhador;

import java.util.List;

public class ListaComentariosAdapter extends RecyclerView.Adapter {
    private Trabalhador trabalhador;

    public ListaComentariosAdapter(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avaliar, parent, false);

        RecyclerView.ViewHolder viewHolder = new ComentariosViewHolder(item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ComentariosViewHolder) {
            ((ComentariosViewHolder) holder).lbComentario.setText(trabalhador.getComentarios().get(position));
            ((ComentariosViewHolder) holder).rbNota.setRating(trabalhador.getNotas().get(position));
        }
    }

    @Override
    public int getItemCount() {
        return trabalhador.getComentarios().size();
    }

    private static class ComentariosViewHolder extends RecyclerView.ViewHolder {
        public TextView lbComentario;
        public RatingBar rbNota;

        private ComentariosViewHolder(@NonNull View itemView) {
            super(itemView);

            lbComentario = itemView.findViewById(R.id.lbComentario);
            rbNota = itemView.findViewById(R.id.rbNota);
        }
    }
}
