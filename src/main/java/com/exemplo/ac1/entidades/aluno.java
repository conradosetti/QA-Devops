package com.exemplo.ac1.entidades;

import java.util.ArrayList;
import java.util.List;

public class aluno {
    public String nome;
    public String email;
    public List<curso> cursos;
    public List<comentario> comentarios;

    public aluno(String nome, String email, List<curso> cursos, List<comentario> comentarios) {
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
        this.comentarios = comentarios;
    }

    public aluno(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.cursos = new ArrayList<>();
        this.comentarios = new ArrayList<>();
    }

    public void adicionarCurso(curso curso) {
        this.cursos.add(curso);
    }

    public void adicionarComentario(comentario comentario) {
        if (this.cursos.contains(comentario.curso)) {
            this.comentarios.add(comentario);
        }
    }

    public int totalCursos() {
        return this.cursos.size();
    }

    public int totalComentarios() {
        return this.comentarios.size();
    }
}
