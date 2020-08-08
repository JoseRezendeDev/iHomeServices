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

import com.example.ihomeservices.adapter.ListaTrabalhadoresAdapter;
import com.example.ihomeservices.model.Oficio;
import com.example.ihomeservices.model.Trabalhador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkersActivity extends AppCompatActivity {

    private RecyclerView rvListaTrabalhadores;

    private ListaTrabalhadoresAdapter listaTrabalhadoresAdapter;

    private List<Trabalhador> trabalhadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);

        trabalhadores = new ArrayList<>(Arrays.asList(
                getMockedTrabalhador1(),
                getMockedTrabalhador2(),
                getMockedTrabalhador1(),
                getMockedTrabalhador2(),
                getMockedTrabalhador1(),
                getMockedTrabalhador2(),
                getMockedTrabalhador1(),
                getMockedTrabalhador2()
                ));

        rvListaTrabalhadores = findViewById(R.id.rvListaTrabalhadores);

        listaTrabalhadoresAdapter = new ListaTrabalhadoresAdapter(trabalhadores);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvListaTrabalhadores.setLayoutManager(layoutManager);
        rvListaTrabalhadores.setAdapter(listaTrabalhadoresAdapter);

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

    private Trabalhador getMockedTrabalhador1() {
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome("Jose");
        trabalhador.setSobrenome("Rezende");
        trabalhador.setEmail("jose@gmail.com");
        trabalhador.setTelefone("16991223344");
        trabalhador.setPreco(120.0);
        trabalhador.setOficio(Oficio.PEDREIRO);
        trabalhador.setAvaliacao(Arrays.asList(4, 5, 5, 4, 4, 4));
        return trabalhador;
    };

    private Trabalhador getMockedTrabalhador2() {
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome("Vitor");
        trabalhador.setSobrenome("Margoto");
        trabalhador.setEmail("jose@gmail.com");
        trabalhador.setTelefone("11988776655");
        trabalhador.setPreco(130.0);
        trabalhador.setOficio(Oficio.PEDREIRO);
        trabalhador.setAvaliacao(Arrays.asList(4, 2, 2, 1, 1));
        return trabalhador;
    };
}
