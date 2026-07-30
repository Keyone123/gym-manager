-- Schema inicial do gym-manager

CREATE TABLE usuarios (
                          id          BIGSERIAL PRIMARY KEY,
                          nome        VARCHAR(120) NOT NULL,
                          email       VARCHAR(180) NOT NULL UNIQUE,
                          senha       VARCHAR(255) NOT NULL,
                          ativo       BOOLEAN NOT NULL DEFAULT TRUE,
                          criado_em   TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE usuario_roles (
                               usuario_id  BIGINT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
                               role        VARCHAR(20) NOT NULL,
                               PRIMARY KEY (usuario_id, role)
);

CREATE TABLE exercicios (
                            id              BIGSERIAL PRIMARY KEY,
                            nome            VARCHAR(120) NOT NULL UNIQUE,
                            grupo_muscular  VARCHAR(60),
                            descricao       VARCHAR(500)
);

CREATE TABLE treinos (
                         id          BIGSERIAL PRIMARY KEY,
                         usuario_id  BIGINT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
                         nome        VARCHAR(120) NOT NULL,
                         descricao   VARCHAR(500),
                         ativo       BOOLEAN NOT NULL DEFAULT TRUE,
                         criado_em   TIMESTAMP NOT NULL DEFAULT now()
);

CREATE INDEX idx_treinos_usuario_id ON treinos(usuario_id);

CREATE TABLE treino_exercicios (
                                   id                BIGSERIAL PRIMARY KEY,
                                   treino_id         BIGINT NOT NULL REFERENCES treinos(id) ON DELETE CASCADE,
                                   exercicio_id      BIGINT NOT NULL REFERENCES exercicios(id),
                                   ordem             INTEGER NOT NULL,
                                   series_alvo       INTEGER NOT NULL,
                                   repeticoes_alvo   INTEGER NOT NULL,
                                   carga_alvo        DOUBLE PRECISION
);

CREATE INDEX idx_treino_exercicios_treino_id ON treino_exercicios(treino_id);

CREATE TABLE registros_treino (
                                  id              BIGSERIAL PRIMARY KEY,
                                  usuario_id      BIGINT NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
                                  treino_id       BIGINT REFERENCES treinos(id),
                                  data_execucao   TIMESTAMP NOT NULL,
                                  observacao      VARCHAR(500)
);

CREATE INDEX idx_registros_treino_usuario_id ON registros_treino(usuario_id);
CREATE INDEX idx_registros_treino_data ON registros_treino(data_execucao);

CREATE TABLE registros_exercicio (
                                     id                  BIGSERIAL PRIMARY KEY,
                                     registro_treino_id  BIGINT NOT NULL REFERENCES registros_treino(id) ON DELETE CASCADE,
                                     exercicio_id        BIGINT NOT NULL REFERENCES exercicios(id),
                                     serie_numero        INTEGER NOT NULL,
                                     repeticoes          INTEGER NOT NULL,
                                     carga               DOUBLE PRECISION NOT NULL
);

CREATE INDEX idx_registros_exercicio_registro_treino_id ON registros_exercicio(registro_treino_id);
CREATE INDEX idx_registros_exercicio_exercicio_id ON registros_exercicio(exercicio_id);
