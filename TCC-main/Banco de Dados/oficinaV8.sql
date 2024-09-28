uCREATE DATABASE oficinav8;

USE oficinav8;


CREATE TABLE cliente (
    idCliente INT,
    idOrcamento INT,
    email VARCHAR(100),
    endereco VARCHAR(150),
    telefone VARCHAR(20),
    nome VARCHAR(150),
    celular VARCHAR(20),
    veiculo VARCHAR(250),
    placa VARCHAR(10),
    fk_usuario_idUsuario INT,
    PRIMARY KEY (idCliente, idOrcamento)
);

CREATE TABLE usuario (
    nome VARCHAR(150),
    cpf VARCHAR(15),
    endereco VARCHAR(150),
    cargo VARCHAR(100),
    senha VARCHAR(25),
    cargaHoraria VARCHAR(100),
    email VARCHAR(100),
    rg VARCHAR(12),
    idUsuario INT PRIMARY KEY
);

CREATE TABLE orcamento (
    idOrcamento INT,
    data DATE,
    pdf BIT,
    idCliente INT,
    fk_cliente_idCliente INT,
    fk_cliente_idOrcamento INT,
    PRIMARY KEY (idOrcamento, idCliente)
);

CREATE TABLE cronograma (
    idCronograma INT,
    idUsuario INT,
    dataInicio DATE,
    dataFim DATE,
    descricao VARCHAR(255),
    cor VARCHAR(20),
    fk_usuario_idUsuario INT,
    PRIMARY KEY (idCronograma, idUsuario)
);

CREATE TABLE materiais (
    idMaterial INT,
    idOrcamento INT,
    unidade INT,
    quantidade INT,
    descriminizacao VARCHAR(255),
    precoUnidade DOUBLE,
    valor DOUBLE,
    codProduto INT,
    PRIMARY KEY (idMaterial, idOrcamento)
);

CREATE TABLE itenDoOrcamento (
    fk_orcamento_idOrcamento INT,
    fk_orcamento_idCliente INT,
    fk_materiais_idMaterial INT,
    fk_materiais_idOrcamento INT
);
 
ALTER TABLE cliente ADD CONSTRAINT FK_itenDoOrcamento_2
    FOREIGN KEY (fk_usuario_idUsuario)
    REFERENCES usuario (idUsuario)
    ON DELETE RESTRICT;
 
ALTER TABLE orcamento ADD CONSTRAINT FK_orcamento_2
    FOREIGN KEY (fk_cliente_idCliente, fk_cliente_idOrcamento)
    REFERENCES cliente (idCliente, idOrcamento)
    ON DELETE RESTRICT;
 
ALTER TABLE cronograma ADD CONSTRAINT FK_cronograma_2
    FOREIGN KEY (fk_usuario_idUsuario)
    REFERENCES usuario (idUsuario)
    ON DELETE CASCADE;
 
ALTER TABLE itenDoOrcamento ADD CONSTRAINT FK_itenDoOrcamento_1
    FOREIGN KEY (fk_orcamento_idOrcamento, fk_orcamento_idCliente)
    REFERENCES orcamento (idOrcamento, idCliente)
    ON DELETE RESTRICT;
 
ALTER TABLE itenDoOrcamento ADD CONSTRAINT FK_itenDoOrcamento_2
    FOREIGN KEY (fk_materiais_idMaterial, fk_materiais_idOrcamento)
    REFERENCES materiais (idMaterial, idOrcamento)
    ON DELETE RESTRICT;