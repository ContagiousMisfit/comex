package br.com.alura.comex.loja.domain.factory;

import br.com.alura.comex.loja.domain.ItemDePedido;
import br.com.alura.comex.loja.domain.Produto;
import br.com.alura.comex.loja.domain.TipoDescontoItemPedido;

import java.math.BigDecimal;

public class ItemDePedidoBuilder {

    private BigDecimal precoUnitario;
    private long quantidade;

    private Produto produto;

    private BigDecimal desconto = BigDecimal.ZERO;
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

    public ItemDePedidoBuilder aplicarDesconto() {
        if (quantidade > 10) {
            this.tipoDesconto = TipoDescontoItemPedido.QUANTIDADE;
            this.desconto = BigDecimal.TEN;
            return this;
        }
        this.tipoDesconto = TipoDescontoItemPedido.NENHUM;
        this.desconto = BigDecimal.ZERO;
        return this;
    }


    public ItemDePedido build() {
        return new ItemDePedido(precoUnitario, quantidade, produto, desconto, tipoDesconto);
    }

}
