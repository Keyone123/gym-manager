package com.keyone.managerapi.controller;

import com.keyone.managerapi.dto.request.TreinoRequest;
import com.keyone.managerapi.dto.response.TreinoResponse;
import com.keyone.managerapi.service.TreinoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treinos")
@RequiredArgsConstructor
@Tag(name = "Treinos", description = "Cadastro e gerenciamento dos treinos do usuario logado")
public class TreinoController {

    private final TreinoService treinoService;

    @GetMapping
    public List<TreinoResponse> listarMeusTreinos() {
        return treinoService.listarMeusTreinos();
    }

    @GetMapping("/{id}")
    public TreinoResponse buscarPorId(@PathVariable Long id) {
        return treinoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TreinoResponse cadastrar(@Valid @RequestBody TreinoRequest request) {
        return treinoService.cadastrar(request);
    }

    @PutMapping("/{id}")
    public TreinoResponse atualizar(@PathVariable Long id, @Valid @RequestBody TreinoRequest request) {
        return treinoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        treinoService.remover(id);
    }
}
