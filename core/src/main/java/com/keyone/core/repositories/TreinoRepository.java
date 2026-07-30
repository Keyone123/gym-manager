package com.keyone.core.repositories;

import com.keyone.core.models.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    List<Treino> findByUsuarioIdAndAtivoTrue(Long usuarioId);

    Optional<Treino> findByIdAndUsuarioId(Long id, Long usuarioId);
}
