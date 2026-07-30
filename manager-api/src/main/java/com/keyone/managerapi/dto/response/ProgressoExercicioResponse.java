package com.keyone.managerapi.dto.response;

import com.keyone.managerapi.dto.ExercicioDTO;

import java.util.List;

public record ProgressoExercicioResponse(
        ExercicioDTO exercicio,
        Double recordePessoal,
        List<PontoProgressoResponse> historico
) {
}
