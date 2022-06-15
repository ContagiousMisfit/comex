package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Cliente;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ClienteDto {

    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String local;

    private List<DetalhesDoPedidoDto> listaDePedidos = new ArrayList<>();

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
        this.local = cliente.getEndereco().getCidade() + cliente.getEndereco().getEstado();
    }

    public static List<ClienteDto> converter(List<Cliente> clientes) {
        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }

    public static Page<ClienteDto> converterPagina(Page<Cliente> clientes) {
        return clientes.map(ClienteDto::new);
    }

}
