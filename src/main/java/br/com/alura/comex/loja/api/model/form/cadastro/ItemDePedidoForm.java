package br.com.alura.comex.loja.api.model.form.cadastro;

import br.com.alura.comex.loja.api.repository.ProdutoRepository;
import br.com.alura.comex.loja.domain.ItemDePedido;
import br.com.alura.comex.loja.domain.Produto;
import br.com.alura.comex.loja.domain.factory.ItemDePedidoBuilder;
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
