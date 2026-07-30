package com.keyone.managerapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TreinoExercicioRequest(
        @NotNull(message = "exercicioId e obrigatorio")
        Long exercicioId,

        @NotNull(message = "ordem e obrigatoria")
        @Min(value = 1, message = "ordem deve ser >= 1")
        Integer ordem,

        @NotNull(message = "seriesAlvo e obrigatorio")
        @Min(value = 1, message = "seriesAlvo deve ser >= 1")
        Integer seriesAlvo,

        @NotNull(message = "repeticoesAlvo e obrigatorio")
        @Min(value = 1, message = "repeticoesAlvo deve ser >= 1")
        Integer repeticoesAlvo,

        Double cargaAlvo
) {
}
