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

    /*
    CONSTRAINT fk_Atributo FOREIGN KEY (id) REFERENCES atributo(atributoId),
    CONSTRAINT fk_Tipo FOREIGN KEY (id) REFERENCES tipo(tipoId),
    CONSTRAINT fk_Arquetipo FOREIGN KEY (id) REFERENCES arquetipo(arquetipoId),
    CONSTRAINT fk_Nivel FOREIGN KEY (id) REFERENCES nivel(nivelId)
    */
);

