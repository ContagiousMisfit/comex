package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.ItemDePedido;
import br.com.alura.comex.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDePedidoDto {

    private Long id;

    private ProdutoDto produtoDto;
    private long quantidade;
    private BigDecimal precoUnitario;

    public ItemDePedidoDto(ItemDePedido itemDePedido) {
        this.id = itemDePedido.getId();
        this.produtoDto = new ProdutoDto(itemDePedido.getProduto());
        this.quantidade = itemDePedido.getQuantidade();
        this.precoUnitario = itemDePedido.getPrecoUnitario();
    }

}
