package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ihomeservices.model.Trabalhador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeuPerfilActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtPreco;
    private Button btnConfirmar;

    private Trabalhador trabalhador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);

        bindInterfaceElements();

        trabalhador = (Trabalhador) getIntent().getExtras().getSerializable("trabalhador");

        if (trabalhador != null) {
            setValuesToFields();
        }

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TrabalhadorDetalhesActivity.class);
                intent.putExtra("trabalhador", trabalhador);
                startActivity(intent);
            }
        });
    }

    private void setValuesToFields() {
        txtNome.setText(trabalhador.getNome());
        txtSobrenome.setText(trabalhador.getSobrenome());
        txtEmail.setText(trabalhador.getEmail());
        txtTelefone.setText(trabalhador.getTelefone());
        txtPreco.setText(trabalhador.getPreco() != null ? trabalhador.getPreco().toString() : " ");
    }

    private void bindInterfaceElements() {
        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtPreco = findViewById(R.id.txtPreco);
        btnConfirmar = findViewById(R.id.btnConfirmar);
    }
}
