package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoriaForm {

    @NotNull
    @Length(min = 2)
    String nome;

    StatusCategoria status;

    public Categoria converter() {
        return new Categoria(nome, status);
    }

}
