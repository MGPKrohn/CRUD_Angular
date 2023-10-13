package com.example.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_carro", schema = "public")
public class Carro extends AbstractEntity{
    @Getter @Setter
    @Column(name = "cl_nome")
    private String nomeCarro;

    @Getter @Setter
    @Column(name = "cl_ano")
    private int ano;
}
