package com.exemplo.ac1.entidades;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class plataforma {
    private List<aluno> alunos;
    private List<curso> cursos;

    public plataforma(List<aluno> alunos, List<curso> cursos) {
        this.alunos = alunos;
        this.cursos = cursos;
    }

    // Getters e setters
    public List<aluno> getAlunos() {
        return alunos;
    }

    public List<curso> getCursos() {
        return cursos;
    }

}
