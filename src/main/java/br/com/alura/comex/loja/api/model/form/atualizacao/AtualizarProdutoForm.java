package br.com.alura.comex.loja.api.model.form.atualizacao;

import br.com.alura.comex.loja.api.repository.CategoriaRepository;
import br.com.alura.comex.loja.api.repository.ProdutoRepository;
import br.com.alura.comex.loja.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class AtualizarProdutoForm {

    @NotNull
    @Size(min = 2)
    private String nome;

    private String descricao;

    @NotNull
    @Min(value = 0)
    private BigDecimal precoUnitario;

    private long quantidadeEmEstoque;

    private Long idCategoria;

    public Produto atualizar(Long id, CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getReferenceById(id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(this.precoUnitario);
        produto.setQuantidadeEmEstoque(this.quantidadeEmEstoque);
        produto.setCategoria(categoriaRepository.findById(this.idCategoria).get());
        return produto;
    }
}
