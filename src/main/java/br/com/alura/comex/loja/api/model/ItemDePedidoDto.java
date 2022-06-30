package br.com.alura.comex.loja.api.model;

import br.com.alura.comex.loja.domain.ItemDePedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDePedidoDto {

    private Long id;

    private String nomeProduto;

    private String categoriaProduto;
    private long quantidade;
    private BigDecimal precoUnitario;

    public ItemDePedidoDto(ItemDePedido itemDePedido) {
        this.id = itemDePedido.getId();
        this.nomeProduto = itemDePedido.getProduto().getNome();
        this.categoriaProduto = itemDePedido.getProduto().getCategoria().getNome();
        this.quantidade = itemDePedido.getQuantidade();
        this.precoUnitario = itemDePedido.getPrecoUnitario();
    }

}
