CREATE TABLE medicos
(
    id            SERIAL PRIMARY KEY,
    nome          VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL UNIQUE,
    crm           VARCHAR(50)  NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    logradouro    VARCHAR(255) NOT NULL,
    bairro        VARCHAR(255) NOT NULL,
    cep           VARCHAR(20)  NOT NULL,
    cidade        VARCHAR(100) NOT NULL,
    uf            CHAR(2)      NOT NULL,
    numero        VARCHAR(20),
    complemento   VARCHAR(255)
);
