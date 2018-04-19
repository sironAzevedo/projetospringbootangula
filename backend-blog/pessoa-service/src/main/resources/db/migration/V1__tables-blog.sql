CREATE DATABASE IF NOT EXISTS blog;
USE blog;

CREATE TABLE IF NOT EXISTS tb_pessoa
(
	id_pessoa int primary key auto_increment not null,
    ds_nome varchar(100) not null,
	ds_email varchar(100) not null,
    fl_ativo bit not null
);

CREATE TABLE IF NOT EXISTS tb_mes
(
	id_mes int primary key auto_increment not null,
    ds_mes varchar(100) not null
);

CREATE TABLE IF NOT EXISTS tb_mes_salario
(
	id_mes_salario int primary key auto_increment not null,
    id_mes int not null,
    valor_salario decimal(10,2) not null,
    FOREIGN KEY (id_mes) REFERENCES tb_mes(id_mes)
);

CREATE TABLE IF NOT EXISTS tb_conta
(
	id_conta int primary key auto_increment not null,
    tipo_conta varchar(100) not null,
    valor_conta decimal(10,2) not null,
    data_vencimento Date not null,
    data_pagamento Date not null,
    ds_comentario varchar(500),
    id_mes_salario int not null, 
   
    FOREIGN KEY (id_mes_salario) REFERENCES tb_mes_salario(id_mes_salario)
);

INSERT INTO tb_mes(ds_mes) VALUES ('JANEIRO');
INSERT INTO tb_mes(ds_mes) VALUES ('FEVEREIRO');
INSERT INTO tb_mes(ds_mes) VALUES ('MARÇO');
INSERT INTO tb_mes(ds_mes) VALUES ('ABRIL');
INSERT INTO tb_mes(ds_mes) VALUES ('MAIO');
INSERT INTO tb_mes(ds_mes) VALUES ('JUNHO');
INSERT INTO tb_mes(ds_mes) VALUES ('JULHO');
INSERT INTO tb_mes(ds_mes) VALUES ('AGOSTO');
INSERT INTO tb_mes(ds_mes) VALUES ('SETEMBRO');
INSERT INTO tb_mes(ds_mes) VALUES ('OUTUBRO');
INSERT INTO tb_mes(ds_mes) VALUES ('NOVEMBRO');
INSERT INTO tb_mes(ds_mes) VALUES ('DEZEMBRO');

