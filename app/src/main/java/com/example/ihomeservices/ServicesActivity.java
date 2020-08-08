package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.ihomeservices.adapter.ListaOficiosAdapter;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miMeuPerfil:
                Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
