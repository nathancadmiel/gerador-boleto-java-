CREATE DATABASE IF NOT EXISTS boleto_db;
USE boleto_db;

CREATE TABLE IF NOT EXISTS boletos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    banco VARCHAR(50) NOT NULL,
    agencia VARCHAR(10),
    conta VARCHAR(20),
    nosso_numero VARCHAR(50),
    linha_digitavel VARCHAR(100),
    pagador VARCHAR(100),
    cedente VARCHAR(100),
    valor DOUBLE,
    data_emissao VARCHAR(20),
    data_vencimento VARCHAR(20),
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

