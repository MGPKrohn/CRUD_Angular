package com.example.project.service;

import com.example.project.dto.PessoaDTO;
import com.example.project.entity.Pessoa;
import com.example.project.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;
    @Autowired
    public PessoaService(PessoaRepository pessoaRepository){
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<PessoaDTO> findById(Long id){
        return pessoaRepository.findById(id).map(PessoaDTO::new);
    }

    @Transactional(readOnly = true)
    public List<PessoaDTO> findAll(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(PessoaDTO::new).toList();
    }

    public void validarPessoa(final Pessoa pessoa){
        String nomePessoa = pessoa.getNomePessoa();
        if (nomePessoa == null || nomePessoa.isEmpty()){
            throw  new IllegalArgumentException("Nome da Pessoa não informado!");
        }
        if (!nomePessoa.matches("[a-zA-Z ]+")){
            throw new IllegalArgumentException("Nome da Pessoa inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Pessoa cadastrar(Pessoa pessoa){
        validarPessoa(pessoa);
        return pessoaRepository.save(pessoa);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Pessoa atualizar(Long id, Pessoa pessoa) {
        validarPessoa(pessoa);
        Optional<Pessoa> pessoaExistenteOptional = pessoaRepository.findById(id);

        if (pessoaExistenteOptional.isPresent()) {
            Pessoa pessoaExistente = pessoaExistenteOptional.get();

            if (pessoa.getNomePessoa() != null) {
                pessoaExistente.setNomePessoa(pessoa.getNomePessoa());
            }

            if (pessoa.getNomePessoa() != null) {
                pessoaExistente.setIdade(pessoa.getIdade());
            }

            return pessoaRepository.save(pessoaExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id) {
        Optional<Pessoa> pessoaExistenteOptional = pessoaRepository.findById(id);

        if (pessoaExistenteOptional.isPresent()) {
            Pessoa pessoaExistente = pessoaExistenteOptional.get();
            pessoaRepository.deleteById(pessoaExistente.getId());
        } else {
            throw new IllegalArgumentException("ID de pessoa inválido!");
        }
    }
}
