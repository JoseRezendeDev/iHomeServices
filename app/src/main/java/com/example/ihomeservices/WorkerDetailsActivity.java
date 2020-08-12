package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ihomeservices.adapter.ImageGridAdapter;
import com.example.ihomeservices.model.Trabalhador;

import java.text.DecimalFormat;

public class WorkerDetailsActivity extends AppCompatActivity {

    private TextView lbNomeSobrenome;
    private TextView lbOficio;
    private TextView lbPreco;
    private TextView lbTelefone;
    private TextView lbEmail;
    private TextView lbAvaliacao;
    private ImageView imgFoto;
    private GridView gvFotos;
    private ImageGridAdapter imageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        bindInterfaceElements();

        Trabalhador trabalhador = getTrabalhadorSelecionado();

        setWorkerDetailsOnInterfaceElements(trabalhador);

        imageGridAdapter = new ImageGridAdapter(this);
        gvFotos.setAdapter(imageGridAdapter);
    }

    private void setWorkerDetailsOnInterfaceElements(Trabalhador trabalhador) {
        DecimalFormat formatarPreco = new DecimalFormat("#.00");
        DecimalFormat formatarAvaliacao = new DecimalFormat("#.0");
        lbNomeSobrenome.setText(String.format("%s %s", trabalhador.getNome(), trabalhador.getSobrenome()));
        lbOficio.setText(String.format("%s %s", lbOficio.getText(), trabalhador.getOficio()));
        lbPreco.setText(String.format("%s R$%s", lbPreco.getText(), formatarPreco.format(trabalhador.getPreco())));
        lbTelefone.setText(String.format("%s %s", lbTelefone.getText(), trabalhador.getTelefone()));
        lbEmail.setText(String.format("%s %s", lbEmail.getText(), trabalhador.getEmail()));
        lbAvaliacao.setText(String.format("%s %s", lbAvaliacao.getText(), formatarAvaliacao.format(trabalhador.getAvaliacao())));
    }

    private Trabalhador getTrabalhadorSelecionado() {
        Bundle bundle = getIntent().getExtras();
        return (Trabalhador) bundle.getSerializable("trabalhador");
    }

    private void bindInterfaceElements() {
        lbNomeSobrenome = findViewById(R.id.lbNomeSobrenome);
        lbOficio = findViewById(R.id.lbOficio);
        lbPreco = findViewById(R.id.lbPreco);
        lbTelefone = findViewById(R.id.lbTelefone);
        lbEmail = findViewById(R.id.lbEmail);
        lbAvaliacao = findViewById(R.id.lbAvaliacao);
        gvFotos = findViewById(R.id.gvFotos);
    }
}
