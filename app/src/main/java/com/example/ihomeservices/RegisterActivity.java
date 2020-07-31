package com.example.ihomeservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements RegisterTrabalhadorFragment.OnFragmentInteractionListener {

    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        RegisterTrabalhadorFragment registerTrabalhadorFragment = new RegisterTrabalhadorFragment();
        fragmentTransaction.add(R.id.flRegister, registerTrabalhadorFragment);
        fragmentTransaction.commit();

        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServicesActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("Interagiu com fragment");
    }
}
