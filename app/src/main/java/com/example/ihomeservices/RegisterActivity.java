package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends
        AppCompatActivity implements
        RegisterTipoUsuarioFragment.OnFragmentInteractionListener,
        RegisterTrabalhadorFragment.OnFragmentInteractionListener,
        RegisterClienteFragment.OnFragmentInteractionListener {

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private Button btnCadastrar;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindInterfaceElements();

        fragmentManager = getSupportFragmentManager();

        setInitialTipoUsuarioFragment();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword("jose12345@gmail.com", "aaabbbccc123")
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.e("Aaaa", "BBBBBBBBBB");
                                    bindInterfaceElements();
                                }
                                else {
                                    Log.e("UUUUU", "OOOO");
                                }
                            }
                        });

            }
        });
    }

    private void bindInterfaceElements() {
        btnCadastrar = findViewById(R.id.btnCadastrar);
    }

    @Override
    public void onTipoDeUsuarioSelected(int checkedId) {
        if (checkedId == R.id.rbTrabalhador) {
            setRegisterTrabalhadorFragment();
        } else if (checkedId == R.id.rbCliente) {
            setRegisterClienteFragment();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.flRegister);
        if (fragment instanceof RegisterTipoUsuarioFragment) {
            super.onBackPressed();
        } else {
            setRegisterTipoUsuarioFragment();
        }
    }

    private void setInitialTipoUsuarioFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        RegisterTipoUsuarioFragment registerTipoUsuarioFragment = new RegisterTipoUsuarioFragment();
        fragmentTransaction.add(R.id.flRegister, registerTipoUsuarioFragment);
        fragmentTransaction.commit();
    }

    private void setRegisterTrabalhadorFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterTrabalhadorFragment registerTrabalhadorFragment = new RegisterTrabalhadorFragment();
        fragmentTransaction.replace(R.id.flRegister, registerTrabalhadorFragment);
        fragmentTransaction.commit();
        btnCadastrar.setVisibility(View.VISIBLE);
    }

    private void setRegisterClienteFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterClienteFragment registerClienteFragment = RegisterClienteFragment.newInstance();
        fragmentTransaction.replace(R.id.flRegister, registerClienteFragment);
        fragmentTransaction.commit();
        btnCadastrar.setVisibility(View.VISIBLE);
    }

    private void setRegisterTipoUsuarioFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegisterTipoUsuarioFragment registerTipoUsuarioFragment = new RegisterTipoUsuarioFragment();
        fragmentTransaction.replace(R.id.flRegister, registerTipoUsuarioFragment);
        fragmentTransaction.commit();
        btnCadastrar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
