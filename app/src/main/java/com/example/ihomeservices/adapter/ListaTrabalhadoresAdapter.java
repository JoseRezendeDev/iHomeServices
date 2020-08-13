package com.example.ihomeservices.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihomeservices.R;
import com.example.ihomeservices.WorkerDetailsActivity;
import com.example.ihomeservices.model.Trabalhador;

import java.text.DecimalFormat;
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
        DecimalFormat formatarPreco = new DecimalFormat("#.00");
        DecimalFormat formatarAvaliacao = new DecimalFormat("#.0");

        if (holder instanceof TrabalhadorViewHolder) {
            ((TrabalhadorViewHolder) holder).lbNomeSobrenome.setText(String.format("%s %s",
                    trabalhadores.get(position).getNome(), trabalhadores.get(position).getSobrenome()));
            ((TrabalhadorViewHolder) holder).lbPreco.setText(String.format("R$%s",
                    formatarPreco.format(trabalhadores.get(position).getPreco())
            ));
            ((TrabalhadorViewHolder) holder).lbAvaliacao.setText(String.format("%s",
                    formatarAvaliacao.format(trabalhadores.get(position).getMediaNotas())
            ));
            ((TrabalhadorViewHolder) holder).imgFoto.setImageResource(getImage(position));
        }
    }

    @Override
    public int getItemCount() {
        return trabalhadores.size();
    }

    public class TrabalhadorViewHolder extends RecyclerView.ViewHolder {

        public TextView lbNomeSobrenome;
        public TextView lbPreco;
        public TextView lbAvaliacao;
        public ImageView imgFoto;

        public TrabalhadorViewHolder(@NonNull final View itemView) {
            super(itemView);

            lbNomeSobrenome = itemView.findViewById(R.id.lbNomeSobrenome);
            lbPreco = itemView.findViewById(R.id.lbPreco);
            lbAvaliacao = itemView.findViewById(R.id.lbAvaliacao);
            imgFoto = itemView.findViewById(R.id.imgFoto);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), WorkerDetailsActivity.class);
                    intent.putExtra("trabalhador", trabalhadores.get(1));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    private int getImage(int position) {
        switch (position % 6) {
            case 0:
                return R.drawable.unhas_manicure1;
            case 1:
                return R.drawable.unhas_manicure2;
            case 2:
                return R.drawable.unhas_manicure3;
            case 3:
                return R.drawable.unhas_manicure4;
            case 4:
                return R.drawable.unhas_manicure5;
            case 5:
                return R.drawable.unhas_manicure6;
            default:
                return R.drawable.ic_launcher_background;
        }
    }
}
