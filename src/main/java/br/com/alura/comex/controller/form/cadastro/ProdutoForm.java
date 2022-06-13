package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Optional;
@ToString
public class ProdutoForm {


    @NotNull
    @Size(min = 2)
    private String nome;

    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitario;

    @Min(value = 0)
    private long quantidadeEmEstoque;

    @NotNull
    private Long idCategoria;

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque, categoria.get());
    }

}
