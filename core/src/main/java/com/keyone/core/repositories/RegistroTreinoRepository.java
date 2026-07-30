package com.keyone.core.repositories;

import com.keyone.core.models.RegistroTreino;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegistroTreinoRepository extends JpaRepository<RegistroTreino, Long> {

    @EntityGraph(attributePaths = {"registrosExercicio", "registrosExercicio.exercicio", "treino"})
    List<RegistroTreino> findByUsuarioIdOrderByDataExecucaoDesc(Long usuarioId);

    @EntityGraph(attributePaths = {"registrosExercicio", "registrosExercicio.exercicio", "treino"})
    Optional<RegistroTreino> findByIdAndUsuarioId(Long id, Long usuarioId);

    long countByUsuarioId(Long usuarioId);
}
