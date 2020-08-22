package com.example.ihomeservices.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihomeservices.OnTrabalhadorClickListener;
import com.example.ihomeservices.R;
import com.example.ihomeservices.model.Trabalhador;

import java.text.DecimalFormat;
import java.util.List;

public class ListaTrabalhadoresAdapter extends RecyclerView.Adapter {
    private List<Trabalhador> trabalhadores;
    private OnTrabalhadorClickListener onTrabalhadorClickListener;

    public ListaTrabalhadoresAdapter(List<Trabalhador> trabalhadores, OnTrabalhadorClickListener onTrabalhadorClickListener) {
        this.trabalhadores = trabalhadores;
        this.onTrabalhadorClickListener = onTrabalhadorClickListener;
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
                    formatarAvaliacao.format(trabalhadores.get(position).calcularMediaNotas())
            ));
            ((TrabalhadorViewHolder) holder).imgFoto.setImageResource(getImage(position));
            ((TrabalhadorViewHolder) holder).bind(trabalhadores.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return trabalhadores.size();
    }

    public class TrabalhadorViewHolder extends RecyclerView.ViewHolder {

        private TextView lbNomeSobrenome;
        private TextView lbPreco;
        private TextView lbAvaliacao;
        private ImageView imgFoto;
        private Trabalhador trabalhador;

        public TrabalhadorViewHolder(@NonNull final View itemView) {
            super(itemView);

            lbNomeSobrenome = itemView.findViewById(R.id.lbNomeSobrenome);
            lbPreco = itemView.findViewById(R.id.lbPreco);
            lbAvaliacao = itemView.findViewById(R.id.lbAvaliacao);
            imgFoto = itemView.findViewById(R.id.imgFoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTrabalhadorClickListener.onTrabalhadorClick(trabalhador);
                }
            });
        }

        public void bind(Trabalhador trabalhador) {
            this.trabalhador = trabalhador;
        }
    }

    private int getImage(int position) {
        switch (trabalhadores.get(position).getOficio()) {
            case PINTOR:
                return R.drawable.pintor1;
            case JARDINEIRO:
                return R.drawable.jardineiro1;
            case MANICURE:
                return R.drawable.manicure1;
            case CABELEIREIRO:
                return R.drawable.cabeleireiro1;
            case PEDREIRO:
                return R.drawable.pedreiro1;
            case PISCINEIRO:
                return R.drawable.piscineiro1;
            default:
                return R.drawable.ic_launcher_background;
        }
    }
}
