package br.com.alura.comex.loja;

import br.com.alura.comex.loja.domain.ItemDePedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDePedidoEvent {

    private Long id;

    private String nomeProduto;

    private String categoriaProduto;

    private long quantidade;

    private BigDecimal precoUnitario;

    public ItemDePedidoEvent(ItemDePedido itemDePedido) {
        this.id = itemDePedido.getId();
        this.nomeProduto = itemDePedido.getProduto().getNome();
        this.categoriaProduto = itemDePedido.getProduto().getCategoria().getNome();
        this.quantidade = itemDePedido.getQuantidade();
        this.precoUnitario = itemDePedido.getPrecoUnitario();
    }
}
