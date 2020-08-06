package com.example.ihomeservices.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Trabalhador {
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private Double preco;
    private Oficio oficio;
    private List<Integer> avaliacao = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Oficio getOficio() {
        return oficio;
    }

    public void setOficio(Oficio oficio) {
        this.oficio = oficio;
    }

    public Double getAvaliacao() {
        Double soma = 0.0;
        for (Integer nota: avaliacao) {
            soma += nota;
        }
        return soma/avaliacao.size();
    }

    public void setAvaliacao(List<Integer> avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setAvaliacao(Integer nota) {
        this.avaliacao.add(nota);
    }
}
