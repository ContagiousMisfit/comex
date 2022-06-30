package br.com.alura.comex.loja.api.model.form.cadastro;

import br.com.alura.comex.loja.api.repository.CategoriaRepository;
import br.com.alura.comex.loja.domain.Categoria;
import br.com.alura.comex.loja.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoForm {

    @NotNull
    @Size(min = 2)
    private String nome;

    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull
    private BigDecimal precoUnitario;

    @Min(value = 0)
    @NotNull
    private long quantidadeEmEstoque;

    @NotNull
    private Long idCategoria;

    public Produto converter(CategoriaRepository categoriaRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        return new Produto(nome, descricao, precoUnitario, quantidadeEmEstoque, categoria.get());
    }

}
