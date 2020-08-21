package com.example.ihomeservices.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Avaliacao implements Serializable {
    private String comentario;
    private Integer nota;

    public Avaliacao(String comentario, Integer nota) {
        this.comentario = comentario;
        this.nota = nota;
    }

    public Avaliacao() {
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
