package com.keyone.managerapi.dto.response;

import java.time.Instant;
import java.util.List;

public record TreinoResponse(
        Long id,
        String nome,
        String descricao,
        boolean ativo,
        Instant criadoEm,
        List<TreinoExercicioResponse> exercicios
) {
}
