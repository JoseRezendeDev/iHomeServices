package com.example.ihomeservices;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ihomeservices.model.Cliente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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

    private EditText txtNome;
    private EditText txtSobrenome;
    private EditText txtTelefone;
    private EditText txtEmail;

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

        getActivity().findViewById(R.id.btnCadastrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
