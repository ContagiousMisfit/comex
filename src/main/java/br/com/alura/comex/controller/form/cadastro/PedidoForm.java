package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.repository.ClienteRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.model.utils.PedidoBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PedidoForm {

    private Long idCliente;

    private List<ItemDePedidoForm> listaDeItens;

    public Pedido converter(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {

        List<ItemDePedido> lista = new ArrayList<>();

        listaDeItens.forEach(item -> {
            lista.add(item.converter(produtoRepository));
        });

        Pedido pedido = new PedidoBuilder()
                .comCliente(clienteRepository.findById(idCliente).get())
                .comDataAtual()
                .comListaDePedidos(lista)
                .aplicarDesconto()
                .build();

        return pedido;

    }

}
