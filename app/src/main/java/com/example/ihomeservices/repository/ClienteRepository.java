package com.example.ihomeservices.repository;

import com.example.ihomeservices.model.Cliente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClienteRepository {
    private DatabaseReference databaseReference;

    public ClienteRepository() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void inserirCliente(Cliente cliente) {
        databaseReference.child("cliente").child(Integer.toString(cliente.getId())).setValue(cliente);
    }
}
