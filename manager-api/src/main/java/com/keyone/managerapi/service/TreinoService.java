package com.keyone.managerapi.service;

import com.keyone.core.models.Exercicio;
import com.keyone.core.models.Treino;
import com.keyone.core.models.TreinoExercicio;
import com.keyone.core.models.Usuario;
import com.keyone.core.repositories.TreinoRepository;
import com.keyone.managerapi.dto.request.TreinoExercicioRequest;
import com.keyone.managerapi.dto.request.TreinoRequest;
import com.keyone.managerapi.dto.response.TreinoResponse;
import com.keyone.managerapi.exception.RecursoNaoEncontradoException;
import com.keyone.managerapi.mapper.TreinoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ExercicioService exercicioService;
    private final TreinoMapper treinoMapper;
    private final UsuarioLogadoService usuarioLogadoService;

    @Transactional(readOnly = true)
    public List<TreinoResponse> listarMeusTreinos() {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();
        return treinoRepository.findByUsuarioIdAndAtivoTrue(usuario.getId()).stream()
                .map(treinoMapper::paraResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TreinoResponse buscarPorId(Long id) {
        return treinoMapper.paraResponse(buscarEntidadeDoUsuarioAtual(id));
    }

    @Transactional
    public TreinoResponse cadastrar(TreinoRequest request) {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();

        Treino treino = Treino.builder()
                .usuario(usuario)
                .nome(request.nome())
                .descricao(request.descricao())
                .ativo(true)
                .build();

        adicionarExercicios(treino, request.exercicios());

        return treinoMapper.paraResponse(treinoRepository.save(treino));
    }

    @Transactional
    public TreinoResponse atualizar(Long id, TreinoRequest request) {
        Treino treino = buscarEntidadeDoUsuarioAtual(id);

        treino.setNome(request.nome());
        treino.setDescricao(request.descricao());
        treino.getExercicios().clear();
        adicionarExercicios(treino, request.exercicios());

        return treinoMapper.paraResponse(treinoRepository.save(treino));
    }

    @Transactional
    public void remover(Long id) {
        Treino treino = buscarEntidadeDoUsuarioAtual(id);
        // soft delete: preservamos o historico de registros de treino que apontam para ele
        treino.setAtivo(false);
        treinoRepository.save(treino);
    }

    private void adicionarExercicios(Treino treino, List<TreinoExercicioRequest> itens) {
        for (TreinoExercicioRequest item : itens) {
            Exercicio exercicio = exercicioService.buscarEntidadePorId(item.exercicioId());
            TreinoExercicio treinoExercicio = TreinoExercicio.builder()
                    .exercicio(exercicio)
                    .ordem(item.ordem())
                    .seriesAlvo(item.seriesAlvo())
                    .repeticoesAlvo(item.repeticoesAlvo())
                    .cargaAlvo(item.cargaAlvo())
                    .build();
            treino.adicionarExercicio(treinoExercicio);
        }
    }

    Treino buscarEntidadeDoUsuarioAtual(Long id) {
        Usuario usuario = usuarioLogadoService.obterUsuarioAtual();
        return treinoRepository.findByIdAndUsuarioId(id, usuario.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Treino nao encontrado: id=" + id));
    }
}
