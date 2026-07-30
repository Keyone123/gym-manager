package com.keyone.managerapi.service;

import com.keyone.core.models.*;
import com.keyone.core.repositories.RegistroTreinoRepository;
import com.keyone.managerapi.dto.request.RegistroExercicioRequest;
import com.keyone.managerapi.dto.request.RegistroTreinoRequest;
import com.keyone.managerapi.dto.response.RegistroTreinoResponse;
import com.keyone.managerapi.exception.RecursoNaoEncontradoException;
import com.keyone.managerapi.mapper.RegistroTreinoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroTreinoService {

    private final RegistroTreinoRepository registroTreinoRepository;
    private final ExercicioService exercicioService;
    private final TreinoService treinoService;
    private final RegistroTreinoMapper registroTreinoMapper;
    private final UsuarioLogadoService usuarioLogadoService;

    @Transactional(readOnly = true)
    public List<RegistroTreinoResponse> listarHistorico() {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();
        return registroTreinoRepository.findByUsuarioIdOrderByDataExecucaoDesc(usuario.getId()).stream()
                .map(registroTreinoMapper::paraResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public RegistroTreinoResponse buscarPorId(Long id) {
        return registroTreinoMapper.paraResponse(buscarEntidadeDoUsuarioAtual(id));
    }

    @Transactional
    public RegistroTreinoResponse registrar(RegistroTreinoRequest request) {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();

        Treino treino = request.treinoId() != null
                ? treinoService.buscarEntidadeDoUsuarioAtual(request.treinoId())
                : null;

        RegistroTreino registro = RegistroTreino.builder()
                .usuario(usuario)
                .treino(treino)
                .dataExecucao(request.dataExecucao() != null ? request.dataExecucao() : Instant.now())
                .observacao(request.observacao())
                .build();

        for (RegistroExercicioRequest item : request.series()) {
            Exercicio exercicio = exercicioService.buscarEntidadePorId(item.exercicioId());
            RegistroExercicio serie = RegistroExercicio.builder()
                    .exercicio(exercicio)
                    .serieNumero(item.serieNumero())
                    .repeticoes(item.repeticoes())
                    .carga(item.carga())
                    .build();
            registro.adicionarRegistroExercicio(serie);
        }

        return registroTreinoMapper.paraResponse(registroTreinoRepository.save(registro));
    }

    @Transactional
    public void remover(Long id) {
        RegistroTreino registro = buscarEntidadeDoUsuarioAtual(id);
        registroTreinoRepository.delete(registro);
    }

    private RegistroTreino buscarEntidadeDoUsuarioAtual(Long id) {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();
        return registroTreinoRepository.findByIdAndUsuarioId(id, usuario.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Registro de treino nao encontrado: id=" + id));
    }
}
