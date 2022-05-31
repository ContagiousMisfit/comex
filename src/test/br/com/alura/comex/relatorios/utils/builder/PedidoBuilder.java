package br.com.alura.comex.relatorios.utils.builder;

import br.com.alura.comex.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PedidoBuilder {

    private String categoria;
    private String produto;
    private String cliente;
    private BigDecimal valor;
    private int quantidade;
    private LocalDate data;

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
