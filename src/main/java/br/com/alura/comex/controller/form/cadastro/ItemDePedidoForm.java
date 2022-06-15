package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.model.utils.ItemDePedidoBuilder;
import br.com.alura.comex.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDePedidoForm {

    private Long idProduto;

    private long quantidadeVendida;

    public ItemDePedido converter(ProdutoRepository produtoRepository) {

        Produto produto = produtoRepository.findById(idProduto).get();

        if (quantidadeVendida > produto.getQuantidadeEmEstoque()) {
            throw new IllegalArgumentException();
        }

        ItemDePedido item = new ItemDePedidoBuilder()
                .comProduto(produto)
                .comQuantidade(quantidadeVendida)
                .comPrecoUnitario(produto.getPrecoUnitario())
                .aplicarDesconto()
                .build();

        return item;
    }

}
