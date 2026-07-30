package com.keyone.managerapi.dto.response;

public record AutenticacaoResponse(
        String token,
        String tipo,
        String nome,
        String email
) {
    public static AutenticacaoResponse deToken(String token, String nome, String email) {
        return new AutenticacaoResponse(token, "Bearer", nome, email);
    }
}
