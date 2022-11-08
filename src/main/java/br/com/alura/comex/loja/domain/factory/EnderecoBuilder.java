package br.com.alura.comex.loja.domain.factory;

import br.com.alura.comex.loja.domain.Endereco;

public class EnderecoBuilder {

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public EnderecoBuilder comRua(String rua) {
        this.rua = rua;
        return this;
    }

    public EnderecoBuilder comNumero(String numero) {
        this.numero = numero;
        return this;
    }

    public EnderecoBuilder comComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public EnderecoBuilder comBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public EnderecoBuilder comCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecoBuilder comEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public Endereco build() {
        return new Endereco(rua, numero, complemento, bairro, cidade, estado);
    }

}
