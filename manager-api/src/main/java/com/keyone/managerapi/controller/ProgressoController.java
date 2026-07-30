package com.keyone.managerapi.controller;

import com.keyone.managerapi.dto.response.ProgressoExercicioResponse;
import com.keyone.managerapi.service.ProgressoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progresso")
@RequiredArgsConstructor
@Tag(name = "Progresso", description = "Estatisticas de evolucao ao longo do tempo")
public class ProgressoController {

    private final ProgressoService progressoService;

    @GetMapping("/exercicios/{exercicioId}")
    public ProgressoExercicioResponse progressoDoExercicio(@PathVariable Long exercicioId) {
        return progressoService.progressoDoExercicio(exercicioId);
    }
}
