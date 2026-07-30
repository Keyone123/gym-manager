package com.keyone.managerapi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.List;

public record RegistroTreinoRequest(
        Long treinoId,

        Instant dataExecucao,

        String observacao,

        @NotEmpty(message = "informe ao menos uma serie realizada")
        @Valid
        List<RegistroExercicioRequest> series
) {
}
