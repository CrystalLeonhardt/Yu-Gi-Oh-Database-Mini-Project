CREATE TABLE IF NOT EXISTS atributo(
    atributoId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    atributoNome VARCHAR(10) NOT NULL 
);

CREATE TABLE IF NOT EXISTS tipo(
    tipoId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tipoNome VARCHAR(10) NOT NULL 
);

CREATE TABLE IF NOT EXISTS arquetipo(
    arquetipoId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    arquetipoNome VARCHAR(100) NOT NULL 
);

CREATE TABLE IF NOT EXISTS nivel(
    nivelId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nivelNumero INT NOT NULL 
);

CREATE TABLE IF NOT EXISTS carta(
    cartaId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cartaNome VARCHAR(100) NOT NULL,
    descricao VARCHAR(1000),
    atk INT NOT NULL,
    def INT NOT NULL
);

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuario(
    usuarioId UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome      VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL
);

-- Tabela de perfis (cargo/role)
CREATE TABLE IF NOT EXISTS perfil_usuario(
    id        SERIAL PRIMARY KEY,
    usuarioId UUID NOT NULL,
    cargo     VARCHAR(50) NOT NULL,
    CONSTRAINT fk_perfil_usuario FOREIGN KEY (usuarioId) REFERENCES usuario(usuarioId),
    CONSTRAINT perfil_usuario_unique UNIQUE (usuarioId)
);

CREATE TABLE IF NOT EXISTS imagem_carta (
    imagemId     UUID         PRIMARY KEY DEFAULT gen_random_uuid(),
    cartaId      UUID         NOT NULL UNIQUE,
    nomeOriginal VARCHAR(255) NOT NULL,
    caminho      VARCHAR(500) NOT NULL,
    CONSTRAINT fk_imagem_carta FOREIGN KEY (cartaId)
        REFERENCES carta(cartaId) ON DELETE CASCADE
);

ALTER TABLE carta ADD COLUMN atributoId UUID; IF NOT EXISTS
ALTER TABLE carta ADD COLUMN tipoId UUID; IF NOT EXISTS
ALTER TABLE carta ADD COLUMN arquetipoId UUID; IF NOT EXISTS
ALTER TABLE carta ADD COLUMN nivelId UUID; IF NOT EXISTS

ALTER TABLE carta ADD CONSTRAINT fk_Atributo FOREIGN KEY (atributoId) REFERENCES atributo(atributoId); IF NOT EXISTS
ALTER TABLE carta ADD CONSTRAINT fk_Tipo FOREIGN KEY (tipoId) REFERENCES tipo(tipoId); IF NOT EXISTS
ALTER TABLE carta ADD CONSTRAINT fk_Arquetipo FOREIGN KEY (arquetipoId) REFERENCES arquetipo(arquetipoId); IF NOT EXISTS
ALTER TABLE carta ADD CONSTRAINT fk_Nivel FOREIGN KEY (nivelId) REFERENCES nivel(nivelId); IF NOT EXISTS

ALTER TABLE nivel ALTER COLUMN nivelnumero TYPE VARCHAR(10);