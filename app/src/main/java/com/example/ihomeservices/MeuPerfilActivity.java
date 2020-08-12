package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ihomeservices.model.Oficio;
import com.example.ihomeservices.model.Trabalhador;

import java.util.Arrays;

public class MeuPerfilActivity extends AppCompatActivity {

    private Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_perfil);

        btnConfirmar = findViewById(R.id.btnConfirmar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkerDetailsActivity.class);
                Trabalhador trabalhador = getMockedTrabalhador1();
                intent.putExtra("trabalhador", trabalhador);
                startActivity(intent);
            }
        });
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
}
