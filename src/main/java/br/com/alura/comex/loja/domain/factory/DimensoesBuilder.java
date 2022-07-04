package br.com.alura.comex.loja.domain.factory;

import br.com.alura.comex.loja.domain.Dimensoes;

public class DimensoesBuilder {

    private Double comprimento;
    private Double altura;
    private Double largura;
    private Double peso;

    public DimensoesBuilder comComprimento(Double comprimento) {
        this.comprimento = comprimento;
        return this;
    }

    public DimensoesBuilder comAltura(Double altura) {
        this.altura = altura;
        return this;
    }

    public DimensoesBuilder comLargura(Double largura) {
        this.largura = largura;
        return this;
    }

    public DimensoesBuilder comPeso(Double peso) {
        this.peso = peso;
        return this;
    }

    public Dimensoes build() {
        return new Dimensoes(comprimento, altura, largura, peso);
    }

}
