package com.keyone.managerapi.controller;

import com.keyone.managerapi.dto.request.LoginRequest;
import com.keyone.managerapi.dto.request.RegistrarUsuarioRequest;
import com.keyone.managerapi.dto.response.AutenticacaoResponse;
import com.keyone.managerapi.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticacao", description = "Cadastro e login de usuarios")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public AutenticacaoResponse registrar(@Valid @RequestBody RegistrarUsuarioRequest request) {
        return authService.registrar(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AutenticacaoResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.autenticar(request));
    }
}
