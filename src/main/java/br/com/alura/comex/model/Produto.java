package br.com.alura.comex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends AbstractEntity {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private long quantidadeEmEstoque;

    @ManyToOne
    private Categoria categoria;

    @OneToMany
    private List<ItemDePedido> itensDePedido;

}
