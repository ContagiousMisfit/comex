package br.com.alura.comex.loja.api.model;

import br.com.alura.comex.loja.domain.Categoria;
import br.com.alura.comex.loja.domain.StatusCategoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {

    private Long id;

    private String nome;

    private StatusCategoria status;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.status = categoria.getStatus();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).toList();
    }

}
