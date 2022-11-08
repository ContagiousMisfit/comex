package br.com.alura.comex.loja.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "quantidade_em_estoque", nullable = false)
    private long quantidadeEmEstoque;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Categoria categoria;

    @Embedded
    private Dimensoes dimensoes;

    public Produto(String nome, String descricao, BigDecimal precoUnitario, long quantidadeEmEstoque, Categoria categoria, Dimensoes dimensoes) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;
        this.dimensoes = dimensoes;
    }

}
