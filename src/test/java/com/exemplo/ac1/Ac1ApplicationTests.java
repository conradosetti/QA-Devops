package com.exemplo.ac1;

import com.exemplo.ac1.entidades.aluno;
import com.exemplo.ac1.entidades.comentario;
import com.exemplo.ac1.entidades.curso;
import com.exemplo.ac1.entidades.plataforma;
import com.exemplo.ac1.servicos.PlataformaService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Ac1ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void deveDarCursoParaAlunoComMaisComentarios() {
        // Arrange
        curso curso1 = new curso("Java", "Java para iniciantes");
        curso curso2 = new curso("Python", "Python para iniciantes");
        curso curso3 = new curso("C#", "C# para iniciantes");

        aluno aluno1 = new aluno("Conrado", "conrado@facens.com");
        aluno1.adicionarCurso(curso1);

        comentario comentario1aluno1 = new comentario("Muito bom", curso1);
        comentario comentario2aluno1 = new comentario("Gostei", curso1);
        comentario comentario3aluno1 = new comentario("Aula de orientado a objetos foi dificil", curso1);

        aluno1.adicionarComentario(comentario1aluno1);
        aluno1.adicionarComentario(comentario2aluno1);
        aluno1.adicionarComentario(comentario3aluno1);

        aluno aluno2 = new aluno("Alan", "alan@facens.com");
        aluno2.adicionarCurso(curso1);

        comentario comentario1aluno2 = new comentario("Gostei, mas achei dificil", curso1);
        aluno2.adicionarComentario(comentario1aluno2);

        aluno aluno3 = new aluno("Gabriel", "gabriel@facens.com");
        comentario comentario1aluno3 = new comentario("Muito bom", curso1);

        aluno3.adicionarCurso(curso1);
        aluno3.adicionarComentario(comentario1aluno3);

        int quantidadeDeCursosAluno2 = aluno2.totalCursos();
        int quantidadeDeCursosAluno3 = aluno3.totalCursos();

        plataforma plataforma = new plataforma(List.of(aluno1, aluno2, aluno3), List.of(curso1, curso2, curso3));

        PlataformaService plataformaService = new PlataformaService(plataforma);

        // Act
        plataformaService.recompensarAlunoComMaisComentarios();

        // Assert
        assertAll(
                () -> assertEquals(2, aluno1.totalCursos()),
                () -> assertEquals(1, quantidadeDeCursosAluno2),
                () -> assertEquals(1, quantidadeDeCursosAluno3)
        );
    }
    @Test
    public void alunoNaomatriculadoNaoDevePoderComentarNoForum() {
        // Arrange
        curso curso1 = new curso("Java", "Java para iniciantes");

        aluno aluno1 = new aluno("Gabriel", "gabriel.portillo@facens.com");

        comentario comentario1aluno1 = new comentario("Aula de orientado a objetos foi dificil", curso1);

        plataforma plataforma = new plataforma(List.of(aluno1), List.of(curso1));

        // Act

        aluno1.adicionarComentario(comentario1aluno1);

        //Assert

        assertEquals(0, aluno1.totalComentarios());
    }
    @Test
    public void DeveRetornarErroQuandoNaoHaMaisCursos(){
    //Arrange
    curso curso1 = new curso("Java", "Java para iniciantes");

    aluno aluno1 = new aluno("Conrado", "conrado@facens.com");
    aluno1.adicionarCurso(curso1);

    comentario comentario1aluno1 = new comentario("Muito bom", curso1);
    comentario comentario2aluno1 = new comentario("Gostei", curso1);

    aluno1.adicionarComentario(comentario1aluno1);
    aluno1.adicionarComentario(comentario2aluno1);

    aluno aluno2 = new aluno("Alan", "alan@facens.com");
    aluno2.adicionarCurso(curso1);

    comentario comentario1aluno2 = new comentario("Gostei, mas achei dificil", curso1);
    aluno2.adicionarComentario(comentario1aluno2);

    plataforma plataforma = new plataforma(List.of(aluno1, aluno2), List.of(curso1));

    PlataformaService plataformaService = new PlataformaService(plataforma);

    // Act
    Exception exception = assertThrows(RuntimeException.class, plataformaService::recompensarAlunoComMaisComentarios);

    // Assert
    assertTrue(exception.getMessage().contains("Não é possível se matricular em mais cursos"));

    }

}
