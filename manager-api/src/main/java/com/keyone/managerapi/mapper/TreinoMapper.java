package com.keyone.managerapi.mapper;

import com.keyone.core.models.Treino;
import com.keyone.core.models.TreinoExercicio;
import com.keyone.managerapi.dto.response.TreinoExercicioResponse;
import com.keyone.managerapi.dto.response.TreinoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TreinoMapper {

    private final ExercicioMapper exercicioMapper;

    public TreinoResponse paraResponse(Treino treino) {
        var exercicios = treino.getExercicios().stream()
                .map(this::paraExercicioResponse)
                .toList();

        return new TreinoResponse(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.isAtivo(),
                treino.getCriadoEm(),
                exercicios
        );
    }

    private TreinoExercicioResponse paraExercicioResponse(TreinoExercicio treinoExercicio) {
        return new TreinoExercicioResponse(
                treinoExercicio.getId(),
                exercicioMapper.paraDTO(treinoExercicio.getExercicio()),
                treinoExercicio.getOrdem(),
                treinoExercicio.getSeriesAlvo(),
                treinoExercicio.getRepeticoesAlvo(),
                treinoExercicio.getCargaAlvo()
        );
    }
}
