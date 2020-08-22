package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.ihomeservices.adapter.ListaComentariosAdapter;
import com.example.ihomeservices.model.Trabalhador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ComentariosActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private EditText txtComentario;
    private RatingBar rbNota;
    private Button btnAddComentario;
    private RecyclerView rvComentarios;
    private RecyclerView.Adapter listaComentariosAdapter;

    private Trabalhador trabalhador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);

        bindInterfaceElements();

        trabalhador = (Trabalhador) getIntent().getExtras().getSerializable("trabalhador");

        listaComentariosAdapter = new ListaComentariosAdapter(trabalhador);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvComentarios.setAdapter(listaComentariosAdapter);
        rvComentarios.setLayoutManager(layoutManager);

        DatabaseReference trabalhadorNode = databaseReference.child("trabalhador").child(trabalhador.getId());
        trabalhadorNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaComentariosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnAddComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trabalhador.addAvaliacao(txtComentario.getText().toString(), Math.round(rbNota.getRating()));
                databaseReference.child("trabalhador").child(trabalhador.getId()).setValue(trabalhador);

                txtComentario.setText("");
                rbNota.setRating(1);
            }
        });
    }

    private void bindInterfaceElements() {
        txtComentario = findViewById(R.id.txtComentario);
        rbNota = findViewById(R.id.rbNota);
        btnAddComentario = findViewById(R.id.btnAddComentario);
        rvComentarios = findViewById(R.id.rvComentarios);
    }
}
