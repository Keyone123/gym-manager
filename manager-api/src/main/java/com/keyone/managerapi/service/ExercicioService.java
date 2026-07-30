package com.keyone.managerapi.service;

import com.keyone.core.models.Exercicio;
import com.keyone.core.repositories.ExercicioRepository;
import com.keyone.managerapi.dto.ExercicioDTO;
import com.keyone.managerapi.exception.RecursoNaoEncontradoException;
import com.keyone.managerapi.mapper.ExercicioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * O catalogo de exercicios e compartilhado (nao pertence a um usuario),
 * entao aqui nao ha filtro por usuario logado - qualquer usuario autenticado
 * pode consultar e cadastrar exercicios no catalogo.
 */
@Service
@RequiredArgsConstructor
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;
    private final ExercicioMapper exercicioMapper;

    @Transactional(readOnly = true)
    public List<ExercicioDTO> listarTodos() {
        return exercicioRepository.findAll().stream()
                .map(exercicioMapper::paraDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ExercicioDTO buscarPorId(Long id) {
        return exercicioMapper.paraDTO(buscarEntidadePorId(id));
    }

    @Transactional
    public ExercicioDTO cadastrar(ExercicioDTO dto) {
        Exercicio exercicio = Exercicio.builder()
                .nome(dto.nome())
                .grupoMuscular(dto.grupoMuscular())
                .descricao(dto.descricao())
                .build();
        return exercicioMapper.paraDTO(exercicioRepository.save(exercicio));
    }

    @Transactional
    public ExercicioDTO atualizar(Long id, ExercicioDTO dto) {
        Exercicio exercicio = buscarEntidadePorId(id);
        exercicio.setNome(dto.nome());
        exercicio.setGrupoMuscular(dto.grupoMuscular());
        exercicio.setDescricao(dto.descricao());
        return exercicioMapper.paraDTO(exercicioRepository.save(exercicio));
    }

    @Transactional
    public void remover(Long id) {
        Exercicio exercicio = buscarEntidadePorId(id);
        exercicioRepository.delete(exercicio);
    }

    Exercicio buscarEntidadePorId(Long id) {
        return exercicioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Exercicio nao encontrado: id=" + id));
    }
}
