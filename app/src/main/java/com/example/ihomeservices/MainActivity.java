package com.example.ihomeservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private TextView lbCadastrar;
    private EditText txtUsuario;
    private EditText txtSenha;

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindInterfaceElements();

//        Mock.popularBancoFirebase();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();
                if (!usuario.isEmpty() && !senha.isEmpty()) {
                    auth.signInWithEmailAndPassword(txtUsuario.getText().toString(), txtSenha.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), OficiosActivity.class);

                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(MainActivity.this, "Preencha os dois campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lbCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);

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
        txtUsuario = findViewById(R.id.txtUsuario);
        txtSenha = findViewById(R.id.txtSenha);
    }
}
