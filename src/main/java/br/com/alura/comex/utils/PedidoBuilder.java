package br.com.alura.comex.utils;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class PedidoBuilder {

    String categoria;
    String produto;
    String cliente;
    BigDecimal valor;
    int quantidade;
    LocalDate data;

    public PedidoBuilder comCategoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public PedidoBuilder comProduto(String produto) {
        this.produto = produto;
        return this;
    }

    public PedidoBuilder comCliente(String cliente) {
        this.cliente = cliente;
        return this;
    }

    public PedidoBuilder comValor(String valor) {
        this.valor = new BigDecimal(valor);
        return this;
    }

    public PedidoBuilder comQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public PedidoBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public Pedido build() {
        return new Pedido(categoria, produto, cliente, valor, quantidade, data);
    }

}
