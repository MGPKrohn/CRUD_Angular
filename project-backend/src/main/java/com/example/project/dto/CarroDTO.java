package com.example.project.dto;

import com.example.project.entity.Carro;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarroDTO {
    private Long id;
    private String nomeCarro;
    private int ano;

    public CarroDTO(Carro carro){
        id = carro.getId();
        nomeCarro = carro.getNomeCarro();
        ano = carro.getAno();
    }
    public CarroDTO(Long id, String nomeCarro, int ano){
        this.id = id;
        this.nomeCarro = nomeCarro;
        this.ano = ano;
    }
}
