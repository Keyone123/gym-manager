package com.keyone.core.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "treino_exercicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(exclude = "treino")
public class TreinoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treino_id", nullable = false)
    private Treino treino;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exercicio_id", nullable = false)
    private Exercicio exercicio;

    @Column(nullable = false)
    private Integer ordem;

    @Column(name = "series_alvo", nullable = false)
    private Integer seriesAlvo;

    @Column(name = "repeticoes_alvo", nullable = false)
    private Integer repeticoesAlvo;

    @Column(name = "carga_alvo")
    private Double cargaAlvo;
}
