package com.keyone.managerapi.service;

import com.keyone.core.models.Exercicio;
import com.keyone.core.models.Usuario;
import com.keyone.core.repositories.ProgressoDiarioProjecao;
import com.keyone.core.repositories.RegistroExercicioRepository;
import com.keyone.managerapi.dto.response.PontoProgressoResponse;
import com.keyone.managerapi.dto.response.ProgressoExercicioResponse;
import com.keyone.managerapi.mapper.ExercicioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProgressoService {

    private final RegistroExercicioRepository registroExercicioRepository;
    private final ExercicioService exercicioService;
    private final ExercicioMapper exercicioMapper;
    private final UsuarioLogadoService usuarioLogadoService;

    /**
     * Monta a evolucao historica (carga maxima e volume por dia) de um exercicio
     * para o usuario logado, junto com o recorde pessoal ja alcancado.
     */
    @Transactional(readOnly = true)
    public ProgressoExercicioResponse progressoDoExercicio(Long exercicioId) {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();
        Exercicio exercicio = exercicioService.buscarEntidadePorId(exercicioId);

        var historico = registroExercicioRepository
                .buscarProgressoDiario(usuario.getId(), exercicioId).stream()
                .map(this::paraPonto)
                .toList();

        Double recorde = registroExercicioRepository.buscarRecordePessoal(usuario.getId(), exercicioId);

        return new ProgressoExercicioResponse(exercicioMapper.paraDTO(exercicio), recorde, historico);
    }

    private PontoProgressoResponse paraPonto(ProgressoDiarioProjecao projecao) {
        return new PontoProgressoResponse(
                projecao.getData(),
                projecao.getCargaMaxima(),
                projecao.getVolumeTotal(),
                projecao.getTotalSeries()
        );
    }
}
