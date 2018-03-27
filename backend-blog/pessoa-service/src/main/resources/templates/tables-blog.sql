CREATE DATABASE IF NOT EXISTS blog;
USE blog;

CREATE TABLE IF NOT EXISTS tb_pessoa
(
	id_pessoa int primary key auto_increment not null,
    ds_nome varchar(100) not null,    
    fl_ativo bit not null
);

/*ALTER TABLE tb_pessoa ADD ds_email VARCHAR (100) not null;*/

CREATE TABLE IF NOT EXISTS tb_mes
(
	id_mes int primary key auto_increment not null,
    ds_mes varchar(100) not null
);

CREATE TABLE IF NOT EXISTS tb_mes_salario
(
	id_mes_salario int primary key auto_increment not null,
    id_mes int not null,
    valor_salario int,
    FOREIGN KEY (id_mes) REFERENCES tb_mes(id_mes)
);

CREATE TABLE IF NOT EXISTS tb_conta
(
	id_conta int primary key auto_increment not null,
    tipo_conta varchar(100) not null,
    valor_conta int not null,
    data_vencimento Date not null,
    data_pagamento Date not null,
    ds_comentario varchar(500) not null,
    id_mes_salario int not null, 
   
    FOREIGN KEY (id_mes_salario) REFERENCES tb_mes_salario(id_mes_salario)
);

INSERT INTO tb_mes VALUES (1,'JANEIRO');
INSERT INTO tb_mes VALUES (2, 'FEVEREIRO');
INSERT INTO tb_mes VALUES (3, 'MARÇO');
INSERT INTO tb_mes VALUES (4, 'ABRIL');
INSERT INTO tb_mes VALUES (5, 'MAIO');
INSERT INTO tb_mes VALUES (6, 'JUNHO');
INSERT INTO tb_mes VALUES (7, 'JULHO');
INSERT INTO tb_mes VALUES (8, 'AGOSTO');
INSERT INTO tb_mes VALUES (9, 'SETEMBRO');
INSERT INTO tb_mes VALUES (10, 'OUTUBRO');
INSERT INTO tb_mes VALUES (11, 'NOVEMBRO');
INSERT INTO tb_mes VALUES (12, 'DEZEMBRO');

