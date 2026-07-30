package com.keyone.managerapi.dto.response;

import java.time.Instant;
import java.util.List;

public record RegistroTreinoResponse(
        Long id,
        Long treinoId,
        String treinoNome,
        Instant dataExecucao,
        String observacao,
        List<RegistroExercicioResponse> series
) {
}
