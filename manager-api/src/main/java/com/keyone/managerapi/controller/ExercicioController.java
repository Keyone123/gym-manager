package com.keyone.managerapi.controller;

import com.keyone.managerapi.dto.ExercicioDTO;
import com.keyone.managerapi.service.ExercicioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@RequiredArgsConstructor
@Tag(name = "Exercicios", description = "Catalogo de exercicios")
public class ExercicioController {

    private final ExercicioService exercicioService;

    @GetMapping
    public List<ExercicioDTO> listar() {
        return exercicioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ExercicioDTO buscarPorId(@PathVariable Long id) {
        return exercicioService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExercicioDTO cadastrar(@Valid @RequestBody ExercicioDTO dto) {
        return exercicioService.cadastrar(dto);
    }

    @PutMapping("/{id}")
    public ExercicioDTO atualizar(@PathVariable Long id, @Valid @RequestBody ExercicioDTO dto) {
        return exercicioService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        exercicioService.remover(id);
    }
}
