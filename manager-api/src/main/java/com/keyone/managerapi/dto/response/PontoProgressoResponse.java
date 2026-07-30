package com.keyone.managerapi.dto.response;

import java.time.Instant;

public record PontoProgressoResponse(
        Instant data,
        Double cargaMaxima,
        Double volumeTotal,
        Long totalSeries
) {
}
