package com.keyone.managerapi.controller;

import com.keyone.managerapi.dto.request.RegistroTreinoRequest;
import com.keyone.managerapi.dto.response.RegistroTreinoResponse;
import com.keyone.managerapi.service.RegistroTreinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros-treino")
@RequiredArgsConstructor
@Tag(name = "Registros de treino", description = "Historico de execucoes reais de treino")
public class RegistroTreinoController {

    private final RegistroTreinoService registroTreinoService;

    @GetMapping
    public List<RegistroTreinoResponse> listarHistorico() {
        return registroTreinoService.listarHistorico();
    }

    @GetMapping("/{id}")
    public RegistroTreinoResponse buscarPorId(@PathVariable Long id) {
        return registroTreinoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegistroTreinoResponse registrar(@Valid @RequestBody RegistroTreinoRequest request) {
        return registroTreinoService.registrar(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        registroTreinoService.remover(id);
    }
}
