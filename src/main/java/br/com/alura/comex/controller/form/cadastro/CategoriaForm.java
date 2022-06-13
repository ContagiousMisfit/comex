package br.com.alura.comex.controller.form.cadastro;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CategoriaForm {

    //programe voltado à interface/especificação e não à uma implementação
    @NotNull
    @Size(min = 2)
    private String nome;

    public Categoria converter() {
        return new Categoria(nome, StatusCategoria.ATIVA);
    }

}
