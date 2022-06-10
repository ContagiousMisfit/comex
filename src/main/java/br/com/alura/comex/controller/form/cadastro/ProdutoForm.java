package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

public class ProdutoForm {

    @NotNull
    @Length(min = 2)
    private String nome;

    private String descricao;

    @NotNull
    @Min(value = 0)
    private BigDecimal precoUnitario;

    private long quantidadeEmEstoque;

    private Long idCategoria;

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque, categoria.get());
    }

}
