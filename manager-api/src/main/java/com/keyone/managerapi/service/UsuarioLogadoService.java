package com.keyone.managerapi.service;

import com.keyone.core.models.Usuario;
import com.keyone.core.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Resolve o Usuario correspondente ao token JWT da requisicao atual.
 * Todo dado de treino/registro e sempre filtrado pelo usuario logado,
 * para que um usuario nunca consiga ver ou alterar dados de outro.
 */
@Service
@RequiredArgsConstructor
public class UsuarioLogadoService {

    private final UsuarioRepository usuarioRepository;

    public Usuario obterUsuarioAtual() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado nao encontrado: " + email));
    }
}
