package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Cliente;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ClienteDto {

    private final Long id;

    private final String nome;

    private final String cpf;

    private final String telefone;

    private final EnderecoDto endereco;

    private List<DetalhesDoPedidoDto> listaDePedidos;

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.endereco = new EnderecoDto(cliente.getEndereco());
        this.listaDePedidos.addAll(cliente.getListaDePedidos().stream().map(DetalhesDoPedidoDto::new).collect(Collectors.toList()));
    }

    public static List<ClienteDto> converter(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }


}
