package com.keyone.managerapi.mapper;

import com.keyone.core.models.RegistroExercicio;
import com.keyone.core.models.RegistroTreino;
import com.keyone.managerapi.dto.response.RegistroExercicioResponse;
import com.keyone.managerapi.dto.response.RegistroTreinoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistroTreinoMapper {

    private final ExercicioMapper exercicioMapper;

    public RegistroTreinoResponse paraResponse(RegistroTreino registro) {
        var series = registro.getRegistrosExercicio().stream()
                .map(this::paraSerieResponse)
                .toList();

        return new RegistroTreinoResponse(
                registro.getId(),
                registro.getTreino() != null ? registro.getTreino().getId() : null,
                registro.getTreino() != null ? registro.getTreino().getNome() : null,
                registro.getDataExecucao(),
                registro.getObservacao(),
                series
        );
    }

    private RegistroExercicioResponse paraSerieResponse(RegistroExercicio serie) {
        return new RegistroExercicioResponse(
                serie.getId(),
                exercicioMapper.paraDTO(serie.getExercicio()),
                serie.getSerieNumero(),
                serie.getRepeticoes(),
                serie.getCarga()
        );
    }
}
