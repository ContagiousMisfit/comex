insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('10/9/2021 13:17:17', 0, 'NENHUM', 50.99, 1);
insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('7/24/2021 13:17:17', 0, 'NENHUM', 100.99, 2);
insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('2/16/2022 13:17:17', 0, 'NENHUM', 21, 3);
insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('1/19/2022 13:17:17', 0, 'NENHUM', 14, 4);
insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('12/26/2021 13:17:17', 96, 'FIDELIDADE', 17, 5);
insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('8/1/2021 13:17:17', 35, 'FIDELIDADE', 56, 6);
insert into pedidos (id, `data`, desconto, tipo_desconto, valor_total, cliente_id) values ('12/20/2021 13:17:17', 61, 'FIDELIDADE', 73, 7);

insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Dara', '634930564', '460-803-9124', 'Sachtjen', '8223', 'Alley', 'Tennyson', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Janaina', '512201180', '331-833-9202', '7th', '8', 'Alley', 'Vera','New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Isabella', '219462346', '307-476-9686', 'Burning Wood', '227', 'Drive', 'Kensington', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Bruna', '111646508', '985-272-9610', 'Pond', '551', 'Crossing', '4th', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Cassia', '314263483', '794-591-7591', 'Sunbrook', '15', 'Lane', 'Sugar', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Israel', '035785096', '561-372-6183', 'Cambridge', '5', 'Road', 'Holy Cross', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Pablo', '159726494', '452-369-5270', 'Mcbride', '474', 'Hill', 'Ohio', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Gabriel', '463636976', '557-781-5260', 'Shoshone', '177', 'Park', 'Michigan', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Larissa', '205445420', '241-649-5457', '5th', '657', 'Alley', 'Continental', 'New York', 'New York');
insert into clientes (nome, cpf, telefone, rua, numero, complemento, bairro, cidade, estado) values ('Thiago', '967842138', '265-290-9392', 'Roxbury', '14', 'Place', 'Arizona', 'New York', 'New York');

INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 50.99, 1, 0, 1, 1);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 100.99, 1, 0, 2, 2);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 51000.00, 1, 0, 3, 3);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 32000.00, 1, 0, 4, 4);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 33000.00, 1, 0, 5, 5);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 70000.00, 1, 0, 6, 6);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 50.99, 1, 0, 7, 7);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 100.99, 1, 0, 8, 8);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 100.99, 1, 0, 9, 9);
INSERT INTO itens_de_pedido(desconto, preco_unitario, quantidade, tipo_desconto, pedido_id, produto_id) VALUES (0, 100.99, 1, 0, 10, 10);