package com.example.ihomeservices;

import com.example.ihomeservices.model.Cliente;
import com.example.ihomeservices.model.Oficio;
import com.example.ihomeservices.model.Trabalhador;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mock {
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static void popularBancoFirebase() {
        setMockedTrabalhador1();
        setMockedTrabalhador2();
        setMockedCliente1();
        setMockedCliente2();
        setOficios();
    }

    private static void setMockedTrabalhador1() {
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome("Jose");
        trabalhador.setSobrenome("Rezende");
        trabalhador.setEmail("jose@gmail.com");
        trabalhador.setTelefone("16991223344");
        trabalhador.setPreco(120.0);
        trabalhador.setOficio(Oficio.PEDREIRO);
        trabalhador.addAvaliacao("Muito bom", 4);
        trabalhador.addAvaliacao("Adorei o serviço", 5);
        String id = databaseReference.child("trabalhador").push().getKey();
        trabalhador.setId(id);
        databaseReference.child("trabalhador").child(id).setValue(trabalhador);
    }

    private static void setMockedTrabalhador2() {
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome("Carlos");
        trabalhador.setSobrenome("Pereira");
        trabalhador.setEmail("carlao@gmail.com");
        trabalhador.setTelefone("16911223344");
        trabalhador.setPreco(80.0);
        trabalhador.setOficio(Oficio.JARDINEIRO);
        trabalhador.addAvaliacao("Ruim", 2);
        trabalhador.addAvaliacao("Mais ou menos", 3);
        String id = databaseReference.child("trabalhador").push().getKey();
        trabalhador.setId(id);
        databaseReference.child("trabalhador").child(id).setValue(trabalhador);
    }

    private static void setMockedCliente1() {
        Cliente cliente = new Cliente();
        cliente.setNome("Bruno");
        cliente.setSobrenome("Margoto");
        cliente.setTelefone("16955667788");
        cliente.setEmail("bruno@hotmail.com");
        String id = databaseReference.child("cliente").push().getKey();
        cliente.setId(id);
        databaseReference.child("cliente").child(id).setValue(cliente);
    }

    private static void setMockedCliente2() {
        Cliente cliente = new Cliente();
        cliente.setNome("Felipe");
        cliente.setSobrenome("Rodrigues");
        cliente.setTelefone("19933442211");
        cliente.setEmail("felipe@yahoo.com");
        String id = databaseReference.child("cliente").push().getKey();
        cliente.setId(id);
        databaseReference.child("cliente").child(id).setValue(cliente);
    }

    public static void setOficios() {
        List<Oficio> oficios = new ArrayList<>();
        oficios.add(Oficio.CABELEIREIRO);
        oficios.add(Oficio.JARDINEIRO);
        oficios.add(Oficio.PISCINEIRO);
        oficios.add(Oficio.PINTOR);
        oficios.add(Oficio.PEDREIRO);
        oficios.add(Oficio.MANICURE);
        databaseReference.child("oficio").setValue(oficios);
    }
}
