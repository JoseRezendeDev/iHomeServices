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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrabalhadoresActivity extends AppCompatActivity implements OnTrabalhadorClickListener {

    private DatabaseReference databaseReference;

    private RecyclerView rvListaTrabalhadores;

    private ListaTrabalhadoresAdapter listaTrabalhadoresAdapter;

    private List<Trabalhador> trabalhadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalhadores);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final Oficio oficio = (Oficio) getIntent().getExtras().getSerializable("oficio");

        trabalhadores = new ArrayList<>();

        listaTrabalhadoresAdapter = new ListaTrabalhadoresAdapter(trabalhadores, this);

        DatabaseReference trabalhadorNode = databaseReference.child("trabalhador");
        trabalhadorNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Trabalhador trabalhadorCandidato;
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    trabalhadorCandidato = dataSnapshot.getValue(Trabalhador.class);
                    if (oficio == (Objects.requireNonNull(trabalhadorCandidato).getOficio())) {
                        trabalhadores.add(trabalhadorCandidato);
                    }
                }
                listaTrabalhadoresAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rvListaTrabalhadores = findViewById(R.id.rvListaTrabalhadores);


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
        if (item.getItemId() == R.id.miMeuPerfil) {
            databaseReference.child("trabalhador").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Trabalhador trabalhador = snapshot.getChildren().iterator().next().getValue(Trabalhador.class);

                    Intent intent = new Intent(getApplicationContext(), MeuPerfilActivity.class);
                    intent.putExtra("trabalhador", trabalhador);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onTrabalhadorClick(Trabalhador trabalhador) {
        Intent intent = new Intent(getApplicationContext(), TrabalhadorDetalhesActivity.class);

        intent.putExtra("trabalhador", trabalhador);
        startActivity(intent);
    }
}
