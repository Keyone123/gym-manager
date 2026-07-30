package com.keyone.core.repositories;

import com.keyone.core.models.RegistroExercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistroExercicioRepository extends JpaRepository<RegistroExercicio, Long> {

    @Query("""
            select re.registroTreino.dataExecucao as data,
                   max(re.carga) as cargaMaxima,
                   sum(re.repeticoes * re.carga) as volumeTotal,
                   count(re) as totalSeries
            from RegistroExercicio re
            where re.registroTreino.usuario.id = :usuarioId
              and re.exercicio.id = :exercicioId
            group by re.registroTreino.dataExecucao
            order by re.registroTreino.dataExecucao asc
            """)
    List<ProgressoDiarioProjecao> buscarProgressoDiario(@Param("usuarioId") Long usuarioId,
                                                        @Param("exercicioId") Long exercicioId);

    @Query("""
            select max(re.carga)
            from RegistroExercicio re
            where re.registroTreino.usuario.id = :usuarioId
              and re.exercicio.id = :exercicioId
            """)
    Double buscarRecordePessoal(@Param("usuarioId") Long usuarioId, @Param("exercicioId") Long exercicioId);
}
