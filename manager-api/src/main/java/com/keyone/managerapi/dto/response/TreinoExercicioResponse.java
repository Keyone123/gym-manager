package com.keyone.managerapi.dto.response;

import com.keyone.managerapi.dto.ExercicioDTO;

public record TreinoExercicioResponse(
        Long id,
        ExercicioDTO exercicio,
        Integer ordem,
        Integer seriesAlvo,
        Integer repeticoesAlvo,
        Double cargaAlvo
) {
}
