package br.com.alura.comex.loja.domain.factory;

import br.com.alura.comex.loja.domain.Cliente;
import br.com.alura.comex.loja.domain.Endereco;
import br.com.alura.comex.loja.domain.Pedido;

import java.util.List;

public class ClienteBuilder {

    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;

    private List<Pedido> listaDePedidos;

    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder comCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClienteBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public ClienteBuilder comEndereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public ClienteBuilder comListaDePedidos(List<Pedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
        return this;
    }

    public Cliente build() {
        return new Cliente(nome, cpf, telefone, endereco, listaDePedidos);
    }
}
