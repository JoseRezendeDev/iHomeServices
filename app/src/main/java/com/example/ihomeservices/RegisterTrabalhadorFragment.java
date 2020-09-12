package com.example.ihomeservices;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.ihomeservices.model.Oficio;
import com.example.ihomeservices.model.Trabalhador;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterTrabalhadorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterTrabalhadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterTrabalhadorFragment extends Fragment {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtPreco;
    private EditText txtSenha;
    private Spinner spOficio;
    private Oficio oficio;
    private Button btnCadastrar;

    private OnFragmentInteractionListener mListener;

    public RegisterTrabalhadorFragment() {
        // Required empty public constructor
    }

    public static RegisterTrabalhadorFragment newInstance(String param1, String param2) {
        return new RegisterTrabalhadorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_register_trabalhador, container, false);

        bindInterfaceElements(view);

        DatabaseReference oficioNode = databaseReference.child("oficio");
        oficioNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    List<Oficio> listaOficios = new ArrayList<>();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        listaOficios.add(dataSnapshot.getValue(Oficio.class));
                    }

                    ArrayAdapter<Oficio> spOficioAdapter = new ArrayAdapter<Oficio>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, listaOficios);
                    spOficio.setAdapter(spOficioAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spOficio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oficio = (Oficio) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString())
                        .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Trabalhador trabalhador = new Trabalhador();
                                    trabalhador.setNome(txtNome.getText().toString());
                                    trabalhador.setSobrenome(txtSobrenome.getText().toString());
                                    trabalhador.setTelefone(txtTelefone.getText().toString());
                                    trabalhador.setEmail(txtEmail.getText().toString());
                                    trabalhador.setPreco(Double.parseDouble(txtPreco.getText().toString()));
                                    trabalhador.setOficio(oficio);
                                    String id = databaseReference.child("trabalhador").push().getKey();
                                    trabalhador.setId(id);
                                    databaseReference.child("trabalhador").child(id).setValue(trabalhador);

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

    private void bindInterfaceElements(View view) {
        txtNome = view.findViewById(R.id.txtNome);
        txtSobrenome = view.findViewById(R.id.txtSobrenome);
        txtTelefone = view.findViewById(R.id.txtTelefone);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPreco = view.findViewById(R.id.txtPreco);
        txtSenha = view.findViewById(R.id.txtSenha);
        spOficio = view.findViewById(R.id.spOficio);
        btnCadastrar = Objects.requireNonNull(getActivity()).findViewById(R.id.btnCadastrar);
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

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getContext(), "spinner", Toast.LENGTH_SHORT).show();
//        oficio = (Oficio) parent.getItemAtPosition(position);
//        spOficio.setSelection(position);
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
