package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ihomeservices.model.Trabalhador;

public class WorkerDetailsActivity extends AppCompatActivity {

    private TextView lbNomeSobrenome;
    private TextView lbEmail;
    private ImageView imgFoto;
    private GridView gridView;
    private ImageGridAdapter imageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_details);

        lbNomeSobrenome = findViewById(R.id.lbNomeSobrenome);
        lbEmail = findViewById(R.id.lbEmail);
        gridView = findViewById(R.id.gvFotos);

        Bundle bundle = getIntent().getExtras();
        Trabalhador trabalhador = (Trabalhador) bundle.getSerializable("trabalhador");

        lbNomeSobrenome.setText(String.format("%s %s", trabalhador.getNome(), trabalhador.getSobrenome()));
        lbEmail.setText(trabalhador.getEmail());

        imageGridAdapter = new ImageGridAdapter(this);
        gridView.setAdapter(imageGridAdapter);

    }
}
