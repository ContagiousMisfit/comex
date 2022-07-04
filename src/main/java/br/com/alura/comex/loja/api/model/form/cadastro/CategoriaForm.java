package br.com.alura.comex.loja.api.model.form.cadastro;

import br.com.alura.comex.loja.domain.Categoria;
import br.com.alura.comex.loja.domain.StatusCategoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaForm {
    @NotNull
    @Size(min = 2)
    private String nome;

    public Categoria converter() {
        return new Categoria(nome, StatusCategoria.ATIVA);
    }

}
