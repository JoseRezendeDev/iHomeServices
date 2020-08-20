package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private TextView lbCadastrar;
    private TextView lbPular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindInterfaceElements();

        Mock.popularBancoFirebase();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent intent = new Intent(getApplicationContext(), ServicesActivity.class);

                startActivity(intent);
            }
        });

        lbCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

                startActivity(intent);
            }
        });

        lbPular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ServicesActivity.class);

                startActivity(intent);
            }
        });
    }

    private void bindInterfaceElements() {
        btnEntrar = findViewById(R.id.btnEntrar);
        lbCadastrar = findViewById(R.id.lbCadastrar);
        lbPular = findViewById(R.id.lbPular);
    }
}
