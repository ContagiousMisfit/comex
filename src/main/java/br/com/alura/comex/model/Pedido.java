package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido extends AbstractEntity {

    private LocalDate dataPedido;
    private Cliente cliente;
    private BigDecimal desconto;
    private TipoDesconto tipoDesconto;

    @OneToMany
    private List<ItemDePedido> listaDePedidos;
}
