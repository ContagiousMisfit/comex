package br.com.alura.comex.controller.form.atualizacao;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AtualizarCategoriaForm {

    @NotNull
    @Length(min = 2)
    String nome;

    public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        categoria.setNome(this.nome);
        return categoria;
    }

}
