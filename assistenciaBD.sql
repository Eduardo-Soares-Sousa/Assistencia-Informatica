DROP DATABASE assistencia;
CREATE DATABASE assistencia;
USE assistencia;

CREATE TABLE endereco (
	codigo BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
	logradouro VARCHAR(50) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(50) NULL,
    bairro VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cliente (
	codigo BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(20) NOT NULL,
    senha VARCHAR(150) NOT NULL,
    endereco_id BIGINT(20) NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE formapagamento (
	codigo BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE funcionario (
	codigo BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE service (
	codigo BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(300) NOT NULL,
    dataEmissao DATE NOT NULL,
    dataFinalizacao DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    formaPagamento BIGINT(20) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    observacao VARCHAR(300) NULL,
    cliente_id BIGINT(20) NOT NULL,
    funcionario_id BIGINT(20) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(codigo), 
    FOREIGN KEY (formaPagamento) REFERENCES formapagamento(codigo),
    FOREIGN KEY (funcionario_id) REFERENCES funcionario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT * FROM funcionario;
SELECT * FROM cliente;
SELECT * FROM endereco;
SELECT * FROM formapagamento;
SELECT * FROM service;
