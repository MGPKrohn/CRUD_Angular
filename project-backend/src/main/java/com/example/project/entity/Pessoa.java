package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa", schema = "public")
public class Pessoa extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_nome")
    private String nomePessoa;

    @Getter @Setter
    @Column(name = "cl_idade")
    private int idade;
}
