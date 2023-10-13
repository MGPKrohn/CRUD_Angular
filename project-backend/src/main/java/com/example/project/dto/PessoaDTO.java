package com.example.project.dto;

import com.example.project.entity.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PessoaDTO {
    private Long id;
    private String nomePessoa;
    private int idade;

    public PessoaDTO(Pessoa pessoa){
        id = pessoa.getId();
        nomePessoa = pessoa.getNomePessoa();
        idade = pessoa.getIdade();
    }

    public PessoaDTO(Long id, String nomePessoa, int idade){
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.idade = idade;
    }
}
