package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itens_de_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private long quantidade;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Pedido pedido;
    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    private TipoDescontoItemPedido tipoDesconto;

    public ItemDePedido(BigDecimal precoUnitario, long quantidade, Produto produto, Pedido pedido, BigDecimal desconto, TipoDescontoItemPedido tipoDesconto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public ItemDePedido(BigDecimal precoUnitario, long quantidade, Produto produto, BigDecimal desconto, TipoDescontoItemPedido tipoDesconto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.produto = produto;
        this.desconto = desconto;
        this.tipoDesconto = tipoDesconto;
    }

    public BigDecimal getValorTotal() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }

}
