package br.com.alura.comex.model.utils;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;

public class ProdutoBuilder {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private long quantidadeEmEstoque;

    private Categoria categoria;

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

    public Produto build() {
        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque, categoria);
    }

}
