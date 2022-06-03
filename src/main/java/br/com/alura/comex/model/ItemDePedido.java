package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDePedido extends AbstractEntity {

    private BigDecimal precoUnitario;
    private long quantidade;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Pedido pedido;
    private BigDecimal desconto;
    private TipoDesconto tipoDesconto;

}
