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

        trabalhador = getTrabalhadorSelecionado();

        setWorkerDetailsOnInterfaceElements(trabalhador);

        imageGridAdapter = new ImageGridAdapter(this, trabalhador);
        gvFotos.setAdapter(imageGridAdapter);

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
        lbOficio.setText(String.format("%s %s", lbOficio.getText(), trabalhador.getOficio()));
        lbPreco.setText(String.format("%s R$%s", lbPreco.getText(), formatarPreco.format(trabalhador.getPreco())));
        lbTelefone.setText(String.format("%s %s", lbTelefone.getText(), trabalhador.getTelefone()));
        lbEmail.setText(String.format("%s %s", lbEmail.getText(), trabalhador.getEmail()));
        lbAvaliacao.setText(String.format("%s %s", lbAvaliacao.getText(), formatarAvaliacao.format(trabalhador.calcularMediaNotas())));
    }

    private Trabalhador getTrabalhadorSelecionado() {
        Bundle bundle = getIntent().getExtras();
        return (Trabalhador) bundle.getSerializable("trabalhador");
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
