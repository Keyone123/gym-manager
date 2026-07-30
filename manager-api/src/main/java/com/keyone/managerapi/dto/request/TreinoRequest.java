package com.keyone.managerapi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TreinoRequest(
        @NotBlank(message = "nome do treino e obrigatorio")
        String nome,

        String descricao,

        @NotEmpty(message = "o treino precisa ter ao menos um exercicio")
        @Valid
        List<TreinoExercicioRequest> exercicios
) {
}
