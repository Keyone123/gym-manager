package com.keyone.managerapi.exception;

import java.time.Instant;
import java.util.List;

public record ErroResponse(
        Instant timestamp,
        int status,
        String erro,
        String mensagem,
        List<String> detalhes
) {
}
