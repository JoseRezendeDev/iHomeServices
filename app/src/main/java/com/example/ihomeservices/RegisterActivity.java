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

public class RegisterActivity extends
        AppCompatActivity implements
        RegisterTipoUsuarioFragment.OnFragmentInteractionListener,
        RegisterTrabalhadorFragment.OnFragmentInteractionListener,
        RegisterClienteFragment.OnFragmentInteractionListener {

    private Button btnCadastrar;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        RegisterTipoUsuarioFragment registerTipoUsuarioFragment = new RegisterTipoUsuarioFragment();
        fragmentTransaction.add(R.id.flRegister, registerTipoUsuarioFragment);
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
    public void onTipoDeUsuarioSelected(int checkedId) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (checkedId) {
            case R.id.rbTrabalhador:
                RegisterTrabalhadorFragment registerTrabalhadorFragment = new RegisterTrabalhadorFragment();
                fragmentTransaction.replace(R.id.flRegister, registerTrabalhadorFragment);
                break;
            case R.id.rbCliente:
                RegisterClienteFragment registerClienteFragment = new RegisterClienteFragment();
                fragmentTransaction.replace(R.id.flRegister, registerClienteFragment);
                break;
        }
        fragmentTransaction.commit();
        btnCadastrar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        System.out.println("Tipo usuario escolhido");
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.flRegister);
        if (fragment instanceof RegisterTipoUsuarioFragment) {
            super.onBackPressed();
        } else {
            fragmentTransaction = fragmentManager.beginTransaction();
            RegisterTipoUsuarioFragment registerTipoUsuarioFragment = new RegisterTipoUsuarioFragment();
            fragmentTransaction.replace(R.id.flRegister, registerTipoUsuarioFragment);
            fragmentTransaction.commit();
        }
    }
}
