package com.keyone.managerapi.mapper;

import com.keyone.core.models.Exercicio;
import com.keyone.managerapi.dto.ExercicioDTO;
import org.springframework.stereotype.Component;

@Component
public class ExercicioMapper {

    public ExercicioDTO paraDTO(Exercicio exercicio) {
        if (exercicio == null) {
            return null;
        }
        return new ExercicioDTO(
                exercicio.getId(),
                exercicio.getNome(),
                exercicio.getGrupoMuscular(),
                exercicio.getDescricao()
        );
    }

    public Exercicio paraEntidade(ExercicioDTO dto) {
        return Exercicio.builder()
                .id(dto.id())
                .nome(dto.nome())
                .grupoMuscular(dto.grupoMuscular())
                .descricao(dto.descricao())
                .build();
    }
}
