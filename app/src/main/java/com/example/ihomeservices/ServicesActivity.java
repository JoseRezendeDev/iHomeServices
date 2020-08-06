package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.ihomeservices.model.Oficio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {

    private RecyclerView rvListaOficios;

    private ListaOficiosAdapter listaOficiosAdapter;

    private List<Oficio> listaOficios;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        listaOficios = new ArrayList<>(Arrays.asList(
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.CABELEIREIRO,
                Oficio.JARDINEIRO,
                Oficio.MANICURE,
                Oficio.PEDREIRO,
                Oficio.PINTOR,
                Oficio.PISCINEIRO));

        rvListaOficios = findViewById(R.id.rvListaOficios);

        View.OnClickListener onListaOficiosClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkersActivity.class);

                startActivity(intent);
            }
        };

        listaOficiosAdapter = new ListaOficiosAdapter(listaOficios, onListaOficiosClickListener);

        layoutManager = new LinearLayoutManager(this);

        rvListaOficios.setLayoutManager(layoutManager);
        rvListaOficios.setAdapter(listaOficiosAdapter);
        rvListaOficios.setHasFixedSize(true);

    }
}
