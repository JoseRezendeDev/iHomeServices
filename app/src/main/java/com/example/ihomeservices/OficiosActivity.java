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

import com.example.ihomeservices.adapter.ListaOficiosAdapter;
import com.example.ihomeservices.model.Oficio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OficiosActivity extends AppCompatActivity implements OnOficioClickListener {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private RecyclerView rvListaOficios;

    private ListaOficiosAdapter listaOficiosAdapter;

    private List<Oficio> listaOficios;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficios);

        bindInterfaceElements();

        listaOficios = new ArrayList<>();

        listaOficiosAdapter = new ListaOficiosAdapter(listaOficios, this);

        layoutManager = new LinearLayoutManager(this);
        rvListaOficios.setLayoutManager(layoutManager);
        rvListaOficios.setAdapter(listaOficiosAdapter);
        rvListaOficios.setHasFixedSize(true);

        databaseReference.child("oficio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    listaOficios.add(dataSnapshot.getValue(Oficio.class));
                }
                listaOficiosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void bindInterfaceElements() {
        rvListaOficios = findViewById(R.id.rvListaOficios);
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

    @Override
    public void onOficioClicked(Oficio oficio) {
        Intent intent = new Intent(getApplicationContext(), TrabalhadoresActivity.class);

        intent.putExtra("oficio", oficio);
        startActivity(intent);
    }
}
