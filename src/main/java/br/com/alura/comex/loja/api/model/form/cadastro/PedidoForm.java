package br.com.alura.comex.loja.api.model.form.cadastro;

import br.com.alura.comex.loja.api.repository.ClienteRepository;
import br.com.alura.comex.loja.api.repository.ProdutoRepository;
import br.com.alura.comex.loja.domain.ItemDePedido;
import br.com.alura.comex.loja.domain.Pedido;
import br.com.alura.comex.loja.domain.factory.PedidoBuilder;
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
