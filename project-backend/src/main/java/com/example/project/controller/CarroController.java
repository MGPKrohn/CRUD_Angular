package com.example.project.controller;

import com.example.project.dto.CarroDTO;
import com.example.project.entity.Carro;
import com.example.project.service.CarroService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carro")
@CrossOrigin(origins = "*")
public class CarroController {

    private CarroService carroService;

    @Autowired
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        return carroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarroDTO>> findAll() {
        List<CarroDTO> carroDTOS = carroService.findAll();
        return ResponseEntity.ok(carroDTOS);
    }

    @PostMapping
    public ResponseEntity<Carro> cadastrar(@RequestBody Carro carro) {
        try {
            Carro carroB = carroService.cadastrar(carro);
            return ResponseEntity.ok().body(carroB);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizar(@PathVariable @NotNull Long id, @RequestBody Carro carro) {
        try {
            Carro carro1 = carroService.atualizar(id, carro);
            return ResponseEntity.ok().body(carro1);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            carroService.deletar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro.");
        }
    }
}
