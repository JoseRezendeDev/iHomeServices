package com.example.ihomeservices;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ihomeservices.model.Cliente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.Executor;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterClienteFragment extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtSenha;
    private Button btnCadastrar;

    private OnFragmentInteractionListener mListener;

    public RegisterClienteFragment() {
        // Required empty public constructor
    }

    public static RegisterClienteFragment newInstance() {
        return new RegisterClienteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_cliente, container, false);

        txtNome = view.findViewById(R.id.txtNome);
        txtSobrenome = view.findViewById(R.id.txtSobrenome);
        txtTelefone = view.findViewById(R.id.txtTelefone);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtSenha = view.findViewById(R.id.txtSenha);
        btnCadastrar = Objects.requireNonNull(getActivity()).findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString())
                        .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Cliente cliente = new Cliente();
                                    cliente.setNome(txtNome.getText().toString());
                                    cliente.setSobrenome(txtSobrenome.getText().toString());
                                    cliente.setTelefone(txtTelefone.getText().toString());
                                    cliente.setEmail(txtEmail.getText().toString());
                                    String id = databaseReference.child("cliente").push().getKey();
                                    cliente.setId(id);
                                    databaseReference.child("cliente").child(id).setValue(cliente);

                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getContext(), "Falha no cadastro, altere os dados e tente novamente", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
