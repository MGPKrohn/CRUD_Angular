package com.example.project.service;

import com.example.project.dto.LivroDTO;
import com.example.project.entity.Livro;
import com.example.project.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Transactional(readOnly = true)
    public Optional<LivroDTO> findById(Long id) {
        return livroRepository.findById(id).map(LivroDTO::new);
    }

    @Transactional(readOnly = true)
    public List<LivroDTO> findAll() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream().map(LivroDTO::new).collect(Collectors.toList());
    }

    public void validarLivro(final Livro livro) {
        String nomeLivro = livro.getNomeLivro();
        if (nomeLivro == null || nomeLivro.isEmpty()) {
            throw new IllegalArgumentException("Nome do Livro não informado!");
        }
        if (!nomeLivro.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("Nome do Livro inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Livro cadastrar(Livro livro) {
        validarLivro(livro);
        return livroRepository.save(livro);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Livro atualizar(Long id, Livro livro) {
        validarLivro(livro);
        Optional<Livro> livroExistenteOptional = livroRepository.findById(id);

        if (livroExistenteOptional.isPresent()) {
            Livro livroExistente = livroExistenteOptional.get();

            if (livro.getNomeLivro() != null) {
                livroExistente.setNomeLivro(livro.getNomeLivro());
            }

            if (livro.getTitulo() != null) {
                livroExistente.setTitulo(livro.getTitulo());
            }

            return livroRepository.save(livroExistente);
        } else {
            throw new IllegalArgumentException("ID Inválido!");
        }
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void deletar(Long id) {
        Optional<Livro> livroExistenteOptional = livroRepository.findById(id);

        if (livroExistenteOptional.isPresent()) {
            Livro livroExistente = livroExistenteOptional.get();
            livroRepository.deleteById(livroExistente.getId());
        } else {
            throw new IllegalArgumentException("ID de livro inválido!");
        }
    }
}
