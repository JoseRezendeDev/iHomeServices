package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ihomeservices.adapter.ImageGridAdapter;
import com.example.ihomeservices.model.Trabalhador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Objects;

import static java.net.Proxy.Type.HTTP;

public class TrabalhadorDetalhesActivity extends AppCompatActivity {

    DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();

    private TextView lbNomeSobrenome;
    private TextView lbOficio;
    private TextView lbPreco;
    private TextView lbTelefone;
    private TextView lbEmail;
    private TextView lbAvaliacao;
    private Button btnAvaliar;
    private GridView gvFotos;
    private ImageGridAdapter imageGridAdapter;
    private ImageButton imgBtnLigar;
    private ImageButton imgBtnEnviar;

    private Trabalhador trabalhador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalhador_detalhes);

        bindInterfaceElements();

        getTrabalhadorSelecionado();

        imgBtnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + trabalhador.getTelefone()));
                startActivity(intent);
            }
        });

        imgBtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setDataAndType(Uri.parse(trabalhador.getEmail()), "text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "E-mail de cliente do iHomeServices");
                intent.putExtra(Intent.EXTRA_TEXT, "Olá, sou cliente do iHomeServices, gostaria de saber...");
                startActivity(Intent.createChooser(intent, "Enviar email..."));
            }
        });

        btnAvaliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComentariosActivity.class);

                intent.putExtra("idTrabalhador", trabalhador.getId());
                startActivity(intent);
            }
        });
    }

    private void setWorkerDetailsOnInterfaceElements(Trabalhador trabalhador) {
        DecimalFormat formatarPreco = new DecimalFormat("#.00");
        DecimalFormat formatarAvaliacao = new DecimalFormat("#.0");
        lbNomeSobrenome.setText(String.format("%s %s", trabalhador.getNome(), trabalhador.getSobrenome()));
        lbOficio.setText(String.format("Ofício: %s", trabalhador.getOficio()));
        lbPreco.setText(String.format("Preço: R$%s", formatarPreco.format(trabalhador.getPreco())));
        lbTelefone.setText(String.format("Telefone: %s", trabalhador.getTelefone()));
        lbEmail.setText(String.format("Email: %s", trabalhador.getEmail()));
        Double mediaNotas = trabalhador.calcularMediaNotas();
        lbAvaliacao.setText(String.format("Avaliação: %s", mediaNotas == -1.0 ? "Novo" : formatarAvaliacao.format(mediaNotas)));
    }

    private void getTrabalhadorSelecionado() {
        Bundle bundle = getIntent().getExtras();
        final Trabalhador trabalhadorSelecionado = (Trabalhador) bundle.getSerializable("trabalhador");

        firebaseDatabase.child("trabalhador").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (trabalhadorSelecionado.getId().equals(dataSnapshot.getValue(Trabalhador.class).getId())) {
                        trabalhador = dataSnapshot.getValue(Trabalhador.class);
                        setWorkerDetailsOnInterfaceElements(trabalhador);

                        imageGridAdapter = new ImageGridAdapter(getApplicationContext(), trabalhador);
                        gvFotos.setAdapter(imageGridAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void bindInterfaceElements() {
        lbNomeSobrenome = findViewById(R.id.lbNomeSobrenome);
        lbOficio = findViewById(R.id.lbOficio);
        lbPreco = findViewById(R.id.lbPreco);
        lbTelefone = findViewById(R.id.lbTelefone);
        lbEmail = findViewById(R.id.lbEmail);
        lbAvaliacao = findViewById(R.id.lbAvaliacao);
        btnAvaliar = findViewById(R.id.btnAvaliar);
        gvFotos = findViewById(R.id.gvFotos);
        imgBtnLigar = findViewById(R.id.imgBtnLigar);
        imgBtnEnviar = findViewById(R.id.imgBtnEnviar);
    }
}
