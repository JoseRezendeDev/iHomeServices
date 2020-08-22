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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.ihomeservices.model.Oficio;
import com.example.ihomeservices.model.Trabalhador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterTrabalhadorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterTrabalhadorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterTrabalhadorFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtPreco;
    private Spinner spOficio;
    private Oficio oficio;

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

        View view = inflater.inflate(R.layout.fragment_register_trabalhador, container, false);

        bindInterfaceElements(view);

        spOficio.setOnItemSelectedListener(this);

        final List<Oficio> listaOficios = new ArrayList<>();
        ArrayAdapter<Oficio> spOficioAdapter = new ArrayAdapter<Oficio>(container.getContext(), android.R.layout.simple_spinner_dropdown_item, listaOficios);
        spOficio.setAdapter(spOficioAdapter);

        DatabaseReference oficioNode = databaseReference.child("oficio");
        oficioNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        listaOficios.add(dataSnapshot.getValue(Oficio.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        getActivity().findViewById(R.id.btnCadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        spOficio = view.findViewById(R.id.spOficio);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        oficio = (Oficio) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

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
