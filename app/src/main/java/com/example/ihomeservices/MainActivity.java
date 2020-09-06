package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private TextView lbCadastrar;
    private TextView lbPular;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindInterfaceElements();

        int v = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        System.out.println(v);
//VERSAO DO GOOGLE PLAY SERVICES EH 9, TEM QUE SER 10 OU MAIOR
//        https://stackoverflow.com/questions/35476182/updating-google-play-services-in-emulator/35496184

//        Mock.popularBancoFirebase();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {;
                Intent intent = new Intent(getApplicationContext(), OficiosActivity.class);

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
                Intent intent = new Intent(getApplicationContext(), OficiosActivity.class);

                startActivity(intent);
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        auth.getCurrentUser()
//    }

    private void bindInterfaceElements() {
        btnEntrar = findViewById(R.id.btnEntrar);
        lbCadastrar = findViewById(R.id.lbCadastrar);
        lbPular = findViewById(R.id.lbPular);
    }
}
