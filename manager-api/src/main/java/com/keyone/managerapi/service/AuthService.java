package com.keyone.managerapi.service;

import com.keyone.core.models.enums.Role;
import com.keyone.core.models.Usuario;
import com.keyone.core.repositories.UsuarioRepository;
import com.keyone.managerapi.dto.request.LoginRequest;
import com.keyone.managerapi.dto.request.RegistrarUsuarioRequest;
import com.keyone.managerapi.dto.response.AutenticacaoResponse;
import com.keyone.managerapi.exception.CredenciaisInvalidasException;
import com.keyone.managerapi.exception.EmailJaCadastradoException;
import com.keyone.managerapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AutenticacaoResponse registrar(RegistrarUsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new EmailJaCadastradoException(request.email());
        }

        Usuario usuario = Usuario.builder()
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .roles(Set.of(Role.USER))
                .ativo(true)
                .build();

        usuario = usuarioRepository.save(usuario);

        String token = jwtService.gerarToken(paraUserDetails(usuario));
        return AutenticacaoResponse.deToken(token, usuario.getNome(), usuario.getEmail());
    }

    public AutenticacaoResponse autenticar(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.senha()));
        } catch (BadCredentialsException ex) {
            throw new CredenciaisInvalidasException();
        }

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(CredenciaisInvalidasException::new);

        String token = jwtService.gerarToken(paraUserDetails(usuario));
        return AutenticacaoResponse.deToken(token, usuario.getNome(), usuario.getEmail());
    }

    private UserDetails paraUserDetails(Usuario usuario) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities(usuario.getRoles().stream().map(r -> "ROLE_" + r.name()).toArray(String[]::new))
                .build();
    }
}
