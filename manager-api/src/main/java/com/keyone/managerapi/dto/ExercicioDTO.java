package com.keyone.managerapi.dto;

import jakarta.validation.constraints.NotBlank;

public record ExercicioDTO(
        Long id,

        @NotBlank(message = "nome do exercicio e obrigatorio")
        String nome,

        String grupoMuscular,
        String descricao
) {
}
