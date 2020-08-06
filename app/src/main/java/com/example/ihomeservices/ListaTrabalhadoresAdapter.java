package com.example.ihomeservices;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihomeservices.model.Trabalhador;

import java.util.List;

public class ListaTrabalhadoresAdapter extends RecyclerView.Adapter {
    private List<Trabalhador> trabalhadores;

    public ListaTrabalhadoresAdapter(List<Trabalhador> trabalhadores) {
        this.trabalhadores = trabalhadores;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View trabalhadorViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_trabalhador, parent, false);

        return new TrabalhadorViewHolder(trabalhadorViewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TrabalhadorViewHolder) {
            ((TrabalhadorViewHolder) holder).lbNomeSobrenome.setText(String.format("%s %s",
                    trabalhadores.get(position).getNome(), trabalhadores.get(position).getSobrenome()));
            ((TrabalhadorViewHolder) holder).lbPreco.setText(String.format("%f",
                    trabalhadores.get(position).getPreco()
            ));
            ((TrabalhadorViewHolder) holder).lbAvaliacao.setText(String.format("%f",
                    trabalhadores.get(position).getAvaliacao()
            ));
            ((TrabalhadorViewHolder) holder).imgFoto.setImageResource(R.drawable.unhas_manicure4);
        }
    }

    @Override
    public int getItemCount() {
        return trabalhadores.size();
    }

    public static class TrabalhadorViewHolder extends RecyclerView.ViewHolder {

        public TextView lbNomeSobrenome;
        public TextView lbPreco;
        public TextView lbAvaliacao;
        public ImageView imgFoto;

        public TrabalhadorViewHolder(@NonNull View itemView) {
            super(itemView);

            lbNomeSobrenome = itemView.findViewById(R.id.lbNomeSobrenome);
            lbPreco = itemView.findViewById(R.id.lbPreco);
            lbAvaliacao = itemView.findViewById(R.id.lbAvaliacao);
            imgFoto = itemView.findViewById(R.id.imgFoto);
        }
    }
}
