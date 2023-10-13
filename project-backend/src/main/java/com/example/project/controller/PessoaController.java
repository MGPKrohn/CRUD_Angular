package com.example.project.controller;

import com.example.project.dto.PessoaDTO;
import com.example.project.entity.Pessoa;
import com.example.project.service.PessoaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

    private PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id){
        return pessoaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll(){
        List<PessoaDTO> pessoaDTOS = pessoaService.findAll();
        return ResponseEntity.ok(pessoaDTOS);
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa){
        try{
            Pessoa pessoaB = pessoaService.cadastrar(pessoa);

            return ResponseEntity.ok().body(pessoaB);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable @NotNull Long id, @RequestBody Pessoa pessoa){
        try{
            Pessoa pessoa1 = pessoaService.atualizar(id, pessoa);
            return ResponseEntity.ok().body(pessoa1);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            pessoaService.deletar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro.");
        }
    }
}
