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
import java.util.Arrays;
import java.util.List;

public class WorkersActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private RecyclerView rvListaTrabalhadores;

    private ListaTrabalhadoresAdapter listaTrabalhadoresAdapter;

    private List<Trabalhador> trabalhadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workers);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        trabalhadores = new ArrayList<>();

        listaTrabalhadoresAdapter = new ListaTrabalhadoresAdapter(trabalhadores);

        DatabaseReference trabalhadorNode = databaseReference.child("trabalhador");
        trabalhadorNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    trabalhadores.add(dataSnapshot.getValue(Trabalhador.class));
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
