package com.example.project.dto;

import com.example.project.entity.Livro;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LivroDTO {
    private Long id;
    private String nomeLivro;
    private String titulo;

    public LivroDTO(Livro livro){
        id = livro.getId();
        nomeLivro = livro.getNomeLivro();
        titulo = livro.getTitulo();
    }
    public LivroDTO(Long id, String nomeLivro, String titulo){
        this.id = id;
        this.nomeLivro = nomeLivro;
        this.titulo = titulo;
    }
}
