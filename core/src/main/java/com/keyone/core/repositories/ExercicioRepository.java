package com.keyone.core.repositories;

import com.keyone.core.models.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {

    Optional<Exercicio> findByNomeIgnoreCase(String nome);

    List<Exercicio> findByGrupoMuscularIgnoreCase(String grupoMuscular);
}
