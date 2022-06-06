DROP DATABASE dbtest;
CREATE DATABASE dbtest;
USE dbtest;

USE comexdb;

DROP TABLE categorias;
DROP TABLE produtos;
DROP TABLE clientes;
DROP TABLE pedidos;
DROP TABLE itens_de_pedidos;

CREATE TABLE categorias(
id bigint AUTO_INCREMENT,
nome varchar(200) NOT NULL,
status varchar(20) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE produtos(
-- mudar depois bigint
id bigint AUTO_INCREMENT,
nome varchar(200) NOT NULL,
descricao varchar(500),
preco_unitario decimal(38,2) NOT NULL,
categoria_id bigint NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE produtos
ADD FOREIGN KEY (categoria_id) REFERENCES categorias(id);

CREATE TABLE clientes(
id int(4) AUTO_INCREMENT,
nome varchar(200) NOT NULL,
cpf varchar(11) NOT NULL,
telefone varchar(15) NOT NULL,
rua varchar(30) NOT NULL,
numero varchar(10) NOT NULL,
complemento varchar(15),
bairro varchar(15) NOT NULL,
cidade varchar(30) NOT NULL,
estado varchar(30) NOT NULL,
PRIMARY KEY (id)
);


CREATE TABLE pedidos(
id int(4) AUTO_INCREMENT,
data datetime not null,
cliente_id bigint not null,
desconto double,
tipo_de_desconto varchar(10) not null,
PRIMARY KEY (id)
);

ALTER TABLE pedidos
ADD FOREIGN KEY (cliente_id) REFERENCES clientes(id);

CREATE TABLE itens_de_pedidos(
id int(4) AUTO_INCREMENT,
preco_unitario double NOT NULL,
quantidade int NOT NULL,
produto_id int(4) NOT NULL,
pedido_id int(4) NOT NULL,
desconto double,
tipo_de_desconto varchar(10) NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE itens_de_pedidos
ADD FOREIGN KEY (produto_id) REFERENCES produtos(id);

ALTER TABLE itens_de_pedidos
ADD FOREIGN KEY (pedido_id) REFERENCES pedidos(id);

SELECT * FROM categorias;

