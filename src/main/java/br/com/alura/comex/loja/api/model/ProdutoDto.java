package br.com.alura.comex.loja.api.model;

import br.com.alura.comex.loja.domain.Dimensoes;
import br.com.alura.comex.loja.domain.Produto;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProdutoDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoUnitario;

    private long quantidadeEmEstoque;

    private CategoriaDto categoria;

    private Dimensoes dimensoes;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = new CategoriaDto(produto.getCategoria());
        this.descricao = produto.getDescricao();
        this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
        this.precoUnitario = produto.getPrecoUnitario();
        this.dimensoes = produto.getDimensoes();
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).toList();
    }

    public static Page<ProdutoDto> converterPagina(Page<Produto> produtos) {
        return produtos.map(ProdutoDto::new);
    }

}
