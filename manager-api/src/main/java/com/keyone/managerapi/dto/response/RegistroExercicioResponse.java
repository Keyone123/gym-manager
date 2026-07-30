package com.keyone.managerapi.dto.response;

import com.keyone.managerapi.dto.ExercicioDTO;

public record RegistroExercicioResponse(
        Long id,
        ExercicioDTO exercicio,
        Integer serieNumero,
        Integer repeticoes,
        Double carga
) {
}
