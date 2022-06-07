package br.com.alura.comex.model.utils;

import br.com.alura.comex.model.*;

import java.math.BigDecimal;

public class ItemDePedidoBuilder {

    private BigDecimal precoUnitario;
    private long quantidade;

    private Produto produto;

    private Pedido pedido;
    private BigDecimal desconto;
    private TipoDescontoItemPedido tipoDesconto;

    public ItemDePedidoBuilder comPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public ItemDePedidoBuilder comQuantidade(long quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public ItemDePedidoBuilder comProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    public ItemDePedidoBuilder comDesconto(BigDecimal desconto) {
        this.desconto = desconto;
        return this;
    }

    public ItemDePedidoBuilder comTipoDesconto(TipoDescontoItemPedido tipoDesconto) {
        this.tipoDesconto = tipoDesconto;
        return this;
    }

    public ItemDePedidoBuilder comPedido(Pedido pedido) {
        this.produto = produto;
        return this;
    }

    public ItemDePedido build() {
        return new ItemDePedido(precoUnitario, quantidade, produto, pedido, desconto, tipoDesconto);
    }

}
