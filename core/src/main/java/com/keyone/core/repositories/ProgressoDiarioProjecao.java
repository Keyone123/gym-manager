package com.keyone.core.repositories;

import java.time.Instant;

public interface ProgressoDiarioProjecao {
    Instant getData();
    Double getCargaMaxima();
    Double getVolumeTotal();
    Long getTotalSeries();
}
