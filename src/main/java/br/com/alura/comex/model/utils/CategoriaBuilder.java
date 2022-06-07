package br.com.alura.comex.model.utils;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;

public class CategoriaBuilder {

    private String nome;

    private StatusCategoria status;

    public CategoriaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public CategoriaBuilder comStatus(StatusCategoria status) {
        this.status = status;
        return this;
    }

    public Categoria build() {
        return new Categoria(nome, status);
    }

}
