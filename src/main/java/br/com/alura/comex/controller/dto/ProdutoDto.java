package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Produto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProdutoDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private long quantidadeEmEstoque;

    private CategoriaDto categoria;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
        this.categoria = new CategoriaDto(produto.getCategoria());
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }

}
