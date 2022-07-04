package br.com.alura.comex.loja.domain.factory;

import br.com.alura.comex.loja.domain.Categoria;
import br.com.alura.comex.loja.domain.Dimensoes;
import br.com.alura.comex.loja.domain.ItemDePedido;
import br.com.alura.comex.loja.domain.Produto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoBuilder {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private long quantidadeEmEstoque;

    private List<ItemDePedido> listaDeItensDePedidos;

    private Categoria categoria;

    private Dimensoes dimensoes;

    public ProdutoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdutoBuilder comDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ProdutoBuilder comPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public ProdutoBuilder quantidadeEmEstoque(long quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        return this;
    }

    public ProdutoBuilder comCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProdutoBuilder comDimensoes(Dimensoes dimensoes) {
        this.dimensoes = dimensoes;
        return this;
    }

    public Produto build() {
        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque, categoria, dimensoes);
    }

}
