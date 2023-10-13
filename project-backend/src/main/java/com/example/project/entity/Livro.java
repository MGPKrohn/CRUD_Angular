package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_livro",schema = "public")
public class Livro extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_nome")
    private String nomeLivro;

    @Getter @Setter
    @Column(name = "cl_titulo")
    private String titulo;
}
