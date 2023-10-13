package com.example.project.service;

import com.example.project.dto.CarroDTO;
import com.example.project.entity.Carro;
import com.example.project.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    private CarroRepository carroRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @Transactional(readOnly = true)
    public Optional<CarroDTO> findById(Long id) {
        return carroRepository.findById(id).map(CarroDTO::new);
    }

    @Transactional(readOnly = true)
    public List<CarroDTO> findAll() {
        List<Carro> carros = carroRepository.findAll();
        return carros.stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public void validarCarro(final Carro carro) {
        String nomeCarro = carro.getNomeCarro();
        if (nomeCarro == null || nomeCarro.isEmpty()) {
            throw new IllegalArgumentException("Nome do Carro não informado!");
        }
        if (!nomeCarro.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Nome do Carro inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Carro cadastrar(Carro carro) {
        validarCarro(carro);
        return carroRepository.save(carro);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Carro atualizar(Long id, Carro carro) {
        validarCarro(carro);
        Optional<Carro> carroExistenteOptional = carroRepository.findById(id);

        if (carroExistenteOptional.isPresent()) {
            Carro carroExistente = carroExistenteOptional.get();

            if (carro.getNomeCarro() != null) {
                carroExistente.setNomeCarro(carro.getNomeCarro());
            }

            if (carro.getAno() != 0) {
                carroExistente.setAno(carro.getAno());
            }

            return carroRepository.save(carroExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id) {
        Optional<Carro> carroExistenteOptional = carroRepository.findById(id);

        if (carroExistenteOptional.isPresent()) {
            Carro carroExistente = carroExistenteOptional.get();
            carroRepository.deleteById(carroExistente.getId());
        } else {
            throw new IllegalArgumentException("ID de carro inválido!");
        }
    }
}
