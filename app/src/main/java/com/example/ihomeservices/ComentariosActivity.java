package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ihomeservices.adapter.ListaComentariosAdapter;
import com.example.ihomeservices.model.Trabalhador;

public class ComentariosActivity extends AppCompatActivity {

    private Trabalhador trabalhador;
    private RecyclerView rvComentarios;
    private RecyclerView.Adapter listaComentariosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        trabalhador = (Trabalhador) getIntent().getExtras().getSerializable("trabalhador");

        listaComentariosAdapter = new ListaComentariosAdapter(trabalhador);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvComentarios = findViewById(R.id.rvComentarios);
        rvComentarios.setAdapter(listaComentariosAdapter);
        rvComentarios.setLayoutManager(layoutManager);
    }
}
