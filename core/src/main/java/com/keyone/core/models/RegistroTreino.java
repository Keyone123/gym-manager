package com.keyone.core.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "registros_treino")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = "registrosExercicio")
public class RegistroTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treino_id")
    private Treino treino;

    @Column(name = "data_execucao", nullable = false)
    private Instant dataExecucao;

    @Column(length = 500)
    private String observacao;

    @Builder.Default
    @OneToMany(mappedBy = "registroTreino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegistroExercicio> registrosExercicio = new ArrayList<>();

    @PrePersist
    void aoPersistir() {
        if (dataExecucao == null) {
            dataExecucao = Instant.now();
        }
    }

    public void adicionarRegistroExercicio(RegistroExercicio registroExercicio) {
        registroExercicio.setRegistroTreino(this);
        this.registrosExercicio.add(registroExercicio);
    }
}
