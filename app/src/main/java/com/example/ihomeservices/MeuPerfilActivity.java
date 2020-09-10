package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ihomeservices.model.Cliente;
import com.example.ihomeservices.model.Trabalhador;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MeuPerfilActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtPreco;
    private TextView lbPreco;
    private Button btnConfirmar;

    private Trabalhador trabalhador;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);

        bindInterfaceElements();

        final FirebaseUser firebaseUser = auth.getCurrentUser();

        final DatabaseReference trabalhadoresNode = databaseReference.child("trabalhador");
        trabalhadoresNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (Objects.equals(Objects.requireNonNull(firebaseUser).getEmail(), Objects.requireNonNull(dataSnapshot.getValue(Trabalhador.class)).getEmail())) {
                        trabalhador = dataSnapshot.getValue(Trabalhador.class);

                        if (trabalhador != null) {
                            setValuesToFieldsTrabalhador();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final DatabaseReference clientesNode = databaseReference.child("cliente");
        clientesNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if (Objects.equals(Objects.requireNonNull(firebaseUser).getEmail(), Objects.requireNonNull(dataSnapshot.getValue(Cliente.class)).getEmail())) {
                        cliente = dataSnapshot.getValue(Cliente.class);

                        if (cliente != null) {
                            setValuesToFieldsCliente();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trabalhador != null) {

                    trabalhador.setNome(txtNome.getText().toString());
                    trabalhador.setSobrenome(txtSobrenome.getText().toString());
                    trabalhador.setTelefone(txtTelefone.getText().toString());
                    trabalhador.setPreco(Double.parseDouble(txtPreco.getText().toString()));

                    trabalhadoresNode.child(trabalhador.getId()).setValue(trabalhador);

                    Intent intent = new Intent(getApplicationContext(), TrabalhadorDetalhesActivity.class);
                    intent.putExtra("trabalhador", trabalhador);
                    startActivity(intent);
                } else {
                    cliente.setNome(txtNome.getText().toString());
                    cliente.setSobrenome(txtSobrenome.getText().toString());
                    cliente.setTelefone(txtTelefone.getText().toString());

                    clientesNode.child(cliente.getId()).setValue(cliente);
                    Intent intent = new Intent(getApplicationContext(), OficiosActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setValuesToFieldsTrabalhador() {
        txtNome.setText(trabalhador.getNome());
        txtSobrenome.setText(trabalhador.getSobrenome());
        txtEmail.setText(trabalhador.getEmail());
        txtTelefone.setText(trabalhador.getTelefone());
        txtPreco.setText(trabalhador.getPreco() != null ? trabalhador.getPreco().toString() : " ");
    }

    private void setValuesToFieldsCliente() {
        txtNome.setText(cliente.getNome());
        txtSobrenome.setText(cliente.getSobrenome());
        txtEmail.setText(cliente.getEmail());
        txtTelefone.setText(cliente.getTelefone());
        txtPreco.setVisibility(View.INVISIBLE);
        lbPreco.setVisibility(View.INVISIBLE);
    }

    private void bindInterfaceElements() {
        txtNome = findViewById(R.id.txtNome);
        txtSobrenome = findViewById(R.id.txtSobrenome);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtPreco = findViewById(R.id.txtPreco);
        lbPreco = findViewById(R.id.lbPreco);
        btnConfirmar = findViewById(R.id.btnConfirmar);
    }
}
