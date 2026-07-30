package com.keyone.managerapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RegistroExercicioRequest(
        @NotNull(message = "exercicioId e obrigatorio")
        Long exercicioId,

        @NotNull(message = "serieNumero e obrigatorio")
        @Min(value = 1, message = "serieNumero deve ser >= 1")
        Integer serieNumero,

        @NotNull(message = "repeticoes e obrigatorio")
        @Min(value = 1, message = "repeticoes deve ser >= 1")
        Integer repeticoes,

        @NotNull(message = "carga e obrigatoria")
        @Min(value = 0, message = "carga nao pode ser negativa")
        Double carga
) {
}
