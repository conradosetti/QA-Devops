package com.exemplo.ac1.controller;

import com.exemplo.ac1.servicos.PlataformaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/plataforma")
public class PlataformaController {
    private final PlataformaService plataformaService;

    public PlataformaController(PlataformaService plataformaService) {
        this.plataformaService = plataformaService;
    }

    @PostMapping("/recompensar")
    public ResponseEntity<String> recompensarAlunoComMaisComentarios() {
        try {
            plataformaService.recompensarAlunoComMaisComentarios();
            return new ResponseEntity<>("Aluno recompensado com sucesso!", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
