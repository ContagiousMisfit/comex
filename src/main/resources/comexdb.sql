DROP DATABASE comexdb;
USE comexdb;

DROP TABLE categorias;
DROP TABLE produtos;
DROP TABLE clientes;
DROP TABLE pedidos;
DROP TABLE itens_de_pedidos;

CREATE TABLE categorias(
id bigint AUTO_INCREMENT,
nome varchar(255) NOT NULL,
status varchar(30) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE produtos(
id bigint AUTO_INCREMENT,
nome varchar(255) NOT NULL,
descricao varchar(500),
preco_unitario decimal(38,2) NOT NULL,
categoria_id bigint NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE produtos
ADD FOREIGN KEY (categoria_id) REFERENCES categorias(id);

CREATE TABLE clientes(
id bigint AUTO_INCREMENT,
nome varchar(255) NOT NULL,
cpf varchar(30) NOT NULL,
telefone varchar(30) NOT NULL,
rua varchar(150) NOT NULL,
numero varchar(10) NOT NULL,
complemento varchar(30),
bairro varchar(50) NOT NULL,
cidade varchar(50) NOT NULL,
estado varchar(50) NOT NULL,
PRIMARY KEY (id)
);


CREATE TABLE pedidos(
id bigint AUTO_INCREMENT,
data datetime not null,
cliente_id bigint not null,
desconto decimal(38,2),
tipo_de_desconto varchar(10) not null,
PRIMARY KEY (id)
);

ALTER TABLE pedidos
ADD FOREIGN KEY (cliente_id) REFERENCES clientes(id);

CREATE TABLE itens_de_pedidos(
id bigint AUTO_INCREMENT,
preco_unitario bigint NOT NULL,
quantidade bigint NOT NULL,
produto_id bigint NOT NULL,
pedido_id bigint NOT NULL,
desconto decimal(38,2),
tipo_de_desconto varchar(10) NOT NULL,
PRIMARY KEY (id)
);

ALTER TABLE itens_de_pedidos
ADD FOREIGN KEY (produto_id) REFERENCES produtos(id);

ALTER TABLE itens_de_pedidos
ADD FOREIGN KEY (pedido_id) REFERENCES pedidos(id);

