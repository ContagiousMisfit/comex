package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.StatusCategoria;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
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
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }

}
