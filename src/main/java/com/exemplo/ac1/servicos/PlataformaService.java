package com.exemplo.ac1.servicos;


import com.exemplo.ac1.entidades.aluno;
import com.exemplo.ac1.entidades.curso;
import com.exemplo.ac1.entidades.plataforma;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlataformaService {
    private final plataforma plataforma;

    public PlataformaService(plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public void recompensarAlunoComMaisComentarios(){
        aluno alunoComMaisComentarios = getAlunoComMaisComentarios();

        List<curso> cursosQueAlunoNaoFez = getCursosQueAlunoNaoFez(alunoComMaisComentarios);

        matricularAlunoEmCurso(alunoComMaisComentarios, cursosQueAlunoNaoFez);
    }


    private List<curso> getCursosQueAlunoNaoFez(aluno aluno) {
        return this.plataforma.getCursos().stream()
                .filter(curso -> !aluno.cursos.contains(curso))
                .collect(Collectors.toList());
    }

    private aluno getAlunoComMaisComentarios() {
        return this.plataforma.getAlunos().stream()
                .max(Comparator.comparing(aluno::totalComentarios))
                .orElseThrow(() -> new RuntimeException("Lista de alunos está vazia"));
    }
    private void matricularAlunoEmCurso(aluno alunoComMaisComentarios, List<curso> cursosQueAlunoNaoFez) {
        if (cursosQueAlunoNaoFez.isEmpty()) {
            throw new RuntimeException("Não é possível se matricular em mais cursos");
        }
        alunoComMaisComentarios.adicionarCurso(this.plataforma.getCursos().get(0));
    }
}
